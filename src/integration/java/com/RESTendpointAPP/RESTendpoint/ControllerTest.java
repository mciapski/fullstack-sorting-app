package com.RESTendpointAPP.RESTendpoint;

import com.RESTendpointAPP.RESTendpoint.controller.Controller;
import com.RESTendpointAPP.RESTendpoint.entity.RequestedCurrency;
import com.RESTendpointAPP.RESTendpoint.entity.SortingTask;
import com.RESTendpointAPP.RESTendpoint.entity.TypeOfSorting;
import com.RESTendpointAPP.RESTendpoint.service.RatesService;
import com.RESTendpointAPP.RESTendpoint.service.SortingService;
import com.google.gson.Gson;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(Controller.class)
@Import({SortingService.class, RatesService.class,RestTemplate.class})
//@Import(FileGenerationService.class)
//@SpringBootTest
//@ContextConfiguration(classes = {GeneratorController.class,ExceptionAdvice.class})
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    Controller controller;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    Gson gson;


    @Test
    public  void controllerIsNotNull(){

        assertThat(controller).isNotNull();
    }

    @Test
    public void returnPing() throws Exception {
        var response =  mockMvc.perform(get("/status/ping"))
                .andReturn()
                .getResponse();
        assertThat(response.getContentAsString()).isEqualTo("Ping");
    }

    @Test
    public void returnSortedListAscending() throws Exception {
        var request = new SortingTask("1,2,3", TypeOfSorting.ASC);

        var response = mockMvc.perform(post("/numbers/sort-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.*", Matchers.equalTo(List.of(1,2,3))));
    }
    @Test
    public void returnSortedListDescending() throws Exception {
        var request = new SortingTask("1,2,3", TypeOfSorting.DESC);

        var response = mockMvc.perform(post("/numbers/sort-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.*", Matchers.equalTo(List.of(3,2,1))));
    }

    @Test
    public void getAllRatesTest() throws Exception {
        var response = mockMvc.perform(get("/nbp_rates/all"))
                .andDo(print())
                .andExpect(jsonPath("$.*",hasSize(34)));
    }

    @Test
    public void getCurrentCurrencyValueTest() throws Exception {
        var request = new RequestedCurrency("EUR");
        var response = mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andDo(print())
                .andReturn()
                .getResponse();
        assertThat(response.getContentAsString()).isEqualTo("4.685");

        }
    }
