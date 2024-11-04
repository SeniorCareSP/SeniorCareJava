# Use uma imagem Maven com OpenJDK 17 ou 11
FROM maven:3-openjdk-17 AS builder

LABEL authors="brito"

WORKDIR /build

# Copie todos os arquivos para o diretório de trabalho
COPY . .

# Execute o Maven para empacotar a aplicação, ignorando os testes
RUN mvn clean package -DskipTests

# Use uma imagem OpenJDK slim para a imagem final
FROM openjdk:17-slim

WORKDIR /app

# Copie o arquivo JAR construído para o diretório da aplicação
COPY --from=builder /build/target/*.jar /app/app.jar

# Comando padrão para executar a aplicação
CMD ["java", "-jar", "app.jar"]
