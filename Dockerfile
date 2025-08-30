# Build stage
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build the application and create the JAR file
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=builder /app/target/blue-economy-*.jar app.jar
# Expose the port your app runs on
EXPOSE 8080
# Command to run the application
CMD ["java", "-jar", "app.jar"]