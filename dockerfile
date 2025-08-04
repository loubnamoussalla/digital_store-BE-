FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-Djdk.tls.client.protocols=TLSv1.2", "-jar", "target/zerodigital-0.0.1-SNAPSHOT.jar"]
