package edu.mum.cs.cs425.pcrepairtrackingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/pcrepair", "/pcrepair/public/home", "/pcrepair/home"})
    public String home() {
        return "home/index";
    }

    @GetMapping(value = {"/secured/home","/pcrepair/secured/home"})
    public String home2() {
        return "secured/index";
    }
}