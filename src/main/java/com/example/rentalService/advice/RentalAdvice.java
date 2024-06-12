package com.example.rentalService.advice;

import com.example.rentalService.exceptions.MovieNoExceptionFoundException;
import com.example.rentalService.exceptions.MovieNotAvailableException;
import com.example.rentalService.exceptions.MovieNotFoundException;
import com.example.rentalService.exceptions.MovieWrongDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
public class RentalAdvice extends RuntimeException{

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Exception occured on request.Exception message: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(MovieWrongDataException.class)
    public ResponseEntity<String> handleMovieWrongDataException(MovieWrongDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occured on request.Exception message: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(MovieNotAvailableException.class)
    public ResponseEntity<String> handleMovieNotAvailableException(MovieNotAvailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occured on request.Exception message: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(MovieNoExceptionFoundException.class)
    public ResponseEntity<String> handle500Exception() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Exception occured on request. Invalid endpoint");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        if (ex.getMessage().contains("No static resource")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Bad gateway. There is a problem with MovieService");
        } else if (ex.getMessage().contains("Connection refused")) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Gateway timeout. There is a problem with MovieService");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
