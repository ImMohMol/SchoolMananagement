package com.schoolmanagement.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Set;

@MappedSuperclass
public class ApplicationUser implements UserDetails {
    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;
    @Column (nullable = false, unique = true, length = 100)
    private String username;
    @Column (nullable = false, length = 30)
    private String password;

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
