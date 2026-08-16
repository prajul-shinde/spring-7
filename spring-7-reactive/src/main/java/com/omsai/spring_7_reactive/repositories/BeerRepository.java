package com.omsai.spring_7_reactive.repositories;

import com.omsai.spring_7_reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
