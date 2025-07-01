# Stage 1: Build con Maven
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# Copia el pom.xml y descarga dependencias para hacer cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente
COPY src ./src

# Compila el proyecto
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia el JAR generado desde el stage de construcción
COPY --from=build /app/target/login-system-0.0.1-SNAPSHOT.jar app.jar

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["/wait-for-it.sh", "db:3306", "--", "java", "-jar", "app.jar"]
