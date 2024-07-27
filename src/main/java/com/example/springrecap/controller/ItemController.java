package com.example.springrecap.controller;

import com.example.springrecap.entity.Item;
import com.example.springrecap.entity.ItemRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    /* lombok 쓰기 싫으면 생성자 자동 생성 ㄱㄱ */
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        System.out.println(result);
        model.addAttribute("items", result);
        return "list";
    }

    @GetMapping("/write")
    String write(){
        return "write";
    }

    @PostMapping("/item")
    String save(@ModelAttribute Item item){
//        String save(@RequestParam Map formData){
//        Map data = formData;
//
//        String title = (String) data.get("title");
//        Integer price = Integer.parseInt((String) formData.get("price"));
//
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
        itemRepository.save(item);
        return "redirect:/";
    }
}
