FROM maven:3.9.8-amazoncorretto-21 as maven-builder
WORKDIR /app

ENV LANG="C.utf-8"
ENV TZ=Europe/Moscow

COPY . /app

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM amazoncorretto:21
COPY --from=maven-builder app/target/khamitov-0.0.1-SNAPSHOT.jar /app-service/khamitov.jar
WORKDIR /app-service

EXPOSE 8080
ENTRYPOINT ["java","-jar","khamitov.jar"]