FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the application inside Docker
RUN mvn clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Start the application (foreground process)
CMD ["java", "-jar", "target/room-allocation-0.0.1-SNAPSHOT.jar"]
