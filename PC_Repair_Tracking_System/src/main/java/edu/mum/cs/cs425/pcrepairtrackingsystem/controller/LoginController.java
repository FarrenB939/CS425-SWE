package edu.mum.cs.cs425.pcrepairtrackingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/public/login","/pcrepair/public/login"})
    public String login() {
        return "public/login";
    }

}
