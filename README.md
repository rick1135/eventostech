# 🎫 EventosTech API

API REST para gerenciamento de eventos de tecnologia, com suporte a cupons de desconto e filtragem avançada.

Construída com **Spring Boot 4**, **PostgreSQL**, **Flyway** e containerizada com **Docker**.

---

## 🚀 Tecnologias

* **Linguagem:** Java 21 

* **Framework:** Spring Boot 4.0.3 (Web, Data JPA, Validation)

* **Banco de Dados:** PostgreSQL 16

* **Migrations:** Flyway

* **Infraestrutura:** Docker (para conteinerização do banco de dados)

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
* Java JDK 21+ instalado.
* Maven instalado.
* Docker e Docker Compose instalados.

### Passos

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/rick1135/eventostech.git](https://github.com/rick1135/eventostech.git)
   cd eventostech
   
2. **Suba o Banco com o Docker:**
   Certifique-se de ter um arquivo docker-compose.yml configurado para o PostgreSQL e execute
   ```bash
   docker-compose up -d
   
3. **Execute a aplicação:**
   As tabelas serão criadas automaticamente pelo Flyway na inicialização
    
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
