package com.panierfute.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "alarmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public enum TypeAlarme {
        APPROCHE,
        DEPASSEMENT
    }

    public enum Canal {
        NOTIFICATION,
        EMAIL,
        LES_DEUX
    }
}