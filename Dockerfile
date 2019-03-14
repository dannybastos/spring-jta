FROM dannybastos/openjdk8

MAINTAINER Danny Bastos <danny.bastos.br@gmail.com>

WORKDIR /app

COPY . /app

RUN ./gradlew clean bootJar

RUN mv /app/build/libs/spring-jta*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT java -jar /app/app.jar
