package com.daryazalevskaya.CurrencyConverter.controller;

import com.daryazalevskaya.CurrencyConverter.model.entity.Currency;
import com.daryazalevskaya.CurrencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ConversionRestController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/currencyValues")
    public Map<String, Double> getCurrencyValues() throws SAXException, ParserConfigurationException, ParseException, IOException {
        List<Currency> currencyList = currencyService.getCurrencyList("Valute");
        return currencyList.stream().collect(Collectors.toMap(Currency::getCharCode, currency -> (currency.getNominal() * currency.getValue())));

    }
}
