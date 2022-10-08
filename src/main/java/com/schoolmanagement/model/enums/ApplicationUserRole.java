package com.schoolmanagement.model.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.schoolmanagement.model.enums.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(new HashSet<>(Arrays.asList(FACULTY_READ, FACULTY_WRITE, LESSON_READ, LESSON_WRITE, STUDENT_READ,
            STUDENT_WRITE, TEACHER_READ, TEACHER_WRITE))),
    MANAGER(new HashSet<>(Arrays.asList(FACULTY_READ, FACULTY_WRITE, LESSON_READ, LESSON_WRITE)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole (Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
