package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(@Valid @RequestBody Item item) {
        itemService.addItem(item);
        //return itemService.addItem(item);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Item>> fetchItemList() {
        //return itemService.fetchItemList();
        List<Item> items = itemService.fetchItemList();
        //return ResponseEntity.status(HttpStatus.OK).body(items);
        return ResponseEntity.ok(items);
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
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable("id") Long id) {
        /*
        Using GlobalExceptionHandler is cleaner than this approach
        Optional<Item> item= itemService.getItem(id);
        if (item.isPresent()) {//
                itemService.deleteItem(item.get());
                return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }*/
        Optional<Item> item= itemService.getItem(id);
        itemService.deleteItem(item.orElseThrow(()->new ItemNotFoundException("Item Not Found")));
        return ResponseEntity.noContent().build();
    }

}
