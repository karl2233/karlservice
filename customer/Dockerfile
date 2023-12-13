FROM openjdk:17.0-slim
ARG JAR_FILE=target/*.jar
COPY ./target/customer-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]