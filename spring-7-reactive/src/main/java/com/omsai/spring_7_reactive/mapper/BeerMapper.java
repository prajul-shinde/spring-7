package com.omsai.spring_7_reactive.mapper;

import com.omsai.spring_7_reactive.domain.Beer;
import com.omsai.spring_7_reactive.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}