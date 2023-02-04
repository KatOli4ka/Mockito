package org.example.exception;

public class UserNonUniqueException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Пользователь не уникален!";
    }
}
