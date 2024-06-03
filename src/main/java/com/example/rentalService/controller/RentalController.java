package com.example.rentalService.controller;

import com.example.rentalService.model.Movie;
import com.example.rentalService.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/get/movie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getMovie(id));
    }

    @PutMapping("/return/movie/{id}")
    public ResponseEntity<Void> returnMovie(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
