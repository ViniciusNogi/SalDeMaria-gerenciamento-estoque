# Sal de Maria - API de Gerenciamento de Estoque

## 📌 Visão Geral

A **Sal de Maria** é uma API RESTful desenvolvida com **Spring Boot** para gerenciar o estoque de produtos da empresa. Inclui funcionalidades como cadastro, listagem, atualização e exclusão de produtos, além de autenticação via **JWT** e controle de acesso baseado em permissões.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **PostgreSQL**
- **HATEOAS**
- **Swagger/OpenAPI**

## 📂 Estrutura do Projeto

```
SalDeMaria
│── src/main/java/com/SalDeMaria/SalDeMaria
│   ├── controllers  # Controllers da API
│   ├── dtos         # DTOs para comunicação de dados
│   ├── infra        # Configurações de segurança e token
│   ├── model        # Entidades do banco de dados
│   ├── repositories # Interfaces JPA para acesso ao banco
│   ├── services     # Lógica de negócios (se necessário)
│── src/main/resources
│   ├── application.properties # Configurações do banco e JWT
```

## 🔐 Autenticação e Segurança

A API utiliza **Spring Security** com **JWT** para autenticação e autorização. Os endpoints são protegidos conforme as permissões do usuário.

### 🔑 Geração de Token

Para obter um token JWT, envie um **POST** para:

```
POST /auth/login
```

**Body:**

```json
{
  "login": "admin",
  "password": "123456"
}
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1..."
}
```

Inclua esse token nos próximos requests no cabeçalho **Authorization**:

```
Authorization: Bearer SEU_TOKEN
```

## 📊 Endpoints

### **Produtos**

| Método   | Endpoint         | Descrição                | Acesso        |
| -------- | ---------------- | ------------------------ | ------------- |
| `POST`   | `/produtos`      | Cadastrar um produto     | `ADMIN`       |
| `GET`    | `/produtos`      | Listar todos os produtos | `AUTENTICADO` |
| `GET`    | `/produtos/{id}` | Buscar produto por ID    | `AUTENTICADO` |
| `PUT`    | `/produtos/{id}` | Atualizar um produto     | `ADMIN`       |
| `DELETE` | `/produtos/{id}` | Remover um produto       | `ADMIN`       |

## ⚙️ Configuração e Execução

### 1️⃣ **Clone o Repositório**

```sh
git clone https://github.com/seu-usuario/SalDeMaria.git
cd SalDeMaria
```

### 2️⃣ **Configurar Banco de Dados**

No arquivo `application.properties`, configure o PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/saldemaria
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3️⃣ **Executar o Projeto**

```sh
./mvnw spring-boot:run
```

A API estará disponível em:

```
http://localhost:8080
```

## 📜 Licença

Este projeto está sob a licença **MIT**.

---

💡 **Dica:** Utilize o **Swagger** para testar os endpoints via interface gráfica:

```
http://localhost:8080/swagger-ui/index.html
```

🚀 **API pronta para produção!**

