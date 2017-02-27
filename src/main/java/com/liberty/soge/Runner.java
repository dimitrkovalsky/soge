package com.liberty.soge;

import com.liberty.soge.config.ActionTypesConfig;
import com.liberty.soge.config.BaseConfig;
import com.liberty.soge.config.SecurityConfig;
import com.liberty.soge.config.quartz.JobConfig;
import com.liberty.soge.config.quartz.QuartzConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dmytro_Kovalskyi.
 * @since 08.02.2017.
 */

@Slf4j
@SpringBootApplication(scanBasePackageClasses = { BaseConfig.class, SecurityConfig.class, JobConfig.class,
        QuartzConfig.class, ActionTypesConfig.class})
public class Runner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Runner.class);
        application.run(args);
        log.info("Application started...");
    }

}
