package com.example.prozproszone.mongo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories("com.example.prozproszone.repositories")
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    @Value("${mongodb.uri}")
    private String mongo_uri;

    @Override
    public MongoClient reactiveMongoClient() {

        return MongoClients.create(mongo_uri);
    }

    @Override
    protected String getDatabaseName() {
        return "db_wwsi2020";
    }

}
