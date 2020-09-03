package com.daryazalevskaya.CurrencyConverter.controller;

import com.daryazalevskaya.CurrencyConverter.model.entity.Currency;
import com.daryazalevskaya.CurrencyConverter.repos.CurrencyRepos;
import com.daryazalevskaya.CurrencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepos currencyRepos;

    @GetMapping("/")
    public String showCurrencyTable(Model model) {
        List<Currency> currencyList = new ArrayList<>();

        try {
            Date todayDate = currencyService.getDate();
            model.addAttribute("todayDate", new SimpleDateFormat("dd.MM.yyyy").format(todayDate));

            if (currencyRepos.existsByCourseDate(todayDate)) {
                currencyRepos.deleteAllByCourseDate(todayDate);
            }

            currencyService.setCurrencyList("Valute");
            currencyList = currencyService.getCurrencyList();
            currencyRepos.saveAll(currencyList);


        } catch (ParserConfigurationException | SAXException | IOException | ParseException ex) {
            return "error-page";
        }

        model.addAttribute("currencyList", currencyList);
        return "start-page";
    }

    @GetMapping("/login")
    public String saveCurrencyToDb() {
        List<Currency> currencies = currencyService.getCurrencyList();
        if (currencies.size() == 0) {
            try {
                currencyService.setCurrencyList("Valute");
                currencyRepos.saveAll(currencyService.getCurrencyList());
            } catch (Exception e) {
                return "error-page";
            }
        }

        return "login";
    }
}
