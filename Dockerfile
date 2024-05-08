FROM alpine:latest

RUN apk update && apk add openjdk17
RUN apk add git

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk/
RUN export JAVA_HOME

WORKDIR /app
RUN git clone https://github.com/vromantsev/aws-producer-consumer-app.git
WORKDIR /app/aws-producer-consumer-app/
EXPOSE 8080/tcp

MAINTAINER Vladyslav Romantsev

RUN ./mvnw clean package
ENTRYPOINT ["./mvnw", "spring-boot:run"]