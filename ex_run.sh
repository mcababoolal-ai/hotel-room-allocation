#!/bin/bash
set -e

IMAGE_NAME=hotel-room-allocation
CONTAINER_NAME=hotel-room-allocation-container

echo "Building Docker image..."
docker build -t $IMAGE_NAME .

echo "Running Docker container..."
docker run -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME

