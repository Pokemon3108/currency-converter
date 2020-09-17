package com.daryazalevskaya.currencyconverter.controller;

import com.daryazalevskaya.currencyconverter.model.entity.Currency;
import com.daryazalevskaya.currencyconverter.repos.CurrencyRepos;
import com.daryazalevskaya.currencyconverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<Currency> currencyList;

        try {
            Document document = currencyService.getDocument();
            Date todayDate = currencyService.getDate(document);
            model.addAttribute("todayDate", new SimpleDateFormat("dd.MM.yyyy").format(todayDate));

            if (currencyRepos.existsByCourseDate(todayDate)) {
                currencyRepos.deleteAllByCourseDate(todayDate);
            }

            currencyService.setCurrencyList(document, "Valute");
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
        if (currencies.isEmpty()) {
            try {
                Document document = currencyService.getDocument();
                currencyService.setCurrencyList(document, "Valute");
                currencyRepos.saveAll(currencyService.getCurrencyList());
            } catch (Exception e) {
                return "error-page";
            }
        }

        return "login";
    }
}
