package com.liberty.soge.config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration of spring security.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Value("${cookie.name}")
    private String cookieName;
    
    @Bean
    public Filter authenticationFilter() {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(customAuthenticationManager());
        filter.setPostOnly(false);
        filter.setAuthenticationSuccessHandler(successHandler());
        return filter;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                    throws IOException, ServletException {
                log.warn("authentication failed");
                response.sendRedirect("/signin");
            }};
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() { 
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws ServletException, IOException {
                        log.info("authentication successful");
                        response.sendRedirect("/");
                    }
        };
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() {
        return new ProviderManager(Arrays.asList(
                //TODO implement as service in future.
        new AuthenticationProvider() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String userName = authentication.getName();
                String password = authentication.getCredentials().toString();
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(()-> {return "ROLE_ADMIN";});
                Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
                return auth;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }
            
        }));
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/api", "/test").hasRole("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/login").permitAll();
        http.logout().logoutSuccessUrl("/signin").deleteCookies(cookieName);
    }
    
}
