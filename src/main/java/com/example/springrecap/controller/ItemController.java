package com.example.springrecap.controller;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {


    @GetMapping("/list")
    String list(Model model){
        String[] items = {"apple", "banana", "kiwi"};
        model.addAttribute("items", String.join(", ", items).replace("[", "").replace("]", ""));
        return "list";
    }
}
