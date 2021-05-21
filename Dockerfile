FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD ./target/basic-admin-service-0.0.1.jar back-logogenia.jar
ENTRYPOINT ["java","-jar","back-logogenia.jar"]