package com.hotel.ena.restservice;

public class RacunNotFoundException extends RuntimeException {
    public RacunNotFoundException(Long id) {
        super("Nije moguce pronaci racun " + id);
    }
}
