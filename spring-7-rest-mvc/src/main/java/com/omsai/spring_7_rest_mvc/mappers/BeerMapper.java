package com.omsai.spring_7_rest_mvc.mappers;

import com.omsai.spring_7_rest_mvc.entities.Beer;
import com.omsai.spring_7_rest_mvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
