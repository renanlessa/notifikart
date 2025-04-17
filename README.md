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

## 🚀 Como rodar

### 1. Subir o ambiente Kafka
