package com.liberty.soge;

import com.liberty.soge.config.ActionTypesConfig;
import com.liberty.soge.config.BaseConfig;
import com.liberty.soge.config.SecurityConfig;
import com.liberty.soge.config.quartz.JobConfig;
import com.liberty.soge.config.quartz.QuartzConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Dmytro_Kovalskyi.
 * @since 08.02.2017.
 */
public class Runner {


    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(new Class<?>[]{BaseConfig.class,
                       SecurityConfig.class,
                        JobConfig.class, QuartzConfig.class, ActionTypesConfig.class}, args);

        System.out.println("Application started...");
    }

}
