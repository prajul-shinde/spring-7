package com.omsai.spring_7_reactive_mongo.mappers;

import com.omsai.spring_7_reactive_mongo.domain.Beer;
import com.omsai.spring_7_reactive_mongo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}