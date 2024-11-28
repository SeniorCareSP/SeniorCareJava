# Etapa de build usando imagem Maven com OpenJDK 17
FROM maven:3-openjdk-17 AS builder

LABEL authors="brito"

WORKDIR /build

# Copie o código-fonte para o diretório de trabalho
COPY . .

# Execute o Maven para empacotar a aplicação, ignorando a execução e a compilação dos testes
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

# Use uma imagem OpenJDK slim para a imagem fin al
FROM openjdk:17-slim

WORKDIR /app

# Copie o JAR construído da etapa de build para o diretório da aplicação
COPY --from=builder /build/target/*.jar /app/app.jar

# Exponha a porta, caso necessário
EXPOSE 8080

# Comando padrão para executar a aplicação
CMD ["java", "-jar", "app.jar"]
