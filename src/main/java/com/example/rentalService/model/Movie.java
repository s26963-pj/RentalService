package com.example.rentalService.model;

public class Movie {

    private Long id;
    private String name;
    private String category;
    private Boolean is_available;

    public Movie(Long id, String name, String category, Boolean is_available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.is_available = false;
    }
    public Movie(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }
}
