package com.omsai.spring_7_reactive.repositories;

import com.omsai.spring_7_reactive.config.DatabaseConfig;
import com.omsai.spring_7_reactive.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.r2dbc.test.autoconfigure.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveNewBeer() {
        Beer beer = getTestBeer();
        beerRepository.save(beer).subscribe(System.out::println);
    }

    private Beer getTestBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("123213")
                .build();
    }

}