package com.omsai.spring_7_rest_mvc.repositories;

import com.omsai.spring_7_rest_mvc.entities.Beer;
import com.omsai.spring_7_rest_mvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My beer1222222222222222222222222222222222222222222222222222222222")
                    .beerStyle(BeerStyle.ALE)
                    .upc("12345")
                    .price(new BigDecimal("11.99"))
                    .build());
            // runs too fast and session ends quickly. tells hibernate to immediately write to database
            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer() {

        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My beer")
                .beerStyle(BeerStyle.ALE)
                .upc("12345")
                .price(new BigDecimal("11.99"))
                .build());
        // runs too fast and session ends quickly. tells hibernate to immediately write to database
        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}