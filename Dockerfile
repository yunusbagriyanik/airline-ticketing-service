FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -DskipTests


FROM openjdk:17
WORKDIR /backend
COPY . .
ENTRYPOINT ["java", "-Dspring.config.use-legacy-processing=true", "-jar", "/airlines-ticketing-backend.jar"]
EXPOSE 8080
