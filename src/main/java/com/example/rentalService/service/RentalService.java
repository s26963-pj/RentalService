package com.example.rentalService.service;

import com.example.rentalService.model.Movie;
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

    public Void returnMovie(Long id){
        return restTemplate.getForEntity("http://localhost:8080/movies/isavailable/"+id, Void.class).getBody();
    }
}
