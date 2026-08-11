package com.omsai.spring_7_rest_mvc.service;

import com.omsai.spring_7_rest_mvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);

    List<BeerDTO> listBeers();

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    void updateBeerById(UUID beerId, BeerDTO beerDTO);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beerDTO);
}