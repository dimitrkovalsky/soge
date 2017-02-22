package com.liberty.soge.config;

import com.liberty.soge.codegen.GeneratorContext;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Dmytro_Kovalskyi on 28.03.2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.liberty.soge")
@EnableMongoRepositories("com.liberty.soge.repository")
public class BaseConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "soge-db";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }


    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.liberty.soge.model";
    }

    @Bean
    public GeneratorContext generatorContext(){
        return new GeneratorContext("D:\\github\\soge\\", "com.liberty.soge");
    }


}