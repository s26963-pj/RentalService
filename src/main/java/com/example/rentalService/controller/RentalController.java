package com.example.rentalService.controller;

import com.example.rentalService.model.Movie;
import com.example.rentalService.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get movie by id", description = "Return one movie")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {

        return ResponseEntity.ok(rentalService.getMovie(id));
    }

    @PutMapping("/return/movie/{id}")
    @Operation(summary = "Return the movie", description = "Changes the isAvailable attribute to true")
    @ApiResponse(responseCode = "200", description = "Successfully returned")
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    public ResponseEntity<Void> returnMovie(@PathVariable Long id){
        rentalService.returnMovie(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/rent/movie/{id}")
    @Operation(summary = "Rent", description = "Rent the movie")
    @ApiResponse(responseCode = "200", description = "Successfully rented")
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @ApiResponse(responseCode = "400", description = "Movie with that id is not available", content = @Content)
    public ResponseEntity<Void> rentMovie(@PathVariable Long id){
        rentalService.rentMovie(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
