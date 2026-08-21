package com.omsai.spring_7_reactive_mongo.web.fn;

import com.omsai.spring_7_reactive_mongo.model.BeerDTO;
import com.omsai.spring_7_reactive_mongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BeerHandler {

    private final BeerService beerService;

    public Mono<ServerResponse> deleteBeerById(ServerRequest serverRequest) {
        return beerService.deleteBeerById(serverRequest.pathVariable("beerId"))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> patchBeerById(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BeerDTO.class)
                .flatMap(beerDTO -> beerService.patchBeer(serverRequest.pathVariable("beerId"), beerDTO))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateBeerById(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BeerDTO.class)
                .flatMap(beerDto -> beerService.updateBeer(serverRequest.pathVariable("beerId"), beerDto))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> saveNewBeer(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BeerDTO.class)
                .flatMap(beerService::saveBeer)
                .flatMap(beerDTO -> ServerResponse
                        .created(UriComponentsBuilder.fromUriString(BeerRouterConfig.BEER_PATH_ID).build(beerDTO.getId()))
                        .build());
    }

    public Mono<ServerResponse> getBeerById(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .body(beerService.getById(serverRequest.pathVariable("beerId")), BeerDTO.class);
    }

    public Mono<ServerResponse> listBeers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }
}
