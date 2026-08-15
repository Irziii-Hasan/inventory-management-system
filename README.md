# JH Gadgets — Inventory & Price Comparison System

A Spring Boot backend built for retailers who compare product prices across multiple malls and shops before buying or reselling. It centralizes price tracking that would otherwise be scattered across notebooks, WhatsApp chats, and Instagram posts.

## Problem it solves

Retailers visit different malls and shops to check prices for the same product. Sometimes they just note the price and photo to post on social media for orders; sometimes they buy on the spot and record it manually. This project brings all of that into a single, structured system.

## Features

- Manage malls, shops, categories, and products
- Track prices for the same product across different shops (core price-comparison feature)
- Look up all products in a category
- Look up all shop listings for a product, to compare prices at a glance
- Clean, flat API responses via DTOs (no raw nested entity data)
- Centralized error handling with meaningful HTTP status codes
- Request validation on all create endpoints
- Logging to both console and file

## Tech Stack

- **Java 21**
- **Spring Boot** (Web, Data JPA, Validation)
- **MySQL**
- **MapStruct** — compile-time Entity ↔ DTO mapping
- **Lombok** — boilerplate reduction
- **SLF4J / Logback** — logging

## Project Structure

```
src/main/java/com/jhgadgets/ims/
├── controller/     REST endpoints
├── service/        Business logic (interfaces + impl)
├── repository/     Spring Data JPA repositories
├── model/          JPA entities
├── dto/            API response objects
├── mapper/         MapStruct entity-to-DTO mappers
└── exception/      Custom exceptions + global exception handler
```

## Getting Started

### Prerequisites
- Java 21
- MySQL running locally
- Maven

### Setup

1. Clone the repository
   ```
   git clone https://github.com/Irziii-Hasan/inventory-management-system.git
   ```

2. Create a MySQL database:
   ```sql
   CREATE DATABASE jhgadgets_db;
   ```

3. Copy `src/main/resources/application-sample.properties` to `src/main/resources/application.properties`, and fill in your own MySQL username and password.

4. Run the application:
   ```
   ./mvnw spring-boot:run
   ```

The API will be available at `http://localhost:8080`.

## API Overview

| Resource | Base Endpoint |
|---|---|
| Categories | `/api/categories` |
| Malls | `/api/malls` |
| Shops | `/api/shops` |
| Products | `/api/products` |
| Shop Products (price listings) | `/api/shop-products` |

Each resource supports standard CRUD (`GET`, `POST`, `DELETE`). A few extra endpoints support the core use case:

- `GET /api/products/category/{categoryId}` — all products in a category
- `GET /api/shop-products/product/{productId}` — all shop listings (with prices) for a product, for direct comparison

## Roadmap

- Purchase/inventory tracking (retailer's own stock)
- Order management (for social-media-driven orders)
- Authentication
- Deployment