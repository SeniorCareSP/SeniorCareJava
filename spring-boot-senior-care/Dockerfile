FROM maven:3-openjdk-17 AS builder

LABEL authors="brito"

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /build/target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]