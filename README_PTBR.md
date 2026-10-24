# 💸 CashTrailApp

[![English](https://img.shields.io/badge/lang-en-blue)](README.md)

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen)
![GitHub last commit](https://img.shields.io/github/last-commit/LuizEduardoMarchi/CashTrailApp)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![License](https://img.shields.io/badge/license-MIT-green)

> Um controle de gastos pessoais: API REST (Java + Spring Boot) e um frontend em HTML/CSS/JS puro.
> Criado como projeto de estudo para praticar arquitetura em camadas, design REST, banco relacional, autenticação e um fluxo Git profissional.

## 📚 Sumário

- 📌 [Sobre o projeto](#-sobre-o-projeto)
- ⚙️ [Funcionalidades](#️-funcionalidades)
- 🧰 [Tecnologias](#-tecnologias-utilizadas)
- 📁 [Estrutura do projeto](#-estrutura-do-projeto)
- 🧪 [Como executar](#-como-executar-o-projeto)
- 🔌 [Endpoints da API](#-endpoints-da-api)
- ⚙️ [Como o sistema funciona](#️-como-o-sistema-funciona)
- 🗺️ [Roadmap](#️-roadmap)
- 🤝 [Contribuições](#-contribuições)
- 📊 [Status do projeto](#-status-do-projeto)
- 📜 [Licença](#-licença)
- 👤 [Autor](#-autor)

## 📌 Sobre o projeto

O CashTrailApp permite registrar despesas, organizá-las por categoria e ver quanto foi gasto, no total ou em um mês específico.

O objetivo principal é **aprender**: o projeto é desenvolvido em fases, cada uma adicionando uma camada de um backend real (CRUD em memória → banco de dados → relatórios → frontend → autenticação).

## ⚙️ Funcionalidades

- [x] Criar e listar categorias
- [ ] Criar, listar, editar e excluir despesas *(em andamento)*
- [ ] Validação de entrada com mensagens claras em português
- [ ] Tratamento centralizado de erros (400 / 404 / 500) *(400 e 404 prontos)*
- [ ] Total gasto
- [ ] Relatório mensal (`?mes=YYYY-MM`, opcionalmente agrupado por categoria)
- [ ] Persistência com PostgreSQL
- [ ] Frontend web
- [ ] Cadastro, login e despesas por usuário (JWT)

## 🧰 Tecnologias utilizadas

- Java 21 (LTS)
- Spring Boot 4.1.1 (Web MVC, Bean Validation)
- Maven
- Git e GitHub
- PostgreSQL + Spring Data JPA *(planejado, Fase 2)*
- HTML, CSS e JavaScript *(planejado, Fase 4)*
- Spring Security + JWT *(planejado, Fase 5)*

## 📁 Estrutura do projeto

O projeto usa arquitetura **package by layer** (pacotes por camada):

```
CashTrailApp/
├── backend/
│   ├── src/main/java/com/luizdev/cashtrail/
│   │   ├── CashTrailAppApplication.java
│   │   ├── controller/   # camada HTTP: recebe requisições e devolve respostas
│   │   ├── service/      # regras de negócio
│   │   ├── repository/   # acesso a dados (em memória por enquanto, banco na Fase 2)
│   │   ├── model/        # classes de domínio (Category, Expense)
│   │   ├── dto/          # objetos de entrada/saída (records)
│   │   └── exception/    # exceções customizadas e handler global de erros
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── src/test/java/
│   └── pom.xml
└── frontend/             # planejado, Fase 4
```

Regra de dependência: `Controller → Service → Repository`. O controller nunca fala direto com o repository.

## 🧪 Como executar o projeto

**Requisitos**

- Java 21
- Maven 3.9+

**Passos**

```bash
git clone https://github.com/LuizEduardoMarchi/CashTrailApp.git
cd CashTrailApp/backend
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

> ⚠️ Na fase atual os dados ficam em memória, então tudo se perde quando a aplicação para.

## 🔌 Endpoints da API

| Método | Rota | Descrição | Status |
|--------|------|-----------|--------|
| POST | `/categories` | Cria uma categoria | ✅ Pronto |
| GET | `/categories` | Lista as categorias | ✅ Pronto |
| POST | `/expenses` | Cria uma despesa | 🚧 Planejado |
| GET | `/expenses` | Lista as despesas | 🚧 Planejado |
| GET | `/expenses/{id}` | Busca uma despesa | 🚧 Planejado |
| PUT | `/expenses/{id}` | Atualiza uma despesa | 🚧 Planejado |
| DELETE | `/expenses/{id}` | Exclui uma despesa | 🚧 Planejado |

**Exemplo**

```http
POST /categories
Content-Type: application/json

{ "name": "Alimentação" }
```

Resposta `201 Created`:

```json
{ "id": 1, "name": "Alimentação" }
```

Erro de validação `400 Bad Request`:

```json
{ "status": 400, "message": "Nome é obrigatório" }
```

## ⚙️ Como o sistema funciona

A requisição entra por um **Controller**, que valida o corpo (`@Valid`) e delega para um **Service**. O Service aplica as regras de negócio e usa um **Repository** para ler ou gravar dados. Os erros lançados em qualquer ponto são capturados por um único `GlobalExceptionHandler`, que sempre responde no mesmo formato JSON (`status` + `message`).

Decisões de projeto:

- Dinheiro é `BigDecimal`, nunca `double`.
- Datas usam `LocalDate`.
- O código é escrito em inglês; as mensagens exibidas ao usuário são em português.
- DTOs são `record`s; entidades nunca são expostas diretamente pela API.

## 🗺️ Roadmap

| Fase | Objetivo | Status |
|------|----------|--------|
| 1 | CRUD em memória (categorias e despesas), validação, status codes | 🚧 Em andamento |
| 2 | Persistência com PostgreSQL e JPA | ⏳ Planejado |
| 3 | Relatório mensal | ⏳ Planejado |
| 4 | Frontend (HTML + CSS + JS) | ⏳ Planejado |
| 5 | Autenticação JWT | ⏳ Planejado |

## 🤝 Contribuições

É um projeto de estudo pessoal, mas sugestões são bem-vindas. Fique à vontade para abrir uma issue ou um pull request.

Fluxo usado no repositório: trabalho na `dev`, commits pequenos seguindo Conventional Commits (ex.: `feat: add CategoryService`) e Pull Request de `dev` para `main`.

## 📊 Status do projeto

🚧 Em desenvolvimento (Fase 1)

## 📜 Licença

Este projeto está licenciado sob a Licença MIT.

## 👤 Autor

Luiz Eduardo Marchi
