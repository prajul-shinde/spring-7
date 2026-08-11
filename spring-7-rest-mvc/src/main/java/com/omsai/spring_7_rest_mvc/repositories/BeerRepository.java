package com.omsai.spring_7_rest_mvc.repositories;

import com.omsai.spring_7_rest_mvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
