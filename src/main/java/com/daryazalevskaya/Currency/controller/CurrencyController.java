package com.daryazalevskaya.Currency.controller;

import com.daryazalevskaya.Currency.model.entity.Currency;
import com.daryazalevskaya.Currency.repos.CurrencyRepos;
import com.daryazalevskaya.Currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepos currencyRepos;

    @GetMapping("/")
    public String showCurrencyTable(Model model) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<Currency> currencyList = new ArrayList<>();

        Date todayDate = new Date();
        todayDate = currencyService.getDate();
        model.addAttribute("todayDate", new SimpleDateFormat("dd.MM.yyyy").format(todayDate));

        if (currencyRepos.existsByCourseDate(todayDate)) {
            currencyList = currencyRepos.getAllByCourseDate(todayDate);
        } else {
            try {
                currencyList = currencyService.getCurrencyList("Valute");
                currencyRepos.saveAll(currencyList);
            } catch (ParserConfigurationException | SAXException | IOException | ParseException ex) {
                return "error-page";
            }
        }

        model.addAttribute("currencyList", currencyList);

        return "start-page";
    }
}
