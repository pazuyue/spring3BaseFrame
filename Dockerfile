FROM openjdk:17
MAINTAINER Spring3BaseFrame
COPY ./target/SpringDemo-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080 10005
ENTRYPOINT ["java","-jar","app.jar"]
