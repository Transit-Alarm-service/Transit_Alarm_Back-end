package com.transitAlarm.transitAlarm.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
@RequiredArgsConstructor
public class directionController {

    @GetMapping("/directions")
    public String getDirection() {

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        String result = restTemplate
                .postForObject("https://maps.googleapis.com/maps/api/directions/json?origin=37.36673247840618,127.1081966687801&destination=37.4514327,126.651520&mode=transit&departure_time=now&key=AIzaSyAkHtuxiAN8ib4dc4uLfTBrLwH4tThCIfc",
                        map, String.class);

        return result;

    }

    /**
     * V2
     * return route in korean
     */
    @GetMapping("/directions/kr")
    public ResponseEntity<String> getDirectionKr() {
        // request URL
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=37.36673247840618,127.1081966687801&destination=37.4514327,126.651520&mode=transit&departure_time=now&key=AIzaSyAkHtuxiAN8ib4dc4uLfTBrLwH4tThCIfc";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders httpHeaders = new HttpHeaders();

        // set 'Content-Type' and 'Accept' and 'Accept-Language' headers
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String language = "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7";
        httpHeaders.setAcceptLanguage(Locale.LanguageRange.parse(language));

        // build the request
        HttpEntity request = new HttpEntity(httpHeaders);

        // make an HTTP Get request with headers
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );
    }
}
