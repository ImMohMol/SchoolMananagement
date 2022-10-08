package com.schoolmanagement.model.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.schoolmanagement.model.enums.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(new HashSet<>(Arrays.asList(FACULTY_READ, FACULTY_WRITE, LESSON_READ, LESSON_WRITE, STUDENT_READ,
            STUDENT_WRITE, TEACHER_READ, TEACHER_WRITE))),
    MANAGER(new HashSet<>(Arrays.asList(FACULTY_READ, FACULTY_WRITE, LESSON_READ, LESSON_WRITE)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole (Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions () {
        return permissions;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> getGrantedAuthorities = this.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        getGrantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", this.name())));
        return getGrantedAuthorities;
    }
}
