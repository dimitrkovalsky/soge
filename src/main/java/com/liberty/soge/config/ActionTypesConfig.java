package com.liberty.soge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.liberty.soge.postprocessors.ActionTypesPostProcessor;

@Configuration
@ComponentScan("com.liberty.soge")
public class ActionTypesConfig {
    
    @Bean
    public ActionTypesPostProcessor actionTypesPostProcessor() {
        return new ActionTypesPostProcessor();
    }
    
}
