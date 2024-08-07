# Tienda Nube Pricing Service

This project is a microservice for managing pricing plans for a fictional service called "Tienda Nube". The service allows for the creation and listing of pricing plans, including specific types such as EmailPricing and ContactsPricing.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Setup and Run](#setup-and-run)
- [Usage](#usage)

## Features

- **Create Contacts Pricing:** Create pricing plans based on the number of available contacts.
- **Create Email Pricing:** Create pricing plans based on the number of available emails.
- **List Pricings:** Retrieve a list of all pricing plans with filtering options.

## Technology Stack

- **Java 17**
- **Spring Boot**
- **MongoDB**
- **JUnit 5**
- **Mockito**

## Prerequisites

- Java 17 installed
- MongoDB installed and running
- Maven installed

## Setup and Run

### Clone the Repository

```bash
git clone https://github.com/alearmas/tienda-nube-challenge.git
cd tienda-nube-challenge
mvn clean install
mvn spring-boot:run
```

The application will start on http://localhost:8080.

## Usages

### Create Contacts Pricing
```bash
curl -X POST http://localhost:8080/api/v1/pricing/contacts/create \
-H "Content-Type: application/json" \
-d '{
"contactsAvailable": 10000,
"price": 100.00
}'
```

### Create Email Pricing
```bash
curl -X POST http://localhost:8080/api/v1/pricing/email/create \
-H "Content-Type: application/json" \
-d '{
"emailsAvailable": 120000,
"price": 200.00
}'
```

### List Pricings
```bash
curl -X GET http://localhost:8080/api/v1/pricing/list
```