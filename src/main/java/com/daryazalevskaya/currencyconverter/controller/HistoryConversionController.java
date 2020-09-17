package com.daryazalevskaya.currencyconverter.controller;

import com.daryazalevskaya.currencyconverter.model.Conversion;
import com.daryazalevskaya.currencyconverter.model.entity.User;
import com.daryazalevskaya.currencyconverter.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryConversionController {

    @Autowired
    private UserRepos userRepos;

    @GetMapping
    public String showConversionHistory(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userRepos.findByUsername(username);
        List<Conversion> conversionHistory = loggedUser.getConversionHistory();
        model.addAttribute("conversionHistory", conversionHistory);
        return "conversion-history";
    }
}
