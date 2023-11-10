package com.github.osvaldsoza.brasilapiresttemplate.controller;

import com.github.osvaldsoza.brasilapiresttemplate.models.Bank;
import com.github.osvaldsoza.brasilapiresttemplate.models.Holliday;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("external-api/v1")
public class BrasilApiController {

    @Value("${brasil.api.banks}")
    private String brasilApiBanksUrl;

    @Value("${brasil.api.hollidays}")
    private String brasilApiHollidaysUrl;

    @GetMapping("banks")
    public List<Bank> listBanks(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Bank[]> response = restTemplate.getForEntity(brasilApiBanksUrl, Bank[].class);

        return Arrays.asList(response.getBody());
    }

    @GetMapping("hollidays/{year}")
    public List<Holliday> listHollidays(@PathVariable("year") int year){
        String url = brasilApiHollidaysUrl + year;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Holliday[]> response = restTemplate.getForEntity(url, Holliday[].class);

        return Arrays.asList(response.getBody());
    }
}
