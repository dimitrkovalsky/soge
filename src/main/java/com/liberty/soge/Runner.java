package com.liberty.soge;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.liberty.soge.config.ActionTypesConfig;
import com.liberty.soge.config.BaseConfig;
import com.liberty.soge.config.WebSocketConfig;
import com.liberty.soge.config.quartz.JobConfig;
import com.liberty.soge.config.quartz.QuartzConfig;

/**
 * @author Dmytro_Kovalskyi.
 * @since 08.02.2017.
 */
public class Runner {


    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(new Class<?>[]{BaseConfig.class, WebSocketConfig.class,
                        JobConfig.class, QuartzConfig.class, ActionTypesConfig.class}, args);

        System.out.println("Application started...");
    }

}
