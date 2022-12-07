package com.RESTendpointAPP.RESTendpoint;

import com.RESTendpointAPP.RESTendpoint.dto.RatesDto;
import com.RESTendpointAPP.RESTendpoint.entity.RequestedCurrency;
import com.RESTendpointAPP.RESTendpoint.exceptions.BadCodeException;
import com.RESTendpointAPP.RESTendpoint.service.RatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.BadRequestException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RatesServiceTest {

    RatesService ratesService;
    RestTemplate restTemplate;

    @BeforeEach
    void setUp(){
        restTemplate = new RestTemplate();
        ratesService=new RatesService(restTemplate);
    }

    @Test
    void resultListIsNotEmpty(){
        // given
        List<RatesDto.Rate> testList = ratesService.getAllRates();
        // then
        assertThat(testList).isNotNull();
    }


    @Test
    void returnRequestedRate(){
        // given
        List<RatesDto.Rate> testList = ratesService.getAllRates();
        var requestedCurrency = new RequestedCurrency("EUR");
        // when
        double result = ratesService.getRate(requestedCurrency);
        double expected = 4.6850;
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void throwAnExceptionIfWrongCode(){
        // given
        List<RatesDto.Rate> testList = ratesService.getAllRates();
        var requestedCurrency = new RequestedCurrency("EURo");
        // expect
        assertThatThrownBy(()->ratesService.getRate(requestedCurrency))
                .isInstanceOf(BadCodeException.class)
                .hasMessage("Wrong CODE");
    }
}
