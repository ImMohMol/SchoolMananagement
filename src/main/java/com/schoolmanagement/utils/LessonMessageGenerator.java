package com.schoolmanagement.utils;

public class LessonMessageGenerator {
    public static String createLessonExistsMessage (String lessonName) {
        return String.format("Lesson (%s) exists and can't add it again!", lessonName);
    }

    public static String createLessonDoesNotExistMessage (String lessonName) {
        return String.format("There is no lesson with name (%s)!", lessonName);
    }

    public static String createLessonCreatedMessage (String lessonName) {
        return String.format("Lesson (%s) created successfully!", lessonName);
    }

    public static String createLessonUpdatedMessage (String lessonName) {
        return String.format("Lesson (%s) updated successfully!", lessonName);
    }

    public static String createLessonDeletedMessage (String lessonName) {
        return String.format("Lesson (%s) delete successfully!", lessonName);
    }
}
