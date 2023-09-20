FROM openjdk:17
EXPOSE 8080
COPY target/pin-city-0.0.1-SNAPSHOT.jar app.jar
ENV POSTGRES_SCHEMA "pins"
ENTRYPOINT ["java","-XX:MaxRAM=100M", "-jar", "/app.jar"]
