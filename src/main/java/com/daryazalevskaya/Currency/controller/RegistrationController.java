package com.daryazalevskaya.Currency.controller;

import com.daryazalevskaya.Currency.model.entity.Role;
import com.daryazalevskaya.Currency.model.entity.User;
import com.daryazalevskaya.Currency.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        User userFromDb = userRepos.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message_exists", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepos.save(user);
        return "redirect:/login";
    }
}
