FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]