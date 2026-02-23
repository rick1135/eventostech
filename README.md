# 🎫 EventosTech API

API REST para gerenciamento de eventos de tecnologia, com suporte a cupons de desconto e filtragem avançada.

Construída com **Spring Boot 4**, **PostgreSQL**, **Flyway** e containerizada com **Docker**.

---

## 🚀 Tecnologias

* **Linguagem:** Java 21 

* **Framework:** Spring Boot 4.0.3 (Web, Data JPA, Validation)

* **Banco de Dados:** PostgreSQL 16

* **Migrations:** Flyway

* **Infraestrutura:** Docker & Docker Compose

* **Utilitários:** Lombok

---

## 🏗 Arquitetura

O projeto segue uma arquitetura em camadas:

* **Controller-Service-Repository:** Separação das responsabilidades.
* **DTO (Data Transfer Object):** Utilização de `records` para isolar as entidades da camada de apresentação, evitando vazamento de dados.
* **Validações Customizadas:** Criação de anotações próprias para regras de negócio.

---

## 🗄 Modelo de Dados

### Diagrama ER

```
┌─────────────────┐       ┌─────────────────┐       ┌─────────────────┐
│      EVENT      │       │     COUPON      │       │     ADDRESS     │
├─────────────────┤       ├─────────────────┤       ├─────────────────┤
│ id (UUID) PK    │──┐    │ id (UUID) PK    │       │ id (UUID) PK    │
│ title           │  │    │ code            │       │ city            │
│ description     │  ├───<│ discount        │       │ uf              │
│ img_url         │  │    │ valid_until     │    ┌──│ event_id (FK)   │
│ event_url       │  │    │ event_id (FK)   │    │  └─────────────────┘
│ date            │  │    └─────────────────┘    │
│ remote          │──┘                           │
│                 │──────────────────────────────┘
└─────────────────┘
         1 : N (Coupons)
         1 : 1 (Address)
```

## 🚀 Como Executar o Projeto

### Pré-requisitos
* **Docker** e **Docker Compose** instalados (para rodar tudo containerizado).
* **Java JDK 21+** e **Maven** (apenas se quiser rodar a aplicação localmente).

### Opção 1 — Tudo com Docker Compose (recomendado)

Sobe o banco, a aplicação e o PgAdmin com um único comando:

```bash
docker compose up -d --build
```

| Serviço | URL | Descrição |
|---|---|---|
| **API** | `http://localhost:8080` | Aplicação Spring Boot |
| **PostgreSQL** | `localhost:5432` | Banco de dados |
| **PgAdmin** | `http://localhost:5050` | Interface web do banco (`admin@admin.com` / `admin`) |

Para parar tudo:
```bash
docker compose down
```

### Opção 2 — Desenvolvimento Local (banco no Docker, app fora)

1. **Suba apenas o banco:**
   ```bash
   docker compose up db -d
   ```

2. **Execute a aplicação com Maven:**
   ```bash
   # Linux/Mac
   ./mvnw spring-boot:run

   # Windows
   mvnw.cmd spring-boot:run
   ```

    
## 📡 Endpoints da API

### Eventos

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/events` | Criar um novo evento |
| `GET` | `/api/events` | Listar eventos com filtros e paginação |
| `GET` | `/api/events/{eventId}` | Obter detalhes de um evento (com cupons ativos) |

### Cupons

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/events/{eventId}/coupons` | Adicionar cupom a um evento |

---

## 📁 Estrutura do Projeto

```
src/main/java/com/rick1135/eventostech/
├── EventostechApplication.java          
├── controller/
│   ├── EventController.java            
│   └── CouponController.java            
├── dto/
│   ├── EventRequestDTO.java             
│   ├── EventResponseDTO.java            
│   ├── EventDetailsDTO.java            
│   ├── CouponRequestDTO.java            
│   └── CouponResponseDTO.java           
├── entity/
│   ├── Event.java                       
│   ├── Coupon.java                      
│   └── Address.java                    
├── exception/
│   ├── GlobalExceptionHandler.java     
│   └── ErrorResponseDTO.java            
├── repositories/
│   ├── EventRepository.java            
│   ├── CouponRepository.java            
│   ├── AddressRepository.java           
│   └── EventSpecification.java         
├── service/
│   ├── EventService.java                
│   ├── CouponService.java               
│   └── AddressService.java              
└── validation/
    ├── EventValidation.java             
    └── EventValidator.java              

src/main/resources/
├── application.properties               
└── db/migration/
    ├── V1__create-event-table.sql        
    ├── V2__create-table-coupon.sql      
    └── V3__create-table-address.sql      
```
