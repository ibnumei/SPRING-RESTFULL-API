FROM openjdk:22-slim

RUN mkdir /app

COPY target/spring-restfull-api-0.0.1-SNAPSHOT.jar /app/spring-restfull-api-0.0.1-SNAPSHOT.jar

WORKDIR /app

CMD ["java", "-jar", "spring-restfull-api-0.0.1-SNAPSHOT.jar"]