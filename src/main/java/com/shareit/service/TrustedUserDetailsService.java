package com.shareit.service;

import com.shareit.exception.NotFoundException;
import com.shareit.model.auth.TrustedUser;
import com.shareit.model.repository.TrustedUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrustedUserDetailsService implements UserDetailsService {

    @Autowired
    TrustedUserRepo userRepo;

    // TODO check for exception to handle duplicated users
    public TrustedUser create(TrustedUser user) {
        return userRepo.save(user);
    }

    public TrustedUser findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
        throws NotFoundException {
        TrustedUser user = userRepo.findByUsername(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email));
        return User
                .withUsername(email)
                .password(user.getAuthPhrase())
                .authorities("authPhrase")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
