package com.panierfute.controller;

import com.panierfute.model.Budget;
import com.panierfute.repository.BudgetRepository;
import com.panierfute.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public BudgetController(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Budget> getBudgets(Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).orElseThrow();
        return budgetRepository.findByUserId(user.getId());
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget, Authentication auth) {
        var user = userRepository.findByEmail(auth.getName()).orElseThrow();
        budget.setUser(user);
        return ResponseEntity.ok(budgetRepository.save(budget));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget updated) {
        var budget = budgetRepository.findById(id).orElseThrow();
        budget.setAmount(updated.getAmount());
        budget.setSpent(updated.getSpent());
        budget.setType(updated.getType());
        return ResponseEntity.ok(budgetRepository.save(budget));
    }
}
