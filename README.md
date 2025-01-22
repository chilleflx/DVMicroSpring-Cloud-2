# 🚀 DVMicroSpring-Cloud
This repository contains a collection of microservices built with Spring Boot and Spring Cloud to demonstrate a microservices architecture. The services include:

ConfigMicro: A Spring Cloud Config Server for centralized configuration management.

eurekaserver: A Eureka Server for service discovery.

microservice-commandes: A microservice for managing orders (Commandes).

microservice-produits: A microservice for managing products (Produits).

clientui: A frontend application that interacts with the microservices using Feign clients.

# 📚 Table of Contents
Introduction

Microservices Overview

ConfigMicro

eurekaserver

microservice-commandes

microservice-produits

clientui

zuul-server

Technologies

Setup

Prerequisites

Running the Microservices

Configuration Management

Service Discovery

API Gateway

Endpoints

Health Monitoring

Team

License

# 🚀 Introduction
This repository demonstrates a microservices architecture using Spring Boot and Spring Cloud. The services are designed to work together, leveraging Spring Cloud Config for centralized configuration, Eureka for service discovery, and Zuul for API gateway functionality.

# 🛠 Microservices Overview

1. ConfigMicro ☁️
A Spring Cloud Config Server that provides centralized configuration management for all microservices. It fetches configuration properties from a Git repository.

Key Features:
Centralized configuration management.

Dynamic property updates without restarting services.

Integration with Git for version-controlled configuration.

Configuration:
Git Repository: https://github.com/chilleflx/cloud-config.git

Default Port: 9101

2. eurekaserver 🔍
A Eureka Server for service discovery. All microservices register themselves with this server, enabling dynamic service discovery and load balancing.

Key Features:
Service registration and discovery.

Health monitoring of registered services.

Configuration:
Default Port: 8761

3. microservice-commandes 📦
A microservice for managing orders (Commandes). It uses Spring Cloud Config to fetch its configuration and Eureka for service discovery.

Key Features:
CRUD operations for orders.

Dynamic configuration using Spring Cloud Config.

Health monitoring with Spring Boot Actuator.

Configuration:
Default Port: 8080

Dynamic Property: mes-config-ms.commandes-last (number of days to retrieve orders).

4. microservice-produits 🛒
A microservice for managing products (Produits). It interacts with an H2 database and provides endpoints for retrieving product details.

Key Features:
CRUD operations for products.

Integration with H2 database.

Initial data loaded via data.sql.

Configuration:
Default Port: 8081

Database Schema: Defined in schema.sql.

Initial Data: Inserted via data.sql.

5. clientui 🖥️
A frontend application that interacts with the microservices using Feign clients. It provides a user interface for managing products and orders.

Key Features:
Thymeleaf for rendering views.

Feign clients for communication with microservices.

Endpoints for displaying and managing products and orders.

Configuration:
Default Port: 8082

6. zuul-server 🚪
An API Gateway built with Netflix Zuul. It routes requests to the appropriate microservices and provides load balancing.

Key Features:
Request routing and load balancing.

Integration with Eureka for service discovery.

Configuration:
Default Port: 9004

# 🛠 Technologies
Java 17 ☕

Spring Boot 2.7.16 🌱

Spring Cloud Config ☁️

Spring Data JPA 🗄️

H2 Database 💾 (In-memory database for development)

Spring Boot Actuator 🩺 (Health checks and monitoring)

Springfox Swagger 📄 (API documentation)

Maven 🛠️ (Build and dependency management)

Eureka 🔍 (Service discovery)

Zuul 🚪 (API Gateway)

Feign 🤝 (Declarative REST clients)

🛠 Setup
Prerequisites
Java 17 or higher

Maven 3.x

Git

Running the Microservices

1.Clone the Repository:

git clone https://github.com/yourusername/DVMicroSpring-Cloud.git](https://github.com/chilleflx/DVMicroSpring-Cloud-2.git

cd DVMicroSpring-Cloud-2

2.Run ConfigMicro:

cd ConfigMicro

mvn clean install

mvn spring-boot:run

ConfigMicro will start on http://localhost:9101.

3.Run eurekaserver:

cd ../eurekaserver

mvn clean install

mvn spring-boot:run

Eureka Server will start on http://localhost:9102.

4.Run microservice-commandes:

cd ../microservice-commandes

mvn clean install

mvn spring-boot:run

microservice-commandes will start on http://localhost:9201.

5.Run microservice-produits:

cd ../microservice-produits

mvn clean install

mvn spring-boot:run

microservice-produits will start on http://localhost:9301.

6.Run clientui:

cd ../clientui

mvn clean install

mvn spring-boot:run

clientui will start on http://localhost:8080.

7.Run zuul-server:

cd ../zuul-server

mvn clean install

mvn spring-boot:run

zuul-server will start on http://localhost:9004.

# ⚙️ Configuration Management
ConfigMicro
Fetches configuration from the Git repository: https://github.com/chilleflx/cloud-config.git.

Properties are stored in application.yml or application.properties.

Dynamic Configuration
To update the configuration dynamically:

Modify the configuration in the Git repository.

Refresh the configuration using the Actuator endpoint:
curl -X POST http://localhost:8080/actuator/refresh
# 🔗 Endpoints
microservice-commandes

HTTP Method	Endpoint	Description

POST	/commandes	Add a new order.

GET	/commandes/last	Retrieve the last orders.

GET	/commandes	Retrieve all orders.

GET	/commandes/{id}	Retrieve a specific order by ID.

PUT	/commandes/update	Update an existing order.

DELETE	/commandes/{id}	Delete an order by ID.

microservice-produits

HTTP Method	Endpoint	Description

GET	/Produits	Retrieve the list of all products.

GET	/Produits/{id}	Retrieve a product by its ID.

clientui
HTTP Method	Endpoint	Description

GET	/afficher-message	Displays a message from the controller.

GET	/toutes-commandes	Displays all existing orders.

GET	/details-produit/{id}	Displays the details of a product.

POST	/commander-produit/{idProduit}/{montant}	Creates an order.

DELETE	/supprimer-commande/{idCommande}	Deletes an order.

GET	/modifier-commande/{id}/{productId}	Displays the form to update an order.

POST	/mettre-a-jour-commande	Updates an order.

# 🩺 Health Monitoring
microservice-commandes
Uses Spring Boot Actuator for health checks.

Access the health endpoint:

http://localhost:8080/actuator/health

Custom health indicator: The service is UP if there are orders in the COMMANDE table; otherwise, it is DOWN.

# 👥 Team
Meet the team behind Mcommandes:

DAKKA Ilyass
Marouane Rachdi

# 📜 License
This project is licensed under the MIT License. See the LICENSE file for details.
