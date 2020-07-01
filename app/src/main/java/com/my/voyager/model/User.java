package com.my.voyager.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private List<Place> visitedPlaces = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, List<Place> visitedPlaces) {
        this.email = email;
        this.password = password;
        this.visitedPlaces = visitedPlaces;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Place> getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(List<Place> visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }
}
