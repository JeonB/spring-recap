package com.example.springrecap.controller;

import com.example.springrecap.entity.Item;
import com.example.springrecap.entity.ItemRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    /* lombok 쓰기 싫으면 생성자 자동 생성 ㄱㄱ */
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
//
//    @GetMapping("/")
//    String list(Model model){
//        List<Item> result = itemRepository.findAll();
//        System.out.println(result);
//        model.addAttribute("items", result);
//        return "list";
//    }
//


    @GetMapping("/item/exists")
    public ResponseEntity<Boolean> existsByTitle(@RequestParam String title) {
        boolean exists = itemRepository.existsByTitle(title);
        return ResponseEntity.ok(exists);
    }
    @PostMapping("/item")
    void save(@RequestBody Item item){
//        String save(@RequestParam Map formData){
//        Map data = formData;
//
//        String title = (String) data.get("title");
//        Integer price = Integer.parseInt((String) formData.get("price"));
//
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
        if(item.getTitle() != null && item.getPrice() != null) System.out.println("item 저장 완료 : ->" + item);
        itemRepository.save(item);
    }
}
