package com.schoolmanagement.utils;

public class StudentMessageGenerator {
    public static String createStudentExistsMessage (String firstName, String lastName) {
        return String.format("Student (%s %s) exists and can't add it again!", firstName, lastName);
    }

    public static String createStudentDoesNotExistMessage (String firstName, String lastName) {
        return String.format("Student (%s %s) does not exist!", firstName, lastName);
    }

    public static String createStudentDoesNotExistMessage (String studentNo) {
        return String.format("Student with studentNo (%s) does not exist!", studentNo);
    }

    public static String createStudentDoesNotHaveEnrolledLessonsMessage (String studentNo) {
        return String.format("Student with studentNo (%s) does not have any enrolled lessons!", studentNo);
    }

    public static String createStudentCreatedMessage (String firstName, String lastName) {
        return String.format("Student (%s %s) created successfully!", firstName, lastName);
    }

    public static String createStudentUpdateddMessage (String firstName, String lastName) {
        return String.format("Student (%s %s) updated successfully!", firstName, lastName);
    }

    public static String createStudentDeletedMessage (String studentNo) {
        return String.format("Student (%s) created successfully!", studentNo);
    }
}
