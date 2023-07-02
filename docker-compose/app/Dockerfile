FROM openjdk:17.0-oraclelinux8

WORKDIR /app

ARG JAR_FILE="./build/libs/springdemo-*.jar"
ARG DOCKER_COMPOSE_PATH="./docker-compose/app"
COPY $JAR_FILE ./application.jar
COPY $DOCKER_COMPOSE_PATH/ ./

CMD ["java", "-jar", "/app/application.jar"]