package com.thanu.work.todo2023.excptions;

public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException (String message){
        super(message);
    }
}
