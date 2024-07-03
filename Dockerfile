#Multistage dockerfile para hacer build con gradle
#Build stage
#FROM gradle:8-jdk17-alpine AS builder

#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY build.gradle settings.gradle $APP_HOME
#
#COPY gradle $APP_HOME/gradle
#COPY --chown=gradle:gradle . /home/gradle/src
#USER root
#RUN chown -R gradle /home/gradle/src
#
#RUN gradle build || return 0
#COPY . .
#RUN gradle clean build

#Run stage
#FROM openjdk:17-alpine AS runner

#ENV ARTIFACT_NAME=test-gradle.jar
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY --from=builder $APP_HOME/build/libs/*.jar $ARTIFACT_NAME
##CMD ["java","-jar","test-gradle.jar"]
#ENTRYPOINT exec java -jar ${ARTIFACT_NAME}

###TEST GRADLE EMBEBIDO
# Multistage dockerfile para hacer build con gradle

# Build stage
FROM openjdk:17-alpine AS builder

# Crear directorio de la aplicación
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copiar archivos de Gradle Wrapper y configuraciones de build
COPY gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY build.gradle settings.gradle $APP_HOME/

# Copiar el resto del proyecto
COPY . .

# Dar permisos de ejecución al script gradlew
RUN chmod +x gradlew

# Ejecutar el build usando Gradle Wrapper
RUN ./gradlew clean build

# Run stage
FROM openjdk:17-alpine AS runner

ENV ARTIFACT_NAME=test-gradle.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copiar el JAR generado desde el build stage
COPY --from=builder $APP_HOME/build/libs/*.jar $ARTIFACT_NAME

# Definir el comando de entrada para ejecutar la aplicación
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
