![java10x Logo](https://java10x.dev/wp-content/uploads/2024/12/logo-java.png)

# 🛒 Notifikart - E-commerce Simplificado com Spring Boot e Kafka

Este projeto é composto por dois microsserviços principais: `order-service` e `notification-service`. 
Ele utiliza **Spring Boot**, **Kafka** para mensageria e **MongoDB** como banco de dados. 
O **Keycloak** é usado como servidor de autorização para autenticação baseada em JWT.

## 🧱 Arquitetura

- **order-service**: Responsável por gerenciar pedidos e produzir mensagens no Kafka.
- **notification-service**: Consome mensagens do Kafka e processa notificações.
- **keycloak**: Authorization Server (Keycloak) para autenticação e autorização.
- **kafka**: Mensageria entre os microsserviços.

## 📦 Tecnologias

- **Java 17**
- **Spring Boot 3.3.4**
   - Spring Web
   - Spring Security (OAuth2 Resource Server)
   - Spring Kafka
   - Spring Data MongoDB
   - Spring State Machine
   - Lombok
- **Apache Kafka**
- **MongoDB**
- **Keycloak** (Authorization Server)

## 🚀 Como subir o ambiente (Keycloak + Kafka + MongoDB)

Execute o comando abaixo na pasta docker contida na raiz do projeto para subir os containers:

```bash
docker-compose up -d
```

### 🔐 Configurando o Keycloak para funcionar com Spring Security

### 🧭 Passo 1: Criar o Realm

1. Acesse o Keycloak (`http://localhost:8080`)
2. No menu lateral, clique no combo escrito Keycloak depois em **Create Realm**
3. Nomeie como `notificart` e clique em **Create**
4. Garanta a seleção do realm `notificart` no combo no canto superior esquerdo

---

### 👥 Passo 2: Criar o Usuário

1. Vá até **Users > Add user**
2. Preencha:
    - **Username**
    - **Email**
    - **First Name**
    - **Last Name**
    - Marque **Email Verified** como On
    - Deixe os outros campos padrão
    - Clique em **Create**
3. Após criar, vá até a aba **Credentials**
    - Defina uma senha
    - Marque **Temporary = OFF**
    - Clique em **Set Password**

---

### 🏢 Passo 3: Criar o Cliente (Client)

1. Vá até **Clients > Create**
2. Preencha:
    - **Client Type**: `OpenID Connect`
    - **Client ID**: `notificart-client`    
3. Clique em **Next**
4. Na aba **Capability config** do client:
    - Deixar como está, garantir apenas que: 
    - **Standard Flow Enabled**: ON
    - **Direct Access Grants Enabled**: ON
5. Clique em **Next**
6. Na aba **Settings** do client:
    - Deixar tudo em branco
7. Clique em **Save**

---

### 🛡️ Passo 4: Criar Roles (autorização via roles)

1. No menu lateral vá até **Realm roles**
2. Clique em **Create Role**
    - **Role name**: `ADMIN` (ou outro nome no padrão que você desejar)
    - Clique em **Save**
    - Repita o processo para criar a role `USER`

---

### 👤 Passo 5: Atribuir Roles ao Usuário

1. No menu lateral vá até **Users > `seu usuario` > Role Mapping**
2. Clique em **Assign role**
3. Selecione a role desejada
4. Clique em **Assign**

---

Com isso, o token JWT do Keycloak já conterá:

```json
{
  "realm_access": {
    "roles": [
      "USER",
      "ADMIN"
    ]
  }
}
```

## 🚀 Como rodar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/renanlessa/notifikart.git
```

2. Garanta que MongoDB, Kafka e Keycloak estejam rodando.

3. Execute o order-service (configurado para rodar na porta 8081):

```bash
./mvnw spring-boot:run
```

4. Execute o notification-service (configurado para rodar na porta 8082):

```bash
./mvnw spring-boot:run
```

5. Acesse: `http://localhost:8081` para o order-service e `http://localhost:8082` para o notification-service.

6. Acesse o Keycloak em `http://localhost:8080` para gerenciar usuários e roles, user: admin, password: admin.

7. Realizar autenticação no keycloak e obter o token JWT:

```
   curl --location 'http://localhost:8080/realms/notificart/protocol/openid-connect/token' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'grant_type=password' \
   --data-urlencode 'client_id=notificart-client' \
   --data-urlencode 'username=renanlessa' \
   --data-urlencode 'password=12345'
```
8. Realizar as demais chamadas para o order-service, utilizando o token JWT obtido no passo anterior.
9. Endpoints do order-service:

| Método | Caminho     | Descrição            |
|--------|-------------|----------------------|
| POST   | `/order`    | Criar pedido         |
| PUT    | `/stadiums` | Editar status pedido |

10. notification-service não tem nenhum endpoint, apenas consome mensagens do Kafka.

---

### License

MIT License

Copyright (c) 2025 Renan Lessa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
