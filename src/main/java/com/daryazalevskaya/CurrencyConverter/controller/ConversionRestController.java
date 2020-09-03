package com.daryazalevskaya.CurrencyConverter.controller;

import com.daryazalevskaya.CurrencyConverter.model.entity.Currency;
import com.daryazalevskaya.CurrencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ConversionRestController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currencyValues")
    public Map<String, Double> getCurrencyValues() {
        List<Currency> currencyList = currencyService.getCurrencyList();
        return currencyList.stream().collect(Collectors.toMap(Currency::getCharCode, currency -> (currency.getNominal() * currency.getValue())));
    }
}
