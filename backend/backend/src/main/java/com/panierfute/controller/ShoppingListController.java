package com.panierfute.controller;

import com.panierfute.model.ShoppingList;
import com.panierfute.repository.ShoppingListRepository;
import com.panierfute.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ShoppingListController {

    private final ShoppingListRepository listRepository;
    private final UserRepository userRepository;

    public ShoppingListController(ShoppingListRepository listRepository, UserRepository userRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<ShoppingList> getLists(Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).orElseThrow();
        return listRepository.findByUserId(user.getId());
    }

    @PostMapping
    public ResponseEntity<ShoppingList> createList(@RequestBody ShoppingList list, Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).orElseThrow();
        list.setUser(user);
        return ResponseEntity.ok(listRepository.save(list));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateList(@PathVariable Long id, @RequestBody ShoppingList updated, Authentication auth) {
        var list = listRepository.findById(id).orElseThrow();
        list.setName(updated.getName());
        list.setBudget(updated.getBudget());
        list.setArchived(updated.getArchived());
        return ResponseEntity.ok(listRepository.save(list));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id) {
        listRepository.deleteById(id);
        return ResponseEntity.ok("Liste supprimée");
    }
}
