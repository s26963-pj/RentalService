package com.example.rentalService.service;

import com.example.rentalService.exceptions.MovieNotAvailableException;
import com.example.rentalService.exceptions.MovieNotFoundException;
import com.example.rentalService.exceptions.MovieWrongDataException;
import com.example.rentalService.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RentalService {

    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(Long id){
        try {
            return restTemplate.getForObject("http://localhost:8080/movies/" + id, Movie.class);
        } catch (HttpClientErrorException e) {
             if (e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                 throw new MovieNotFoundException("Movie with that id has not been found");
             } else {
                 throw e;
             }
        }
    }

    public void returnMovie(Long id){
        try {
            restTemplate.put("http://localhost:8080/movies/isavailable/" + id, null);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                throw new MovieNotFoundException("Movie with that id has not been found");
            } else {
                throw e;
            }
        }
    }

    public void rentMovie(Long id){
        try {
            restTemplate.put("http://localhost:8080/movies/rent/"+id, null);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) {
                throw new MovieNotAvailableException("Movie with that id is being used");
            } else if (e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                throw new MovieNotFoundException("Movie with that id has not been found");
            } else {
                throw e;
            }
        }

    }
}
