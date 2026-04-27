# 🛒 Panier Futé

> Gérez vos listes de courses intelligemment — budget, dépenses, historique et suggestions via Open Food Facts.

![Status](https://img.shields.io/badge/status-en%20développement-orange)
![Stack](https://img.shields.io/badge/stack-Next.js%20%7C%20TypeScript%20%7C%20Prisma-blue)

---

## 🎯 Concept

Panier Futé est une application de gestion de courses et de budget alimentaire. L'utilisateur crée des listes, suit ses dépenses, visualise ses tendances mensuelles et bénéficie de l'auto-complétion des produits via l'API Open Food Facts.

---

## ✨ Fonctionnalités

- 📋 Création et gestion de listes de courses
- 💰 Suivi de budget par liste et par mois
- 📊 Graphiques de dépenses (Recharts)
- 🔍 Auto-complétion produits (Open Food Facts)
- ⭐ Gestion des favoris
- 📁 Archivage des listes
- 🔐 Authentification sécurisée (JWT)

---

## 🛠️ Stack technique

| Couche | Technologie |
|--------|-------------|
| Framework | Next.js 14 (App Router) |
| Langage | TypeScript |
| UI | React + Tailwind CSS |
| State | Zustand |
| Animations | Framer Motion |
| Graphiques | Recharts |
| ORM | Prisma |
| Base de données | PostgreSQL (Neon) |
| Auth | JWT + bcrypt + cookies httpOnly |
| API externe | Open Food Facts |
| Déploiement | Vercel |

---

## 🚀 Installation

```bash
git clone https://github.com/TON_USERNAME/panier-fute.git
cd panier-fute

npm install

cp .env.example .env.local
# → renseigner DATABASE_URL + JWT_SECRET

npx prisma db push

npm run dev
```

---

## 📁 Structure du projet

```
src/
├── app/
│   ├── api/
│   │   ├── auth/         # login, register, logout
│   │   ├── lists/        # CRUD listes
│   │   ├── products/     # CRUD produits
│   │   ├── budgets/      # gestion budgets
│   │   └── favorites/    # favoris
│   ├── dashboard/
│   ├── lists/
│   └── page.tsx
├── components/
│   ├── ui/
│   ├── layout/
│   └── charts/
├── lib/
│   ├── db.ts
│   └── auth.ts           # helpers JWT
├── store/                # Zustand stores
├── types/
└── hooks/
prisma/
└── schema.prisma
```

---

## 🗄️ Modèles de données

```prisma
model User {
  id        String   @id @default(cuid())
  email     String   @unique
  password  String   # bcrypt hash
  lists     List[]
  favorites Favorite[]
}

model List {
  id        String    @id @default(cuid())
  name      String
  budget    Float?
  archived  Boolean   @default(false)
  createdAt DateTime  @default(now())
  user      User      @relation(fields: [userId], references: [id])
  userId    String
  products  Product[]
}

model Product {
  id       String  @id @default(cuid())
  name     String
  price    Float?
  quantity Int     @default(1)
  checked  Boolean @default(false)
  list     List    @relation(fields: [listId], references: [id])
  listId   String
}
```

---

## 📡 API Routes

| Méthode | Route | Description |
|---------|-------|-------------|
| POST | `/api/auth/register` | Inscription |
| POST | `/api/auth/login` | Connexion |
| GET | `/api/lists` | Listes de l'utilisateur |
| POST | `/api/lists` | Créer une liste |
| PUT | `/api/lists/:id` | Modifier une liste |
| DELETE | `/api/lists/:id` | Supprimer / archiver |
| GET | `/api/products` | Produits d'une liste |
| POST | `/api/products` | Ajouter un produit |

---

## 👤 Auteur

Développé par **[TON NOM]** — projet de formation développeur web fullstack.
