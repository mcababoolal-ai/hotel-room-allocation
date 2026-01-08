#!/bin/bash
./mvnw clean package
java -jar target/*.jar
