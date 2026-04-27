FROM eclipse-temurin:21

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew build -x test

EXPOSE 8080

CMD ["sh", "-c", "java -jar build/libs/*.jar"]
