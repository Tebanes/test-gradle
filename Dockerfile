#Multistage dockerfile para hacer build con gradle
#Build stage
FROM openjdk:17-alpine AS builder

WORKDIR /app
COPY . .
RUN gradle clean build

#Run stage
FROM openjdk:17-alpine AS runner

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar test-gradle.jar
CMD ["java","-jar","test-gradle.jar"]
