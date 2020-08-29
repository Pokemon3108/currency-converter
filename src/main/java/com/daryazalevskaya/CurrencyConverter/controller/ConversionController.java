package com.daryazalevskaya.CurrencyConverter.controller;

import com.daryazalevskaya.CurrencyConverter.model.entity.User;
import com.daryazalevskaya.CurrencyConverter.repos.UserRepos;
import com.daryazalevskaya.CurrencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ConversionController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private UserRepos userRepos;

    @GetMapping("/converter")
    public String convert(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userRepos.findByUsername(username);

        try {
            model.addAttribute("currencies_name", currencyService.getCurrencyList("Valute"));
        } catch (IOException | SAXException | ParserConfigurationException | ParseException e) {
            return "error-page";
        }

        model.addAttribute("save_conversion", "Your conversion has been succesfully saved!");
        return "convert";
    }
}
