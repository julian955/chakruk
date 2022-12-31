FROM maven:3.6.0-jdk-11-slim
ARG JAR_FILE
COPY /target/test.jar /usr/share
ENTRYPOINT ["java","-jar", "test.jar"]