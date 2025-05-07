FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon
EXPOSE 8080
USER nobody
ENTRYPOINT ["java", "-jar", "build/libs/*.jar"]
