package com.example.crud;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<Item> getItem(Long itemId);
    List<Item> fetchItemList();
    Item addItem(Item item);
    void deleteItem(Item item);
    Item updateItem(Item item);
}
