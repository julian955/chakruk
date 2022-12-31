FROM maven:3.6.0-jdk-11-slim
ARG JAR_FILE
COPY test.jar /usr/share
ENTRYPOINT ["java","-jar", "test.jar"]