FROM openjdk:17-alpine
ADD build/libs/*.jar test-gradle.jar
ENTRYPOINT ["java","-jar","test-gradle.jar"]