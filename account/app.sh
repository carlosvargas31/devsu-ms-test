#!/bin/bash

echo "Release tests..."

./account/test.sh

echo "Starting docker-compose for application..."

docker compose -f account/docker-compose.yml up -d --build