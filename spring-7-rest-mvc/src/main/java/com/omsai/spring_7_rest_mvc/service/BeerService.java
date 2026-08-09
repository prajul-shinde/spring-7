package com.omsai.spring_7_rest_mvc.service;

import com.omsai.spring_7_rest_mvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);

    List<Beer> listBeers();
}