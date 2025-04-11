#!/bin/bash

NETWORK_NAME=my-shared-network

if [ ! docker network ls --format '{{.Name}}' | grep -wq "$NETWORK_NAME" ]; then
  echo "Create external network..."
  docker network create "$NETWORK_NAME"
else
  echo "Network "$NETWORK_NAME" already exists. Continue..."
fi

echo "Starting MS user..."

./user/app.sh

echo "Starting MS account..."

./account/app.sh