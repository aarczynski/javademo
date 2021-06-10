FROM openjdk:11-jre

WORKDIR /app

ARG JAR_FILE="./build/libs/springdemo-*.jar"
ARG DOCKER_COMPOSE_PATH="./docker-compose/app"
COPY $JAR_FILE ./application.jar
COPY $DOCKER_COMPOSE_PATH/ ./
RUN apt update && apt install postgresql-client -y
RUN chmod +x /app/wait-for-it.sh

CMD ["java", "-jar", "/app/application.jar"]