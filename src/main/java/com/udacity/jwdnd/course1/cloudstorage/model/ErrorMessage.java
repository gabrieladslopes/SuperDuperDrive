package com.udacity.jwdnd.course1.cloudstorage.model;

public enum ErrorMessage {
    UNKNOWN_ERROR(0, ""),
    MAX_FILESIZE(1, "File is bigger than the max size allowed."),
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
