package com.schoolmanagement.service.implementation;

import com.schoolmanagement.service.interfaces.IApplicationUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements IApplicationUserService {
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        return null;
    }
}
