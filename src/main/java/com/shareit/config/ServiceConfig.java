package com.shareit.config;

import com.shareit.model.auth.GrantToken;
import com.shareit.model.entities.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ServiceConfig {

    @Bean
    public List<Post> fresh() {
        return new ArrayList<>();
    }

    @Bean
    public GrantToken emptyTokenBean() {
        GrantToken token = new GrantToken();
        token.setTokenType("Bearer");
        return token;
    }

}
