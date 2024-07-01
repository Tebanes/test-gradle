#Multistage dockerfile para hacer build con gradle
#Build stage
FROM gradle:8-jdk17-alpine AS builder

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

#Run stage
FROM openjdk:17-alpine AS runner

ENV ARTIFACT_NAME=test-gradle.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/*.jar $ARTIFACT_NAME
#CMD ["java","-jar","test-gradle.jar"]
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}