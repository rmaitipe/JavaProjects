package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(@Valid @RequestBody Item item) {
        itemService.addItem(item);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Item> fetchItemList() {
        return itemService.fetchItemList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> fetchItemById(@PathVariable Long id) {
        Item item= itemService.getItem(id).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@RequestBody Item item, @PathVariable("id") final Long id) {
        Item itemOld= itemService.getItem(id).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        itemOld.setDesc(item.getDesc());
        itemService.updateItem(itemOld);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable("id") Long id) {
        Item item= itemService.getItem(id).orElseThrow(()-> new ItemNotFoundException("Item not found"));
        itemService.deleteItem(item);
    }

}
