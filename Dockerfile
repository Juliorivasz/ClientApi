FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon
EXPOSE 8080
USER nobody
ENTRYPOINT ["java", "-jar", "build/libs/clientApi-0.0.1-SNAPSHOT.jar"]
