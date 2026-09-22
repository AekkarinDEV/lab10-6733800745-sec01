# Project Memory & Architecture Guide — Lab 10: Spring WebFlux & WebClient

## Overview
Coursework project for CP353002 Principles of Software Design.
Demonstrates Reactive Programming in Spring Boot using Spring WebFlux, Project Reactor (`Mono`, `Flux`), Reactor Netty, and `WebClient`.

## Student Information
- **Name:** เอกรินทร์ บุดดาหลู่
- **Student ID:** 673380074-5
- **Section:** sec01
- **GitHub Repository:** `git@github.com:AekkarinDEV/lab10-6733800745-sec01.git`

## Architectural Components
1. **Model (`com.example.lab10.model.Product`):**
   - Core domain model containing product details (`id`, `name`, `category`, `brand`, `stock`, `price`, `discountType`).
   - Helper `getDiscountedPrice()` handles MEMBER (10%) and SEASONAL (20%) discount computations.

2. **Repository Layer (`com.example.lab10.repository.ProductRepository`):**
   - In-memory storage utilizing `ConcurrentHashMap`.
   - Seeded with Product ID 1 featuring student identification: `"iPhone 15 Pro (เอกรินทร์ บุดดาหลู่ 673380074-5 sec01)"`.
   - Reactive methods: `findById` (`Mono<Product>`), `findAll` (`Flux<Product>`), `save` (`Mono<Product>`), `deleteById` (`Mono<Void>`), `findByCategory` (`Flux<Product>`).

3. **Service Layer (`com.example.lab10.service.ProductService`):**
   - Implements SRP and DIP via constructor injection.
   - Throws `RuntimeException` wrapped in `Mono.error()` when entities are absent (`switchIfEmpty`).
   - Automatically generates UUID strings if `product.getId()` is null or blank.

4. **Controller Layer (`com.example.lab10.controller.ProductController`):**
   - Exposes REST endpoints returning `Mono` and `Flux`.
   - Fully non-blocking event-driven pipeline on Netty port 8080.

5. **Client Layer (`com.example.lab10.client.ProductWebClient`):**
   - Fluent API HTTP client using Spring `WebClient`.
   - Demonstrates reactive retrieval and transformation: `.get()`, `.post()`, `.delete()`, `.retrieve()`, `.bodyToMono()`, `.bodyToFlux()`.

6. **Unit Tests (`com.example.lab10.Lab10ApplicationTests`):**
   - Verifies Reactive Publishers via Reactor Test `StepVerifier`.
   - 7 test cases covering context load, item lookup, missing entity handling, collection streams, entity persistence, category filtering, and item deletion.

## Deliverables in `result/`
- `result/Lab10_WebClient_Report.html`: 6-page A4 academic HTML report designed according to `academic-report-style`.
- `result/Lab10_6733800745_sec01.pdf` & `result/Lab10_WebClient_Report.pdf`: 6-page compiled PDF report.
