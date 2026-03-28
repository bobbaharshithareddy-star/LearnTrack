package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {

    // Prevent instantiation
    private InputValidator() {}

    public static void validateNotEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validatePositive(int value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive number.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || !email.contains("@")) {
            throw new InvalidInputException("Invalid email address: " + email);
        }
    }
        public static void isValidName(String value, String fieldname) throws InvalidInputException{
            if(value == null || value.trim().isEmpty() && !fieldname.matches("^[a-zA-Z]+$"))
            {
                throw new InvalidInputException("Name should not be in numbers or empty: " + fieldname);
            }
        }
}
