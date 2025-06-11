
# 🐾 CatFeed App

CatFeed is a modern Android application built with **Kotlin**, **Jetpack Compose**, **Hilt**, **MVI**, and **Clean Architecture**. It fetches and displays cat breeds from a remote API, with features such as favoriting, offline caching, and pagination.

---

## 🧱 Project Architecture

The app follows **Clean Architecture** and **Modular Separation of Concerns**, inspired by industry best practices (e.g., Now in Android, Google Samples).

```
com.mehdizadeh.catfeed
├── core/
│   ├── Base classes (ViewModel, Action, Event, State)
│   ├── Result wrapper
│
├── data/
│   ├── mapper/         → Converts DTOs to domain models
│   ├── model/          → Remote & local DTOs
│   ├── repository/     → Interface implementations (data layer)
│   ├── dataBase/       → Room DB & DAOs
│   ├── dataStore/      → ProtoDataStore or Preferences storage
│   ├── dispatcher/     → Coroutine dispatcher provider
│   ├── networking/
│   │   ├── ApiServices → Retrofit service interfaces
│   │   └── RepositoryModule → Provides repos and Retrofit clients via Hilt
│
├── domain/
│   ├── model/          → Business-layer models
│   ├── repository/     → Interfaces for business logic
│   ├── useCase/        → Business rules in use case classes
│
├── navigation/         → App-wide navigation logic (Screen abstraction, manager)
│
├── provider/           → Helper providers (e.g., context, connectivity)
│
├── screen/             → Feature presentation logic
│   ├── presentation/   → UI logic (ViewModel, state, events)
│   ├── activity/       → MainActivity, NavHost, App entry
│   └── ui/             → Composable screens and components
│
├── theme/              → Jetpack Compose Material3 theming
├── utils/              → Utility classes and functions
├── CatBreedApplication.kt → Application class
```

---

## ⚙️ Tech Stack

| Layer            | Tools Used                                      |
|------------------|--------------------------------------------------|
| UI               | Jetpack Compose, Material3                      |
| Architecture     | Clean Architecture, MVI                        |
| DI               | Hilt                                            |
| Networking       | Retrofit + OkHttp + Interceptors               |
| Local Storage    | Room, DataStore                                 |
| Pagination       | Manual paging or Jetpack Paging (optional)     |
| State Handling   | Kotlin Flow, sealed interfaces for MVI         |
| Testing          | JUnit, MockK, Compose UI tests (planned)       |

---

## 🚀 Features

- 🐱 Browse all cat breeds
- 💖 Favorite/unfavorite breeds
- 🔄 Pull-to-refresh
- ⚡ Fast and responsive UI with Compose
- 🌐 Clean network/data/domain separation
- 📦 Retrofit caching with selective `@Headers`
- 🔐 Fully testable and scalable architecture

---

## 🔌 Module Responsibilities

| Folder                 | Responsibility |
|------------------------|----------------|
| `core/`                | Base building blocks used across layers |
| `data/`                | Implementation of repositories, API & DB |
| `domain/`              | Business logic: use cases & domain models |
| `screen/`              | ViewModels and Composables per feature |
| `navigation/`          | Dynamic route handling and `NavigationManager` |
| `theme/`               | Reusable design system tokens |
| `utils/`               | Generic helpers and extensions |
| `provider/`            | Reusable system context & managers (like `ConnectivityProvider`) |

---

## 🧪 TODO (Planned)

- Add UI Tests with Compose
- Add Unit Tests for ViewModels and UseCases
- Offline-first support with Room
- Fully abstract `NavigationManager` and make `startDestination` dynamic
- Multi-flavor support (`free`, `pro`)

---

## 🧩 Code Style & Conventions

- ✅ Kotlin DSLs and type-safe builders
- ✅ Jetpack Compose with proper unidirectional data flow (UDF)
- ✅ Sealed classes for MVI Actions, Events, and States
- ✅ Repository Pattern
- ✅ Consistent dependency injection via `@Provides` & `@Binds`

---

## 📁 Getting Started

1. Clone the repo
2. Add your API key to `local.properties`
3. Run the app!
