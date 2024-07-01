#Multistage dockerfile para hacer build con gradle
#Build stage
FROM openjdk:17-alpine AS builder

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle

USER root                # This changes default user to root
RUN chown -R gradle $APP_HOME # This changes ownership of folder
USER gradle              # This changes the user back to the default user "gradle"

RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

#Run stage
FROM openjdk:17-alpine AS runner

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/*.jar test-gradle.jar
CMD ["java","-jar","test-gradle.jar"]