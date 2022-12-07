package com.RESTendpointAPP.RESTendpoint.dto;
/*
"rates": [
      {
        "currency": "bat (Tajlandia)",
        "code": "THB",
        "mid": 0.1280
      },
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class RatesDto {
    @JsonProperty("table")
    private String table;
    @JsonProperty("no")
    private String no;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("rates")
    private List<Rate> listOfRates;

    @NoArgsConstructor
    @Data
    public static class Rate {
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("code")
        private String code;
        @JsonProperty("mid")
        private double mid;

    }
}
