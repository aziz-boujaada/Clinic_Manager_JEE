package Helpers;

import Enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class RequestsValidator {

    public static String required(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " " + "is required");
        }
        return value.trim();
    }

    public static String emailsValidate(String email, String fieldName) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        required(email, fieldName);
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("invalid email format");
        }

        return email.trim();
    }

    public static String phonesValidate(String phone, String fieldName) {
        String phoneRegex =  "^\\d{10}$";
        required(phone, fieldName);
        if (!phone.matches(phoneRegex)) {
            throw new IllegalArgumentException("invalid phone number");
        }

        return phone.trim();
    }

    public static String passwordsValidate(String password, String fieldName) {

        required(password, fieldName);
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be contains 6 characters at lest");
        }

        return password.trim();
    }

    public static <E extends Enum<E>> E enumValuesValidate(
            String value,
            Class<E> enumClass,
            String enumName
    ) {

        required(value, enumName);

        try {
            return Enum.valueOf(
                    enumClass,
                    value.trim().toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid" + " " + enumName);
        }
    }

    public static LocalDate birthDate(String value) {

         required(value, "birth date");

        try {
            LocalDate date = LocalDate.parse(value);

            if (date.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(
                        "Birth date cannot be in the future"
                );
            }

            return date;

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid birth date"
            );
        }
    }

    public static int positiveInt(String value, String fieldName) {
        try {

            int number = Integer.parseInt(required(value, fieldName));
            if (number <= 0) {
                throw new IllegalArgumentException(fieldName + " " + "must be a positive number ");
            }

            return number;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " " + "must be a valid number ");
        }
    }

    public static double positiveDouble(String value, String fieldName) {
        try {

            double number = Double.parseDouble(required(value, fieldName));
            if (number <= 0) {
                throw new IllegalArgumentException(fieldName + " " + "must be a positive number ");
            }

            return number;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " " + "must be a valid number ");
        }
    }

    // parsing dates  and times
    public static LocalDate parseDate(String date , String fieldName) {
        required(date , fieldName);
        return LocalDate.parse(date);
    }

    public static LocalTime parseTime(String time , String fieldName) {
        required(time , fieldName);
        return LocalTime.parse(time);
    }

    public static LocalDateTime parseDateTime(String dateTime , String fieldName) {
        required(dateTime , fieldName);
        return LocalDateTime.parse(dateTime);
    }
}
