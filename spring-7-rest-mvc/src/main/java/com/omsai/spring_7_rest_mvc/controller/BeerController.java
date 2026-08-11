package com.omsai.spring_7_rest_mvc.controller;

import com.omsai.spring_7_rest_mvc.model.BeerDTO;
import com.omsai.spring_7_rest_mvc.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO) {

        beerService.patchBeerById(beerId, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {

        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO) {
        beerService.updateBeerById(beerId, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity handlePost(@RequestBody BeerDTO beerDTO) {

        BeerDTO savedBeerDTO = beerService.saveNewBeer(beerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beerDTO/" + savedBeerDTO.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get BeerDTO by Id - in controller");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

}