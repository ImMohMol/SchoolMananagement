package com.schoolmanagement.constant;

public class TeacherMessageGenerator {
    public static String createTeacherExistsMessage (String firstName, String lastName) {
        return String.format("Teacher (%s %s) exists and can't add it again!", firstName, lastName);
    }

    public static String createTeacherDoesNotExistMessage (String firstName, String lastName) {
        return String.format("Teacher (%s %s) does not exist!", firstName, lastName);
    }

    public static String createTeacherDoesNotExistMessage (String personalNo) {
        return String.format("Teacher with personalNo (%s) does not exist!", personalNo);
    }

    public static String createTeacherCreatedMessage (String firstName, String lastName) {
        return String.format("Teacher (%s %s) created successfully!", firstName, lastName);
    }

    public static String createTeacherUpdatedMessage (String firstName, String lastName) {
        return String.format("Teacher (%s %s) updated successfully!", firstName, lastName);
    }

    public static String createTeacherDeletedMessage (String personalNO) {
        return String.format("Teacher with personalNo (%s) deleted successfully!", personalNO);
    }

    public static String createTeacherHasNoStudentsMessage (String personalNo) {
        return String.format("Teacher with personalNo (%s) has no students!", personalNo);
    }
}
