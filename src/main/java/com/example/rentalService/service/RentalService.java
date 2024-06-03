package com.example.rentalService.service;

import com.example.rentalService.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RentalService {

    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(Long id){
        return restTemplate.getForObject("http://localhost:8080/movies/"+id, Movie.class);
    }

    public void returnMovie(Long id){
        restTemplate.put("http://localhost:8080/movies/isavailable/"+id, null);
    }

    public void rentMovie(Long id){
        restTemplate.put("http://localhost:8080/movies/rent/"+id, null);
    }
}
