package com.liberty.soge.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration of spring security.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthFilter authFilter;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    
    @Bean
    protected ProviderManager authenticationManager() {
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests().antMatchers("/auth/**").permitAll().and()
        .authorizeRequests().antMatchers("/*").permitAll().and()
        .authorizeRequests().antMatchers("/api/**").hasAuthority("ADMIN").and()
        .formLogin()
        .usernameParameter("j_username")
        .passwordParameter("j_password")
        .loginProcessingUrl("/login")
        .failureForwardUrl("/");
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                List<GrantedAuthority> grantedAthorities = new ArrayList<>();
                grantedAthorities.add(new GrantedAuthority() {
                    
                    /**
                     * 
                     */
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getAuthority() {
                        // TODO Auto-generated method stub
                        return "ADMIN";
                    }
                });
                //TODO provide user source
                User user = new User(
                        username, 
                        stubEncode(username), 
                        true, 
                        true, 
                        true, 
                        true, 
                        grantedAthorities);
                
                return user;
            }
        };
    }
    
    private String stubEncode(String input) {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        return e.encode(input);
    }
    
}
