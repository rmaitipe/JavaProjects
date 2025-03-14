package crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Optional<Item> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }
    /*
    @Override
    public ResponseEntity<?> getItem(Long id) {
        Optional<Item> student = itemRepository.findById(id);
        if(student.isPresent()) {
            return new ResponseEntity<>(student,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    */
/*
It is not required to use Optional with List<T> since Spring Data will return an empty list if nothing found.
*/
    @Override
    public List<Item> fetchItemList() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
    /*
    @Override
    public ResponseEntity<?> addItem(Item item) {
        itemRepository.save(item);
        Optional<Item> newItem =  itemRepository.findById(item.getId());
        if(newItem.isPresent()){
            return new ResponseEntity<>(newItem.get(),HttpStatus.CREATED);
        }
        ErrorSource errorSource = new ErrorSource("ERR_IN_USER_CREATION","Error has occurred while creating the Student");
        return new ResponseEntity<>(errorSource,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    */

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
    /*
    @Override
    public ResponseEntity<?> deleteItem(Long id){
        Optional<Item> student = itemRepository.findById(id);
        if (student.isPresent()){
            try {
                itemRepository.deleteById(id);
                return new ResponseEntity<>(null,HttpStatus.OK);
            } catch(Exception exception){
                ErrorSource errorSource = new ErrorSource("ERR_IN_RESOURCE_DELETION",exception.getMessage());
                return new ResponseEntity<>(errorSource,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    */
    @Override
    public Item updateItem(Item item) {
        Item itemPrior= itemRepository.findById(item.getItemId()).get();
        //Update & Save
        itemPrior.setName(item.getName());
        return itemRepository.save(itemPrior);
    }
    /*
    @Override
    public ResponseEntity<?> updateItem(Long id){
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()){
            try {
                //updatehere
                itemRepository.save(item);
                Optional<Item> newItem =  itemRepository.findById(item.getId());
                if(newItem.isPresent()){
                    return new ResponseEntity<>(newItem.get(),HttpStatus.CREATED);
                }
                return new ResponseEntity<>(null,HttpStatus.OK);
            } catch(Exception exception){
                ErrorSource errorSource = new ErrorSource("ERR_IN_RESOURCE_DELETION",exception.getMessage());
                return new ResponseEntity<>(errorSource,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    */
}

