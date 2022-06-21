FROM openjdk:18
ADD target/rest-0.0.1-SNAPSHOT.jar rest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rest-0.0.1-SNAPSHOT.jar"]