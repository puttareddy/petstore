FROM maven:3.5-jdk-8 as BUILD
 
COPY src /usr/src/petstore/src
COPY pom.xml /usr/src/petstore
RUN mvn -f /usr/src/petstore/pom.xml clean package
 
FROM openjdk:8-jdk-alpine
LABEL version="1.0.0"

# add bash support
RUN apk add --no-cache bash

# add curl support
RUN apk add --no-cache curl

# set default JVM options which can be overridden while building image using --build-arg 
ARG DEF_JAVA_OPTS="-Xmx512m -Dlogfilepath=/var/log/"

# use default JVM options which can be overridden by overriding env variable JAVA_OPTS before running container using -e
ENV JAVA_OPTS=$DEF_JAVA_OPTS

# copy api jar & sources jar to /
COPY --from=BUILD  /usr/src/petstore/target/pet-store-*-SNAPSHOT.jar /

# run java application passing in JAVA_OPTS (set in base image)
ENTRYPOINT exec java $JAVA_OPTS -jar /pet-store-*-SNAPSHOT.jar --spring.config.location=file:/etc/config/application.properties
