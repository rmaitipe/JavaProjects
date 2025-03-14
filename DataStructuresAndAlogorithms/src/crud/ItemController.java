package crud;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
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
    public ResponseEntity<Item> fetchItemById(@PathVariable Long itemId) {
        Item item= itemService.getItem(itemId).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@RequestBody Item item, @PathVariable("id") Long itemId) {
        Item itemOld= itemService.getItem(itemId).orElseThrow(()->new ItemNotFoundException("Item Not Found"));
        itemOld.setDesc(item.getDesc());
        itemService.updateItem(itemOld);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable("id") Long itemId) {
        Item item= itemService.getItem(itemId).orElseThrow(()-> new ItemNotFoundException("Item not found"));
        itemService.deleteItem(item);
    }
}
