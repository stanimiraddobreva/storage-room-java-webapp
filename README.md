# Storage Room Management System

A Java-based inventory management system for a storage room, built with core Object-Oriented Programming principles and a lightweight web interface for viewing stock data in real time.

## Overview

This project models a warehouse/storage room where products are tracked with detailed information: name, expiry date, arrival date, manufacturer, measurement unit, quantity, storage location, and comments. Each product also belongs to a category (Perishable or Non-Perishable), which determines its storage fee calculation.

The backend is written in pure Java (no external frameworks) and exposes product data through a lightweight embedded HTTP server. A simple HTML/CSS/JavaScript frontend consumes this data and displays it in a styled, responsive table.

## Features

- Add, remove, and search products by name, manufacturer, or comment
- Track expiry dates and identify expired products
- Sort products by expiry date
- Filter products by storage location (section)
- Calculate total storage fees across all products
- View live inventory data in a browser through a REST-style API

## Object-Oriented Design

This project was built to demonstrate all four core OOP principles:

| Principle | Where it's applied |
|---|---|
| **Encapsulation** | All fields are private, with validating setters (e.g. rejecting negative quantities, empty names, invalid dates) |
| **Abstraction** | `Product` is an abstract class defining common behavior and structure, without allowing direct instantiation |
| **Inheritance** | `PerishableProduct` and `NonPerishableProduct` extend `Product`, adding type-specific behavior |
| **Polymorphism** | `getCategory()` and `getStorageFee()` are overridden per subclass and resolved dynamically at runtime |

## Tech Stack

- **Backend:** Java (JDK), `com.sun.net.httpserver.HttpServer` for a minimal REST API — no external dependencies
- **Frontend:** HTML, CSS, JavaScript (no frameworks)
- **Data:** In-memory (hardcoded sample data), no file or database persistence

## Project Structure
StorageRoom/
├── src/
│ ├── Main.java
│ └── model/
│ ├── Product.java (abstract base class)
│ ├── PerishableProduct.java
│ ├── NonPerishableProduct.java
│ ├── Location.java
│ ├── MeasurementUnit.java
│ ├── StorageRoom.java
│ └── StorageRoomServer.java
└── public/
├── index.html
├── style.css
└── script.js


## How to Run

**1. Compile the Java source files**

From the `src/` directory:
```bash
javac model/*.java Main.java
```

**2. Start the server**

```bash
java model.StorageRoomServer
```

You should see: Server running on http://localhost:8000/products

**3. Open the frontend**

Open `public/index.html` directly in a browser. The page will automatically fetch and display the current inventory from the running Java server.

## API

| Endpoint | Method | Description |
|---|---|---|
| `/products` | GET | Returns all products in the storage room as JSON |
  
- **Data:** In-memory (hardcoded sample data), no file or database persistence

## Project Structure
