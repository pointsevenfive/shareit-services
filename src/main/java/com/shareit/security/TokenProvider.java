package com.shareit.security;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.shareit.exception.OauthException;
import com.shareit.service.TrustedUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {

    @Value("${keystore.resource.path}")
    private String ksLocation;

    @Value("${keystore.ksalias}")
    private String ksAlias;

    @Value("${keystore.kspass}")
    private String ksPass;

    @Value("${security.jwt.token.expiration:3600000}")
    private long expireMilliSecs;

    private Key rsaKey;

    private Key rsaPub;

    @Autowired
    private TrustedUserDetailsService userDetails;

    @Autowired
    private SecUtils secUtils;

    @PostConstruct
    protected void init() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        this.ksPass = Base64.getEncoder().encodeToString(ksPass.getBytes());
        this.rsaKey = secUtils.openKsGetKey(ksLocation, ksAlias, ksPass);
        this.rsaPub = secUtils.openKsGetPublicKey(ksLocation, ksAlias, ksPass);
    }

    public String createToken(String username, List<Role> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .filter(obj -> true)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().toInstant()
                        .plus(expireMilliSecs, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.RS256, rsaKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(rsaKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(rsaPub).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new OauthException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }

}
