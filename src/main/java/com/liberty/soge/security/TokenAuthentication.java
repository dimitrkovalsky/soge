package com.liberty.soge.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
public class TokenAuthentication implements Authentication {
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private UserAccount principal;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(String token, Collection<GrantedAuthority> authorities, boolean isAuthenticated,
                               UserAccount principal) {
        this.token = token;
        this.authorities = authorities;
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>() {{
            this.add(() -> "USER");
        }};
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getName() {
        if (principal != null)
            return ((UserDetails) principal).getUsername();
        else
            return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        isAuthenticated = b;
    }

    public String getToken() {
        return token;
    }

}
