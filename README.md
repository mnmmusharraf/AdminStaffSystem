# Admin Staff Management System - Backend

## Overview

This repository contains the **backend** service for the Admin Staff Management System. The backend is a Java EE Maven project that exposes RESTful APIs to perform CRUD operations on staff data. It handles business logic, validation, and interacts with the database.

## Features

- RESTful API endpoints for staff management (Create, Read, Update, Delete)
- Input validation to ensure data integrity
- Database connectivity and persistence
- Modular architecture with design patterns (MVC, DAO)
- Easily extensible for additional entities

## Technologies Used

- Java EE (Servlets, JAX-RS)
- Maven
- MySQL or PostgreSQL
- Git & GitHub

## Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/mnmmusharraf/AdminStaffSystem.git
   ```

2. **Configure the database**
   - Create a database (e.g., `staff`)
   - Import the schema from `/db/schema.sql`
   - Set database credentials in `src/main/resources/config.properties`

3. **Build and deploy**
   ```bash
   mvn clean install
   ```
   - Deploy the generated WAR file to your Java EE server (Tomcat, GlassFish, etc.)

## API Usage

- Base URL: `http://localhost:8080/AdminStaffSystem/api`
- Example endpoints:
  - `GET /staff` - List all staff
  - `POST /staff` - Add a new staff member
  - `PUT /staff/{id}` - Update staff details
  - `DELETE /staff/{id}` - Remove a staff member

Refer to [API documentation](docs/API.md) for details.

## License

MIT License. See [LICENSE](LICENSE).

## Contact

For issues or support, open a GitHub Issue.
