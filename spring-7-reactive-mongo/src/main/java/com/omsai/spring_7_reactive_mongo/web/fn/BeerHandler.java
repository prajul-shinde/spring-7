package com.omsai.spring_7_reactive_mongo.web.fn;

import com.omsai.spring_7_reactive_mongo.model.BeerDTO;
import com.omsai.spring_7_reactive_mongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BeerHandler {

    private final BeerService beerService;

    public Mono<ServerResponse> listBeers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }
}
