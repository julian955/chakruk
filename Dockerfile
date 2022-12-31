FROM maven:3.6.0-jdk-11-slim
ARG JAR_FILE
COPY target/${JAR_FILE} test.jar
ENTRYPOINT ["java","-jar","test.jar"]