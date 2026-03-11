# API E-commerce com Spring Boot, H2 e Docker

## 📌 Desafio DIO
API REST para gerenciamento de um e-commerce, desenvolvida com **Spring Boot**.  
O projeto utiliza **H2 Database** para persistência e **Docker Compose** para executar a aplicação em container.

Projeto desenvolvido como parte de um desafio prático da **Digital Innovation One (DIO)**.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- Swagger / OpenAPI
- Docker
- Docker Compose

---

## ▶️ Como executar o projeto

### 1️⃣ Gerar o jar

```bash
.\mvnw.cmd clean package -DskipTests
```

### 2️⃣ Subir com Docker
```bash
docker compose up --build
```
---
### 🔎 Acessos
Swagger:
```bash
http://localhost:8080/swagger-ui/index.html 
```
Console H2:
```bash
http://localhost:8080/h2-console 
```
### 🐳 Docker
O projeto utiliza Docker Compose para subir a aplicação em container.

```bash
docker compose up --build
```
---
## 👩‍💻 Autora

Projeto desenvolvido por **Lorrane Aló Cruz**, como parte do aprendizado em Docker e Spring Boot.
