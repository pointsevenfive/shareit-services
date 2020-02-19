package com.shareit.security;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

import com.shareit.exception.NotAuthorizedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecUtils {

    public SecUtils() {
        Security.addProvider(new BouncyCastleProvider());
    }

    Key
    openKsGetKey(String path, String alias, String pass)
            throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {

        char[] bytePasswd = new String(Base64.getDecoder().decode(pass)).toCharArray();
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(new ClassPathResource(path).getInputStream(), bytePasswd);

        return keystore.getKey(alias, bytePasswd);
    }

    Key
    openKsGetPublicKey(String path, String alias, String pass)
            throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {

        char[] bytePasswd = new String(Base64.getDecoder().decode(pass)).toCharArray();
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(new ClassPathResource(path).getInputStream(), bytePasswd);

        return keystore.getCertificate(alias).getPublicKey();
    }
    public String getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        throw new NotAuthorizedException("Authorization token does not contain required scope");
    }

}