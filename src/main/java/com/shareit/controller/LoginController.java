package com.shareit.controller;

import com.shareit.aspect.RestLogger;
import com.shareit.exception.NotAuthorizedException;
import com.shareit.model.auth.GrantToken;
import com.shareit.model.auth.TrustedUser;
import com.shareit.security.Role;
import com.shareit.security.TokenProvider;
import com.shareit.service.TrustedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestLogger
@RestController
public class LoginController {

    @Autowired
    private TrustedUserDetailsService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private GrantToken response;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public GrantToken signup(@Valid @RequestBody TrustedUser user) {
        TrustedUser savedUser = userService.create(user);
        response.setToken(tokenProvider.createToken(savedUser.getUsername(),
                        Collections.singletonList(Role.ROLE_CLIENT)));
        return response;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GrantToken login(@Valid @RequestBody TrustedUser user) {
        TrustedUser registeredUser = userService.findByUsername(user.getUsername());
        if (registeredUser.getUsername() == null)
            throw new NotAuthorizedException(user.getUsername());
        response.setToken(tokenProvider.createToken(registeredUser.getUsername(),
                        Collections.singletonList(Role.ROLE_CLIENT)));
        return response;
    }
}
