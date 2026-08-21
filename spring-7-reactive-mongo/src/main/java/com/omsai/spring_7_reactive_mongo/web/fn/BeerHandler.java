package com.omsai.spring_7_reactive_mongo.web.fn;

import com.omsai.spring_7_reactive_mongo.model.BeerDTO;
import com.omsai.spring_7_reactive_mongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BeerHandler {

    private final BeerService beerService;
    private final Validator validator;

    private void validate(BeerDTO beerDTO) {
        Errors errors = new BeanPropertyBindingResult(beerDTO, "beerDto");
        validator.validate(beerDTO, errors);
        if (errors.hasErrors())
            throw new ServerWebInputException(errors.toString());
    }

    public Mono<ServerResponse> deleteBeerById(ServerRequest serverRequest) {
        return beerService.getById(serverRequest.pathVariable("beerId"))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(beerDTO -> beerService.deleteBeerById(beerDTO.getId()))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> patchBeerById(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BeerDTO.class)
                .doOnNext(this::validate)
                .flatMap(beerDTO -> beerService.patchBeer(serverRequest.pathVariable("beerId"), beerDTO))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateBeerById(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BeerDTO.class)
                .doOnNext(this::validate)
                .flatMap(beerDto -> beerService.updateBeer(serverRequest.pathVariable("beerId"), beerDto))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(savedDto -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> saveNewBeer(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BeerDTO.class)
                .doOnNext(this::validate)
                .flatMap(beerService::saveBeer)
                .flatMap(beerDTO -> ServerResponse
                        .created(UriComponentsBuilder.fromUriString(BeerRouterConfig.BEER_PATH_ID).build(beerDTO.getId()))
                        .build());
    }

    public Mono<ServerResponse> getBeerById(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .body(beerService.getById(serverRequest.pathVariable("beerId"))
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))), BeerDTO.class);
    }

    public Mono<ServerResponse> listBeers(ServerRequest serverRequest) {

        Flux<BeerDTO> flux;
        if (serverRequest.queryParam("beerStyle").isPresent()) {
            flux = beerService.findByBeerStyle(serverRequest.queryParam("beerStyle").get());
        } else {
            flux = beerService.listBeers();
        }
        return ServerResponse.ok()
                .body(flux, BeerDTO.class);
    }
}
