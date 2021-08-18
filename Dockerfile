FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/micro-logogenial-back-1.0.jar micro-logogenia-back.jar
ENTRYPOINT ["java","-jar","micro-logogenia-back.jar"]