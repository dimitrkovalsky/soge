package com.liberty.soge;

import com.liberty.soge.config.ActionTypesConfig;
import com.liberty.soge.config.BaseConfig;
import com.liberty.soge.config.SecurityConfig;
import com.liberty.soge.config.WebMvcConfig;
import com.liberty.soge.config.WebSocketConfig;
import com.liberty.soge.config.quartz.JobConfig;
import com.liberty.soge.config.quartz.QuartzConfig;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * @author Dmytro_Kovalskyi.
 * @since 08.02.2017.
 */

@Slf4j
@SpringBootApplication(scanBasePackageClasses = { BaseConfig.class, SecurityConfig.class, JobConfig.class,
        QuartzConfig.class, ActionTypesConfig.class, WebSocketConfig.class, WebMvcConfig.class})
public class Runner implements ServletContextInitializer {
    
    @Value("${cookie.name}")
    private String cookieName;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Runner.class);
        application.run(args);
        log.info("Application started...");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.getSessionCookieConfig().setName(cookieName);
    }

}
