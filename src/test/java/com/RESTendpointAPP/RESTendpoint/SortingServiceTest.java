package com.RESTendpointAPP.RESTendpoint;

import com.RESTendpointAPP.RESTendpoint.controller.Controller;
import com.RESTendpointAPP.RESTendpoint.entity.SortingTask;
import com.RESTendpointAPP.RESTendpoint.entity.TypeOfSorting;
import com.RESTendpointAPP.RESTendpoint.service.SortingService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SortingServiceTest {

    SortingService sortingService;

    @BeforeEach
    void setUp() {
        sortingService = new SortingService();
    }


    @Test
    void returnSortedListAscending() {
        // given
//        List<Integer> list = List.of(5, 4, 3, 2, 1);
        var request = new SortingTask("5,4,3,2,1", TypeOfSorting.ASC);
        // when
        List<Integer> resultList = sortingService.sort(request);
        List<Integer> expectedList = List.of(1, 2, 3, 4, 5);
        // then
        assertThat(resultList).isEqualTo(expectedList);
    }

    @Test
    void returnSortedListDescending() {
        // given
//        List<Integer> list = List.of(1, 2, 3, 4, 5);
        var request = new SortingTask("1,2,3,4,5", TypeOfSorting.DESC);
        // when
        List<Integer> resultList = sortingService.sort(request);
        List<Integer> expectedList = List.of(5, 4, 3, 2, 1);
        // then
        assertThat(resultList).isEqualTo(expectedList);
    }


}
