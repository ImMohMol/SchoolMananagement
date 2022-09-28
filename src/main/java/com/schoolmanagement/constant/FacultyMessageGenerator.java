package com.schoolmanagement.constant;

public class FacultyMessageGenerator {
    public static String createDuplicateFacultyMessage (String facultyName) {
        return String.format("Faculty (%s) exists and can't add it again!", facultyName);
    }

    public static String createFacultyDoesNotExists (String facultyName) {
        return String.format("There is no faculty with name (%s)!", facultyName);
    }

    public static String createFacultyDoesNotExists (Long facultyId) {
        return String.format("There is no faculty with id (%s)!", facultyId);
    }
}
