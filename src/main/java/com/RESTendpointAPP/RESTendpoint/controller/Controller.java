package com.RESTendpointAPP.RESTendpoint.controller;

import com.RESTendpointAPP.RESTendpoint.dto.RatesDto;
import com.RESTendpointAPP.RESTendpoint.entity.RequestedCurrency;
import com.RESTendpointAPP.RESTendpoint.entity.SortingTask;
import com.RESTendpointAPP.RESTendpoint.service.RatesService;
import com.RESTendpointAPP.RESTendpoint.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class Controller {
    @Autowired
    SortingService sortingService;
    @Autowired
    RatesService ratesService;

    public List<Integer> resultList;

    @GetMapping("/status/ping")
    public String returnString(){

        return "Ping";
    }

    @PostMapping("/numbers/sort-command")
    public List<Integer> returnSortedListOfNumbers(@RequestBody SortingTask sortingTask){
        resultList =sortingService.sort(sortingTask);
        return resultList;
    }
    @GetMapping("/numbers/sort-command")
    public List<Integer> returnList(){

        return resultList;
    }
    @GetMapping("/nbp_rates/all")
    public List<RatesDto.Rate> getAllRates(){

        return ratesService.getAllRates();
    }
    @PostMapping("/currencies/get-current-currency-value-command")
    public Double getCurrentCurrencyValue(@RequestBody RequestedCurrency requestedCurrency){
        return ratesService.getRate(requestedCurrency);
    }
}
