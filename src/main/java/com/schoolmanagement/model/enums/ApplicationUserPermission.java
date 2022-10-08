package com.schoolmanagement.model.enums;

public enum ApplicationUserPermission {
    FACULTY_READ("faculty:read"),
    FACULTY_WRITE("faculty:write"),
    LESSON_READ("lesson:read"),
    LESSON_WRITE("lesson:write"),
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    TEACHER_READ("teacher:read"),
    TEACHER_WRITE("teacher:write");

    private final String permission;

    ApplicationUserPermission (String permission) {
        this.permission = permission;
    }

    public String getPermission () {
        return this.permission;
    }
}
