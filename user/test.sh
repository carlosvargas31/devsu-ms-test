#!/bin/bash

echo "Starting docker-compose for test profile..."

docker compose -f docker-compose.test.yml up -d --wait

echo "Docker services up. Running tests..."

./mvnw test -Dspring.profile.active=test
./mvnw clean package -Dspring.profile.active=test

echo "Cleaning up..."

docker compose -f docker-compose.test.yml down

