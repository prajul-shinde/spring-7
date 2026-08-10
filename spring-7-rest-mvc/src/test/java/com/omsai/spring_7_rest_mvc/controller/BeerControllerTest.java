package com.omsai.spring_7_rest_mvc.controller;

import com.omsai.spring_7_rest_mvc.model.Beer;
import com.omsai.spring_7_rest_mvc.service.BeerService;
import com.omsai.spring_7_rest_mvc.service.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.UUID;

import static com.omsai.spring_7_rest_mvc.controller.BeerController.BEER_PATH;
import static com.omsai.spring_7_rest_mvc.controller.BeerController.BEER_PATH_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<Beer> beerArgumentCaptor;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }


    @Test
    void updateBeerPatchById() throws Exception {

        Beer beer = beerServiceImpl.listBeers().getFirst();
        HashMap<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name");

        mockMvc.perform(patch(BEER_PATH_ID, beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isNoContent());

        verify(beerService).patchBeerById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
    }

    @Test
    void deleteById() throws Exception {
        Beer beer = beerServiceImpl.listBeers().getFirst();
        mockMvc.perform(delete(BEER_PATH_ID, beer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).deleteById(uuidArgumentCaptor.capture());
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void updateBeerById() throws Exception {
        Beer beer = beerServiceImpl.listBeers().getFirst();

        mockMvc.perform(put(BEER_PATH_ID, beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isNoContent());
        verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));

    }

    @Test
    void handlePost() throws Exception {
        Beer beer = beerServiceImpl.listBeers().getFirst();
        beer.setVersion(null);
        beer.setId(null);

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(post(BEER_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void listBeers() throws Exception {
        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());
        mockMvc.perform(get(BEER_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));

    }

    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().getFirst();
        given(beerService.getBeerById(any(UUID.class))).willReturn(testBeer);
        mockMvc.perform(get(BEER_PATH_ID, UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));
    }
}