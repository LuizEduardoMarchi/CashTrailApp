# 💸 CashTrailApp

[![Portuguese](https://img.shields.io/badge/lang-pt--br-green)](README_PTBR.md)

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen)
![GitHub last commit](https://img.shields.io/github/last-commit/LuizEduardoMarchi/CashTrailApp)
![Status](https://img.shields.io/badge/status-in%20development-yellow)
![License](https://img.shields.io/badge/license-MIT-green)

> A personal expense tracker: REST API (Java + Spring Boot) and a plain HTML/CSS/JS frontend.
> Built as a hands-on study project to practice layered architecture, REST design, relational databases, authentication and a professional Git workflow.

## 📚 Summary

- 📌 [About the project](#-about-the-project)
- ⚙️ [Features](#️-features)
- 🧰 [Technologies](#-technologies-used)
- 📁 [Project structure](#-project-structure)
- 🧪 [How to run](#-how-to-run-the-project)
- 🔌 [API endpoints](#-api-endpoints)
- ⚙️ [How it works](#️-how-the-system-works)
- 🗺️ [Roadmap](#️-roadmap)
- 🤝 [Contributions](#-contributions)
- 📊 [Project status](#-project-status)
- 📜 [License](#-license)
- 👤 [Author](#-author)

## 📌 About the project

CashTrailApp lets a person register their expenses, organize them by category and see how much they spent, in total or in a specific month.

The main goal is **learning**: the project is developed in phases, each one adding a layer of a real-world backend (in-memory CRUD → database → reports → frontend → authentication).

## ⚙️ Features

- [x] Create and list categories
- [ ] Create, list, edit and delete expenses *(in progress)*
- [ ] Input validation with clear error messages in Portuguese
- [ ] Centralized error handling (400 / 404 / 500) *(400 and 404 done)*
- [ ] Total amount spent
- [ ] Monthly report (`?month=YYYY-MM`, optionally grouped by category)
- [ ] PostgreSQL persistence
- [ ] Web frontend
- [ ] User registration, login and per-user expenses (JWT)

## 🧰 Technologies used

- Java 21 (LTS)
- Spring Boot 4.1.1 (Web MVC, Bean Validation)
- Maven
- Git and GitHub
- PostgreSQL + Spring Data JPA *(planned, Phase 2)*
- HTML, CSS and JavaScript *(planned, Phase 4)*
- Spring Security + JWT *(planned, Phase 5)*

## 📁 Project structure

The project uses a **package by layer** architecture:

```
CashTrailApp/
├── backend/
│   ├── src/main/java/com/luizdev/cashtrail/
│   │   ├── CashTrailAppApplication.java
│   │   ├── controller/   # HTTP layer: receives requests, returns responses
│   │   ├── service/      # business rules
│   │   ├── repository/   # data access (in-memory for now, database in Phase 2)
│   │   ├── model/        # domain classes (Category, Expense)
│   │   ├── dto/          # request/response objects (records)
│   │   └── exception/    # custom exceptions and global error handler
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── src/test/java/
│   └── pom.xml
└── frontend/             # planned, Phase 4
```

Dependency rule: `Controller → Service → Repository`. A controller never talks to a repository directly.

## 🧪 How to run the project

**Requirements**

- Java 21
- Maven 3.9+

**Steps**

```bash
git clone https://github.com/LuizEduardoMarchi/CashTrailApp.git
cd CashTrailApp/backend
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

> ⚠️ In the current phase data is stored in memory, so everything is lost when the application stops.

## 🔌 API endpoints

| Method | Route | Description | Status |
|--------|-------|-------------|--------|
| POST | `/categories` | Create a category | ✅ Done |
| GET | `/categories` | List categories | ✅ Done |
| POST | `/expenses` | Create an expense | 🚧 Planned |
| GET | `/expenses` | List expenses | 🚧 Planned |
| GET | `/expenses/{id}` | Get one expense | 🚧 Planned |
| PUT | `/expenses/{id}` | Update an expense | 🚧 Planned |
| DELETE | `/expenses/{id}` | Delete an expense | 🚧 Planned |

**Example**

```http
POST /categories
Content-Type: application/json

{ "name": "Food" }
```

Response `201 Created`:

```json
{ "id": 1, "name": "Food" }
```

Validation error `400 Bad Request`:

```json
{ "status": 400, "message": "Nome é obrigatório" }
```

## ⚙️ How the system works

A request enters through a **Controller**, which validates the body (`@Valid`) and delegates to a **Service**. The Service applies the business rules and uses a **Repository** to read or write data. Errors thrown anywhere are caught by a single `GlobalExceptionHandler`, which always answers with the same JSON format (`status` + `message`).

Design decisions:

- Money is stored as `BigDecimal`, never `double`.
- Dates use `LocalDate`.
- Code is written in English; messages shown to the user are in Portuguese.
- DTOs are Java `record`s; entities are never exposed directly by the API.

## 🗺️ Roadmap

| Phase | Goal | Status |
|-------|------|--------|
| 1 | In-memory CRUD (categories and expenses), validation, status codes | 🚧 In progress |
| 2 | PostgreSQL persistence with JPA | ⏳ Planned |
| 3 | Monthly report | ⏳ Planned |
| 4 | Frontend (HTML + CSS + JS) | ⏳ Planned |
| 5 | JWT authentication | ⏳ Planned |

## 🤝 Contributions

This is a personal study project, but suggestions are welcome. Feel free to open an issue or a pull request.

Workflow used in the repository: work on `dev`, small commits following Conventional Commits (e.g. `feat: add CategoryService`), and a Pull Request from `dev` into `main`.

## 📊 Project status

🚧 In development (Phase 1)

## 📜 License

This project is licensed under the MIT License.

## 👤 Author

Luiz Eduardo Marchi
