# our base build image
FROM maven:3-jdk-11 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn -DskipTests package

# our final base image
FROM openjdk:8u171-jre-alpine

# copy over the built artifact from the maven image
COPY --from=maven target/api-*.jar /app/dist/api.jar

WORKDIR /appdocker

RUN mkdir -p /app/logs /app/app /app/config /app/dist

ENTRYPOINT ["java","-jar","/src/deploy/test.jar"]
