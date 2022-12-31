# our base build image
FROM maven:3-jdk-11 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn -f pom.xml clean package

# our final base image
FROM openjdk:17

# copy over the built artifact from the maven image
COPY --from=maven ./target/crakruk-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java","-jar","crakruk-0.0.1-SNAPSHOT.jar"]
