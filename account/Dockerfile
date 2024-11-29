# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy only the necessary files first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy only the built artifact from the build stage
COPY --from=build /app/target/*.jar app.jar

# Reduce image size further by setting JVM options
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Expose port 8080 for the application
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
