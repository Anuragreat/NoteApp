# Start with a base image that has Java
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven build files
COPY pom.xml .

# Download dependencies
RUN apk add --no-cache maven
RUN mvn dependency:go-offline -B

# Copy the rest of the project
COPY src ./src

# Package the app
RUN mvn package -DskipTests

# Expose port the app will run on
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/notes-0.0.1-SNAPSHOT.jar"]
