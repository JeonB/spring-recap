package com.example.springrecap.service;

import com.example.springrecap.entity.Item;
import com.example.springrecap.entity.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItemByTitlePrice(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

    public Item getItem(Long id){
       return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다."));
    }
    public List<Item> getALLItems(){
        return itemRepository.findAll();
    }
    public boolean isPresentItemById(Long id){
        return itemRepository.existsById(id);
    }

}
