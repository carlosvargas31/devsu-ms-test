#!/bin/bash

echo "Release tests..."

./user/test.sh

echo "Starting docker-compose for application..."

docker compose -f user/docker-compose.yml up -d --build