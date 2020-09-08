package com.daryazalevskaya.CurrencyConverter.controller;

import com.daryazalevskaya.CurrencyConverter.model.Conversion;
import com.daryazalevskaya.CurrencyConverter.model.entity.User;
import com.daryazalevskaya.CurrencyConverter.repos.UserRepos;
import com.daryazalevskaya.CurrencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
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
        model.addAttribute("conversion", new Conversion());
        model.addAttribute("currencies_name", currencyService.getCurrencyList());
        return "convert";
    }

    @PostMapping("/saveConversion")
    public ModelAndView saveConversion(@ModelAttribute("conversion") Conversion conversion, Model model) {
        try {
            Document document = currencyService.getDocument();
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User loggedUser = userRepos.findByUsername(username);
            conversion.setDate(currencyService.getDate(document));
            loggedUser.getConversionHistory().add(conversion);
            userRepos.save(loggedUser);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println("Cannot parse info from website");
            return new ModelAndView("error");
        } catch (ParseException e) {
            System.out.println("Cannot parse data");
            return new ModelAndView("error");
        }


        // model.addAttribute("save_conversion", "Your conversion has been successfully saved!");
        return new ModelAndView("redirect:/converter", "success-save", true);
        // return "redirect:/converter";
        // return "convert";
    }
}
