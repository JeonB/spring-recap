package com.example.springrecap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/")
    String hello(){
        return "안녕하세요";
    }

}
