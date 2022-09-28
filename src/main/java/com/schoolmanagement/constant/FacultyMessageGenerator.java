package com.schoolmanagement.constant;

public class FacultyMessageGenerator {
    public static String createDuplicateFacultyMessage (String facultyName) {
        return String.format("Faculty (%s) exists and can't add it again!", facultyName);
    }

    public static String createFacultyDoesNotExistsMessage (String facultyName) {
        return String.format("There is no faculty with name (%s)!", facultyName);
    }

    public static String createFacultyCreatedMessage (String facultyName) {
        return String.format("Faculty (%s) created successfully!", facultyName);
    }

    public static String createFacultyUpdatedMessage (String facultyName) {
        return String.format("Faculty (%s) information updated successfully!", facultyName);
    }

    public static String createFacultyDeletedMessage (String facultyName) {
        return String.format("Faculty (%s) delete successfully!", facultyName);
    }
}
