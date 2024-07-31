package com.example.springrecap.controller;

import com.example.springrecap.entity.Announcement;
import com.example.springrecap.entity.AnnouncementRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementRepository announcementRepository;

    @GetMapping("/announcement")
    String announce(Model model){
        List<Announcement> result = announcementRepository.findAll();
        model.addAttribute("items", result);
        return "announcement";
    }
    
}
