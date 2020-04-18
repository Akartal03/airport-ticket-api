# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11:latest

# Add Maintainer Info
LABEL maintainer="akartal67@gmail.com"

WORKDIR /FLIGHT/TICKETAPI/

ENV JAR_FILE=target/airportticketapi-0.0.1-SNAPSHOT.jar
ENV PROP_FILE=src/main/resources/application.properties
ENV DATA_FILE=src/main/resources/default_data.sql

RUN mkdir -p /FLIGHT/TICKETAPI/

ADD $JAR_FILE $WORKDIR
ADD $PROP_FILE $WORKDIR
ADD $DATA_FILE $WORKDIR

RUN ls -la .

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "airportticketapi-0.0.1-SNAPSHOT.jar"]
