package com.liberty.soge.codegen;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmytro_Kovalskyi.
 * @since 21.02.2017.
 */
@Configuration
@ComponentScan("com.liberty.soge")
@EnableAutoConfiguration
public class GeneratorConfig {

    @Bean
    public GeneratorContext generatorContext(){
        return new GeneratorContext("D:\\github\\soge\\src\\main\\java", "com.liberty.soge");
    }
}
