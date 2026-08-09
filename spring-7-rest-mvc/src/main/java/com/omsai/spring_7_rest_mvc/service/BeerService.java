package com.omsai.spring_7_rest_mvc.service;

import com.omsai.spring_7_rest_mvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);

    List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);
}