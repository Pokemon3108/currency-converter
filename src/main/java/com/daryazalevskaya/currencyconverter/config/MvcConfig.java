package com.daryazalevskaya.currencyconverter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("start-page");
        registry.addViewController("/homePage").setViewName("home-page");
        registry.addViewController("/login").setViewName("login");
    }

}
