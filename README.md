# Spring Boot Customer Management System

This project is a simple Spring Boot application that serves as a Customer Management System. It includes components for handling customer data, authentication, and synchronization with a remote API.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)

## Features

- CRUD operations for managing customers
- Customer authentication with an authentication request model
- Synchronization of customers from a remote API using a provided token

## Technologies Used

- Spring Boot
- Spring Data JPA
- Spring Web
- Java Persistence API (JPA)
- Jakarta Persistence API
- H2 Database (for demonstration purposes)
- Other dependencies as per the project structure

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Maven (for building and managing dependencies)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Manideepmukhi/Assignment.git
   
2.Navigate to the project directory:
cd Assignment

3.Build the project using Maven:
mvn clean install

4. Run the application:

### Usage
Once the application is running, you can access the API endpoints using your preferred API client (e.g., Postman). Refer to the API Endpoints section for details on available endpoints.

### API Endpoints
GET /api/customers: Retrieve all customers

GET /api/customers/{id}: Retrieve a customer by ID

POST /api/customers: Create a new customer

PUT /api/customers/{id}: Update an existing customer

DELETE /api/customers/{id}: Delete a customer by ID

POST /api/customers/sync: Synchronize customers from a remote API for sync button

### Contributing
Contributions are welcome! If you find any issues or have suggestions for improvement, feel free to open an issue or create a pull request.


