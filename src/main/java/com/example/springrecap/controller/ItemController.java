package com.example.springrecap.controller;

import com.example.springrecap.entity.Item;
import com.example.springrecap.entity.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    /* lombok 쓰기 싫으면 생성자 자동 생성 ㄱㄱ */
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        StringBuilder titles = new StringBuilder();
        for(Item item : result){
            titles.append(item.title).append(" ");
        }
        model.addAttribute("items", titles.toString());
        return "list";
    }
}
