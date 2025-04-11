FROM eclipse-temurin:21-jre-alpine
LABEL maintainer="cavargas.bermudez31@gmail.com"
COPY target/user-0.0.1-SNAPSHOT.jar user.jar
ENTRYPOINT ["java", "-jar", "/user.jar"]