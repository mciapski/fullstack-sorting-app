package com.RESTendpointAPP.RESTendpoint.service;

import com.RESTendpointAPP.RESTendpoint.dto.RatesDto;
import com.RESTendpointAPP.RESTendpoint.entity.RequestedCurrency;
import com.RESTendpointAPP.RESTendpoint.exceptions.BadCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/*
1.
ResponseEntity<List<User>> responseEntity =
  restTemplate.exchange(
    BASE_URL,
    HttpMethod.GET,
    null,
    new ParameterizedTypeReference<List<User>>() {}
  );
List<User> users = responseEntity.getBody();
return users.stream()
  .map(User::getName)
  .collect(Collectors.toList());

 2.
 ResponseEntity<User[]> responseEntity =
    restTemplate.getForEntity(BASE_URL, User[].class);
  User[] userArray = responseEntity.getBody();
  return Arrays.stream(userArray)
    .map(User::getName)
    .collect(Collectors.toList());
 */
@Service
@RequiredArgsConstructor
public class RatesService {

    @Autowired
    private final RestTemplate restTemplate;

    public List<RatesDto.Rate> getAllRates() {
//        RatesDto result = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A?format=json",RatesDto.class);
        ResponseEntity<RatesDto[]> responseEntity =
                restTemplate.getForEntity("http://api.nbp.pl/api/exchangerates/tables/A?format=json", RatesDto[].class);
        RatesDto[] userArray = responseEntity.getBody();
        List<RatesDto.Rate> listOfRates = List.of(userArray).get(0).getListOfRates();
        return listOfRates;

    }

    public double getRate(RequestedCurrency requestedCurrency) {
        RatesDto.Rate mid = getAllRates().stream()
                .filter(x-> Objects.equals(x.getCode(), requestedCurrency.getCode()))
                .findFirst()
                .orElseThrow(()->new BadCodeException("Wrong CODE"));


        return mid.getMid();
    }
}
