package com.panierfute.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alarmes")
public class Alarme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seuil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAlarme type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Canal canal;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum TypeAlarme { APPROCHE, DEPASSEMENT }
    public enum Canal { NOTIFICATION, EMAIL, LES_DEUX }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getSeuil() { return seuil; }
    public void setSeuil(Integer seuil) { this.seuil = seuil; }
    public TypeAlarme getType() { return type; }
    public void setType(TypeAlarme type) { this.type = type; }
    public Canal getCanal() { return canal; }
    public void setCanal(Canal canal) { this.canal = canal; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}