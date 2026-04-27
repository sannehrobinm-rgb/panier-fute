package com.panierfute.model;

import jakarta.persistence.*;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetType type;

    private Double spent = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum BudgetType { JOURNALIER, HEBDOMADAIRE, MENSUEL }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public BudgetType getType() { return type; }
    public void setType(BudgetType type) { this.type = type; }
    public Double getSpent() { return spent; }
    public void setSpent(Double spent) { this.spent = spent; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Double getRemaining() { return amount - spent; }
}