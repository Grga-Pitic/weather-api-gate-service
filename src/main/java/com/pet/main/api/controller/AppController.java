package com.pet.main.api.controller;

import com.pet.main.api.service.client.base.WeatherClientType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {

        List<String> apiList = new ArrayList<String>();

        for (WeatherClientType type : WeatherClientType.values()) {
            apiList.add(type.toString());
        }

        model.addAttribute("apiList", apiList);

        return "index";
    }

}
