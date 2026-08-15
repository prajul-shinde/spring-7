package com.omsai.spring_7_restclient.client;

import com.omsai.spring_7_restclient.model.BeerDTO;
import com.omsai.spring_7_restclient.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    public static final String GET_BEER_PATH = "/api/v1/beer";
    public static final String GET_BEER_BY_ID_PATH = "/api/v1/beer/{beerId}";

    private final RestClient.Builder restClientBuilder;

    @Override
    public Page<BeerDTO> listBeers() {
        return null;
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public BeerDTO getBeerById(UUID beerId) {
        RestClient restClient = restClientBuilder.build();
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path(GET_BEER_BY_ID_PATH).build(beerId))
                .retrieve()
                .body(BeerDTO.class);
    }

    @Override
    public BeerDTO createBeer(BeerDTO newDto) {
        RestClient restClient = restClientBuilder.build();
        var location = restClient.post()
                .uri(uriBuilder -> uriBuilder.path(GET_BEER_PATH).build())
                .body(newDto)
                .retrieve()
                .toBodilessEntity()
                .getHeaders()
                .getLocation();
        return restClient.get()
                .uri(location.getPath())
                .retrieve()
                .body(BeerDTO.class);
    }

    @Override
    public BeerDTO updateBeer(BeerDTO beerDto) {
        RestClient restClient = restClientBuilder.build();
        restClient.put()
                .uri(uriBuilder -> uriBuilder.path(GET_BEER_BY_ID_PATH).build(beerDto.getId()))
                .body(beerDto)
                .retrieve();
        return getBeerById(beerDto.getId());
    }

    @Override
    public void deleteBeer(UUID beerId) {
        RestClient restClient = restClientBuilder.build();
        restClient.delete()
                .uri(uriBuilder -> uriBuilder.path(GET_BEER_BY_ID_PATH).build(beerId))
                .retrieve();
    }
}
