package com.company.ExceptionFile;

public class ResumeIncompleteException extends Exception {
    public ResumeIncompleteException (String text){
        super(text);
    }
}