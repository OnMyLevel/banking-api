# 🏦 Banking API - Optimized

API REST optimisée pour minimiser les coûts AWS.

## 🚀 Quick Start

### Local Development
```bash
# Start database
docker-compose up -d postgres

# Run application
mvn spring-boot:run
```

### Test avec Docker
```bash
# Build et run
docker-compose up --build

# Test API
curl http://localhost:8080/api/users
```

## 📊 API Endpoints

- `GET /api/users` - Liste tous les users
- `GET /api/users/{id}` - Récupère un user
- `POST /api/users` - Crée un user
- `PUT /api/users/{id}` - Met à jour un user
- `DELETE /api/users/{id}` - Supprime un user

### Exemples

```bash
# Create user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe"}'

# Get all users
curl http://localhost:8080/api/users

# Update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe"}'

# Delete user
curl -X DELETE http://localhost:8080/api/users/1
```

## 🐳 Docker

Image optimisée : ~150MB
RAM utilisée : ~256MB

## 💰 Coûts AWS Estimés

- RDS t4g.micro : ~$8/mois
- App Runner : ~$5/mois
- ECR : ~$0.05/mois
- **Total : ~$13/mois**

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.2.1
- PostgreSQL 15
- Undertow (lightweight server)
- Docker