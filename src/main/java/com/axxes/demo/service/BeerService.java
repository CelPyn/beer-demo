package com.axxes.demo.service;

import com.axxes.demo.domain.Beer;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    List<Beer> getBeers();

    Optional<Beer> findById(int id);

    List<Beer> searchByName(String search);
}
