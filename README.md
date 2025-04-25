# 🛒 Notifikart - E-commerce Simplificado com Spring Boot e Kafka

Este projeto demonstra a comunicação entre microsserviços usando Apache Kafka, simulando um cenário de e-commerce onde eventos de pedidos disparam notificações automáticas.

## 🧱 Arquitetura

- **Order Service**: expõe endpoints REST para criação e atualização de pedidos, publicando eventos no Kafka.
- **Notification Service**: consome eventos do Kafka e envia notificações baseadas no status do pedido.

![Diagrama da arquitetura](./docs/arquitetura.png)

## 📦 Tecnologias

- Java 17
- Spring Boot 3.x
- Apache Kafka
- Docker / Docker Compose

## 🚀 Como subir o ambiente (Keycloak + Kafka)

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
"realm_access": {  
    "roles": [
      "USER",
      "ADMIN"
    ]
}
```

### 1. Subir o ambiente Kafka
