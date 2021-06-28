#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/availability-api-0.0.1-SNAPSHOT.jar /usr/local/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/app.jar"]
