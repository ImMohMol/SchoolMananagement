package com.schoolmanagement.security;

public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    LESSON_READ("lesson:read"),
    LESSON_WRITE("lesson:write"),
    TEACHER_READ("teacher:read"),
    TEACHER_WRITE("teacher:write"),
    FACULTY_READ("faculty:read"),
    FACULTY_WRITE("faculty:write");

    private final String permission;

    ApplicationUserPermission (String permission) {
        this.permission = permission;
    }

    public String getPermission () {
        return permission;
    }
}
