package com.example.rentalService.exceptions;

public class MovieNotAvailableException extends RuntimeException{
    public MovieNotAvailableException(String message) {
        super(message);
    }
}
