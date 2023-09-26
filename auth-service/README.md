# Auth Service

## Overview

The Auth Service is responsible for user authentication and registration within the system. It follows the Hexagonal Architecture, separating business logic from input/output handling.

## Features

- User Authentication
- User Registration

## Architecture

The project follows the Hexagonal Architecture pattern, segregating the application into the following modules:

- `domain`: Contains domain models and interface contracts (ports).
- `application`: Implements application use cases, utilizing the interfaces defined in `domain`.
- `adapter`: Holds implementations of the interfaces (adapters), including REST controllers.

## Running the Service

The Auth Service runs in a Docker container and is configured to use port 8081.

### Prerequisites

- Docker
- Docker Compose

### Running with Docker Compose

```bash
docker-compose up -d
```

### Running with project

- This command will start the Auth Service container, accessible at http://localhost:8081 and Swagger UI.

## Project Structure

```
src/
  main/
    java/
      com/baking/authservice/
      domain/
      application/
      adapter/
      util/
````

### How to Use
- To use the Auth Service, you can interact with the defined API endpoints. Here are the available endpoints:

### 1. Authenticate and Generate Token

- Endpoint: ```POST /auth/login```
- Payload:
````
{
  "username": "your_username",
  "password": "your_password"
}
````

### 2. Create a New User

- Endpoint: ```POST /users```
- Payload: 

```
{
  "username": "new_username",
  "email": "new_user@example.com",
  "password": "new_password"
  }

```

### 3. Save Password Reset Token
- Endpoint: ```POST /users/password-reset```
- Parameters:
  - email: Email of the user


### Author
- Adriano Goulart
