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
```

### Configure MongoDB

Make sure MongoDB is installed and running on your machine. The application is configured to connect to MongoDB running on `localhost` at the default port `27017.

If MongoDB is running on a different host or port, update the `src/main/resources/application.properties` file with the correct connection details:

```bash
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=base_pricing
```

### Build project
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on http://localhost:8080.

## Usage

### API endpoints

#### Creates contacts pricing:

```bash
POST
http://localhost:8080/api/v1/pricing/contacts
BODY:
{
    "contactsAvailable": 100000,
    "emailsAvailable": 0,
    "price": 150
}
```

#### List contacts pricings:
```bash
GET
http://localhost:8080/api/v1/pricing/contacts
```

#### Creates emails pricing:
```bash
POST
http://localhost:8080/api/v1/pricing/emails
BODY:
{
    "emailsAvailable": 1000,
    "price": 150
}
```
#### List emails pricings:
```bash
GET
http://localhost:8080/api/v1/pricing/emails
```

For detailed information on the API endpoints and their usage, refer to the challenge instructions provided [here](https://coda.io/d/Desafio-Tecnico-Backend_dk8MpPF3fJa/Desafio-Tecnico-Backend_suxBh?searchClick=df9faa06-486c-46f4-9415-4524b4356914_k8MpPF3fJa#_lucoX).