package com.omsai.spring_7_webclient.client;

import com.omsai.spring_7_webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;

import java.util.Map;

public interface BeerClient {

    Flux<String> listBeers();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeerJsonNode();

    Flux<BeerDTO> listBeerDtos();

    Mono<BeerDTO> getBeerById(String beerId);

    Flux<BeerDTO> getBeerByBeerStyle(String style);

    Mono<BeerDTO> createBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(BeerDTO dto);

    Mono<Void> deleteBeer(BeerDTO dto);
}
