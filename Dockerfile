# Etapa 1: Build (Compilar a aplicação)
FROM maven:3.8.3-openjdk-17-slim AS build

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o código fonte e o pom.xml para dentro do contêiner
COPY pom.xml . 
COPY src ./src

# Execute o Maven para compilar e empacotar o projeto
RUN mvn clean install

# Etapa 2: Imagem final (Executar a aplicação)
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Copie o JAR gerado na etapa de build para o contêiner final
COPY --from=build /app/target/*.jar app.jar

# Verifique se o JAR foi copiado corretamente
RUN ls -l /app

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
