package com.shareit.config;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shareit.exception.ForbiddenJson;
import com.shareit.security.TokenAuthenticationFilter;
import com.shareit.security.TokenFilterConfigurer;
import com.shareit.security.TokenProvider;
import com.shareit.service.TrustedUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private TrustedUserDetailsService trustedUserDetailsService;


    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(trustedUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .formLogin()
                    .disable()
                .httpBasic()
                    .disable()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers(
                            "/signup",
                            "/login",
                            "/error").permitAll()
                .anyRequest()
                    .authenticated();

        http.apply(new TokenFilterConfigurer(tokenProvider));
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ServletOutputStream out = response.getOutputStream();
            new ObjectMapper().writeValue(out, new ForbiddenJson(ex.getMessage()));
            out.flush();
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html");
    }

}
