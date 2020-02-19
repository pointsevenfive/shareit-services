package com.shareit.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoClientConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    @Value("${spring.data.mongodb.port}")
    private String mongoPort;
    @Value("${spring.data.mongodb.name}")
    private String mongoName;

    @Override
    public String getDatabaseName() {
        return mongoName;
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(String.format("mongodb://%s:%s", mongoHost, mongoPort));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

}
