#!/bin/bash
set -e

echo "Checking Java installation..."

if ! command -v java >/dev/null 2>&1; then
  echo "Java not found. Installing OpenJDK 21..."
  sudo apt update
  sudo apt install -y openjdk-21-jdk
else
  echo "Java already installed."
fi

echo "Checking Maven installation..."

if ! command -v mvn >/dev/null 2>&1; then
  echo "Maven not found. Installing Maven..."
  sudo apt install -y maven
else
  echo "Maven already installed."
fi

# Set JAVA_HOME dynamically
JAVA_HOME_PATH=$(dirname $(dirname $(readlink -f $(which java))))
export JAVA_HOME=$JAVA_HOME_PATH
export PATH=$JAVA_HOME/bin:$PATH

echo "JAVA_HOME set to: $JAVA_HOME"
java -version
mvn -version

echo "Building application..."
mvn clean package -DskipTests

echo "Starting application..."
java -jar target/*.jar
