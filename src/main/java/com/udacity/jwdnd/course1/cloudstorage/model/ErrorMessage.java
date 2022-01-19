package com.udacity.jwdnd.course1.cloudstorage.model;

public enum ErrorMessage {
    UNKNOWN_ERROR(0, ""),
    REPEATED_USERNAME(1, "Username already taken."),
    REPEATED_FILENAME(2, "File with that name already exists.");

    int type;
    String message;

    ErrorMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
