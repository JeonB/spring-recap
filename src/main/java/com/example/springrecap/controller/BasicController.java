package com.example.springrecap.controller;

import com.example.springrecap.entity.Item;
import com.example.springrecap.entity.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final ItemRepository itemRepository;

    /* lombok 쓰기 싫으면 생성자 자동 생성 ㄱㄱ */
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
//
    @GetMapping("/")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "list";
    }

    @GetMapping("/write")
    String write(){
        return "write";
    }

    @GetMapping("/detail/{id}")
    String detailPage(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다."));
        model.addAttribute("item", item);

        return "detail";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public String handleItemNotFoundException(IllegalArgumentException ex, RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
//        return "redirect:/";
//    }
}
