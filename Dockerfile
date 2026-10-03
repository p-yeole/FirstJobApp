FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=aws

ENTRYPOINT ["java", "-jar", "app.jar"]
