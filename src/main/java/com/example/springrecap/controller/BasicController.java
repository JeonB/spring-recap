package com.example.springrecap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
    @GetMapping("/")
    String hello(){
        return "index";
    }


    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "피싱사이트";
    }
}
