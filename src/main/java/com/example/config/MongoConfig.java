package com.example.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Bean
    public MongoClient mongo() {
        MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder()
                .connectionsPerHost(200)
                .connectTimeout(30000)
                .socketTimeout(60000)
                .threadsAllowedToBlockForConnectionMultiplier(1500)
                .build();

        MongoClient mongoClient = new MongoClient(mongoHost, mongoClientOptions);

        //return new MongoClient(mongoHost);
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), mongoDB);
    }
}
