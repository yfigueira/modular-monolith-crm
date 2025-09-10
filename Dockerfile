# Build stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /build

COPY pom.xml .
COPY account-module/pom.xml account-module/
COPY activity-module/pom.xml activity-module/
COPY bootstrap/pom.xml bootstrap/
COPY deal-module/pom.xml deal-module/
COPY event-bus/pom.xml event-bus/
COPY lead-module/pom.xml lead-module/
COPY user-module/pom.xml user-module/

RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests -pl bootstrap -am

# Runtime stage
FROM amazoncorretto:21

WORKDIR /app

COPY --from=builder /build/bootstrap/target/bootstrap-*.jar /app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app.jar"]