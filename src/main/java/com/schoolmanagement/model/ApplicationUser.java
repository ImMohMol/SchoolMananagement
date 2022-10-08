package com.schoolmanagement.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class ApplicationUser implements UserDetails {
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String username;
    private final String password;

    public ApplicationUser (Set<? extends GrantedAuthority> grantedAuthorities,
                            String username,
                            String password) {
        this.grantedAuthorities = grantedAuthorities;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword () {
        return this.username;
    }

    @Override
    public String getUsername () {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}
