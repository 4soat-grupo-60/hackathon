FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn clean install

EXPOSE 5000

CMD ["java", "-jar", "target/healthmed-0.0.1-SNAPSHOT.jar"]