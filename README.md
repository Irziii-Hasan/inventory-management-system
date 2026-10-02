# JH Gadgets — Inventory & Price Comparison System

A Spring Boot backend built for retailers who compare product prices across multiple malls and shops before buying or reselling. It centralizes price tracking, stock management, and order handling that would otherwise be scattered across notebooks, WhatsApp chats, and Instagram posts.

## Problem it solves

Retailers visit different malls and shops to check prices for the same product. Sometimes they just note the price and photo to post on social media for orders; sometimes they buy on the spot and record it manually, needing to track what they've bought, how much stock they have, what it's worth, and which social-media orders they can actually fulfill. This project brings all of that into a single, structured system.

## Features

- Manage malls, shops, categories, and products — including partial updates (PATCH) with null-safe field handling, so only the fields sent in a request are changed
- Track prices for the same product across different shops (core price-comparison feature)
- Look up all products in a category
- Look up all shop listings for a product, to compare prices at a glance
- Record purchases (product, shop, quantity, price, date)
- Automatic inventory tracking: every purchase updates stock quantity and recalculates a running weighted-average cost per product
- Customer records (name, phone, address) reused across orders instead of re-entering details each time
- Order management with automatic stock-aware status: an order is auto-confirmed (and stock deducted) if enough inventory exists, or left pending if it doesn't — no manual stock checking needed
- Business-rule-guarded order updates: an order's quantity or product can only be changed while it's still `PENDING`, since inventory has already been adjusted once an order is `CONFIRMED`
- Purchase records are treated as a near-immutable ledger — only the purchase date can be corrected after the fact, since quantity/price retroactively edited would silently invalidate the weighted-average cost already calculated for inventory
- Clean, flat API responses via DTOs (no raw nested entity data)
- Centralized error handling with meaningful HTTP status codes (404 for missing resources, 400 for validation failures, 409 for business-rule conflicts)
- Request validation on all create endpoints (including a Pakistani phone number format check)
- Logging to both console and file

## Tech Stack

- **Java 21**
- **Spring Boot** (Web, Data JPA, Validation, Transactions)
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
│   └── enums/      Enum types (e.g. OrderStatus)
├── dto/            API request/response objects
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
| Purchases | `/api/purchases` |
| Inventory | `/api/inventories` |
| Customers | `/api/customers` |
| Orders | `/api/orders` |

Each resource supports standard CRUD (`GET`, `POST`, `PATCH`, `DELETE`), with two deliberate exceptions:
- **Inventory** has no PATCH endpoint — it's a derived record, always recalculated from Purchase/Order activity rather than edited directly.
- **Purchase** only allows its `purchaseDate` to be updated via PATCH — quantity, price, product, and shop are locked after creation to protect the integrity of the weighted-average cost already applied to inventory.

A few extra endpoints support the core use cases:

- `GET /api/products/category/{categoryId}` — all products in a category
- `GET /api/shop-products/product/{productId}` — all shop listings (with prices) for a product, for direct comparison
- `GET /api/inventories/product/{productId}` — current stock and average cost for a specific product
- `GET /api/orders/customer/{customerId}` — all orders placed by a specific customer

Creating a purchase (`POST /api/purchases`) automatically creates or updates the corresponding inventory record. Creating an order (`POST /api/orders`) checks current stock: if enough is available, stock is deducted and the order is marked `CONFIRMED`; otherwise it's marked `PENDING` and inventory is left untouched.

Updating an order (`PATCH /api/orders/{orderId}`) follows the same stock-safety principle: `sellingPrice`, `customerId`, and `orderDate` can always be changed, but `quantity` and `productId` are only editable while the order is still `PENDING` — once `CONFIRMED`, inventory has already been deducted against the original quantity/product, so changing them is rejected with a `409 Conflict`.

## Roadmap

- Manual order confirmation/delivery/cancellation endpoints for pending orders
- Standard selling price on Product (as a default reference, separate from the price actually charged per order)
- Purchase correction with inventory recalculation (reverse the old purchase's contribution to the weighted-average cost, then reapply the corrected values) — deferred for now since Purchase is currently immutable except for date
- Three price tiers: original purchase price, wholesale/resale price, and direct-to-consumer price
- Discount/sale pricing support
- Optional extra product details at creation time
- Multiple photos per product
- Order returns/refunds handling
- Partial or delayed payment tracking for orders
- Authentication
- Deployment