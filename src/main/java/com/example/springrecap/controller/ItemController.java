package com.example.springrecap.controller;

import com.example.springrecap.entity.Item;
import com.example.springrecap.entity.ItemRepository;
import com.example.springrecap.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/")
    String list(Model model){
        List<Item> result = itemService.getALLItems();
        model.addAttribute("items", result);
        return "list";
    }

    @GetMapping("/write")
    String write(){
        return "write";
    }

    @GetMapping("/detail/{id}")
    String detailPage(@PathVariable Long id, Model model) throws Exception {
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);

        return "detail";
    }

    @PostMapping("/item")
    String save(@ModelAttribute Item item){
        if(item.getTitle() != null && item.getPrice() != null) {
            System.out.println("item 저장 완료 : ->" + item);
            itemService.saveItem(item);
        }else{
            System.out.println("item 저장 실패 : ->" + item);
        }
        return "redirect:/";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price){
        itemService.saveItemByTitlePrice(title, price);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(itemService.isPresentItemById(id)){
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
