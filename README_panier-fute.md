# 🛒 Panier Futé

> Gérez vos listes de courses intelligemment — budget, dépenses, historique et suggestions via Open Food Facts.

![Status](https://img.shields.io/badge/status-en%20développement-orange)
![Stack](https://img.shields.io/badge/stack-Angular%20%7C%20Java%20Spring%20Boot%20%7C%20PostgreSQL-blue)

---

## 🎯 Concept

Panier Futé est une application fullstack de gestion de courses et de budget alimentaire. L'utilisateur crée des listes, suit ses dépenses, visualise ses tendances mensuelles et bénéficie de l'auto-complétion des produits via l'API Open Food Facts.

---

## ✨ Fonctionnalités

- 📋 Création et gestion de listes de courses
- 💰 Suivi de budget par liste et par mois
- 📊 Graphiques de dépenses
- 🔍 Auto-complétion produits (Open Food Facts)
- ⭐ Gestion des favoris
- 📁 Archivage des listes
- 🔐 Authentification sécurisée (JWT)

---

## 🛠️ Stack technique

### 🖥️ Frontend
| Couche | Technologie |
|--------|-------------|
| Framework | Angular 21 |
| Langage | TypeScript |
| Styles | SCSS |
| Routing | Angular Router |
| HTTP | HttpClient |

### ⚙️ Backend
| Couche | Technologie |
|--------|-------------|
| Framework | Java Spring Boot |
| Langage | Java 21 |
| Build | Maven |
| ORM | Spring Data JPA / Hibernate |
| Base de données | PostgreSQL |
| Auth | JWT + bcrypt |
| API externe | Open Food Facts |

---

## 📁 Structure du projet

```
panier-fute/
├── frontend/                   # Application Angular
│   └── src/
│       ├── app/
│       │   ├── components/     # Composants UI
│       │   ├── services/       # Services HTTP
│       │   ├── models/         # Interfaces TypeScript
│       │   ├── guards/         # Auth guards
│       │   └── app.routes.ts   # Routing
│       └── styles.scss
│
└── backend/                    # API Java Spring Boot
    └── src/main/java/com/panierfute/
        ├── controller/         # Endpoints REST
        ├── service/            # Logique métier
        ├── repository/         # Accès base de données
        ├── model/              # Entités JPA
        └── security/           # JWT + config sécurité
```

---

## 🚀 Installation

### Prérequis
- Node.js 18+
- Angular CLI 21+
- Java 21+
- Maven 3.9+
- PostgreSQL

### Frontend
```bash
cd frontend
npm install
ng serve
```
Ouvrir [http://localhost:4200](http://localhost:4200)

### Backend
```bash
cd backend/backend
# Configurer src/main/resources/application.properties
# → renseigner spring.datasource.url, username, password, jwt.secret
mvn spring-boot:run
```
API disponible sur [http://localhost:8080](http://localhost:8080)

---

## 🗄️ Modèles de données

```java
// User.java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password; // bcrypt
    @OneToMany(mappedBy = "user")
    private List<ShoppingList> lists;
}

// ShoppingList.java
@Entity
public class ShoppingList {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double budget;
    private Boolean archived = false;
    private LocalDateTime createdAt;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "list")
    private List<Product> products;
}

// Product.java
@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Integer quantity = 1;
    private Boolean checked = false;
    @ManyToOne
    private ShoppingList list;
}
```

---

## 📡 API Endpoints

| Méthode | Route | Description |
|---------|-------|-------------|
| POST | `/api/auth/register` | Inscription |
| POST | `/api/auth/login` | Connexion |
| GET | `/api/lists` | Listes de l'utilisateur |
| POST | `/api/lists` | Créer une liste |
| PUT | `/api/lists/{id}` | Modifier une liste |
| DELETE | `/api/lists/{id}` | Supprimer / archiver |
| GET | `/api/lists/{id}/products` | Produits d'une liste |
| POST | `/api/products` | Ajouter un produit |
| PUT | `/api/products/{id}` | Modifier un produit |
| DELETE | `/api/products/{id}` | Supprimer un produit |

---

## 👤 Auteur

Développé par **Mariama** — projet de formation développeur web fullstack .
