package com.schoolmanagement.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IApplicationUserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername (String username) throws UsernameNotFoundException;
}
