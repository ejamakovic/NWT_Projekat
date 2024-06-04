#FROM amazoncorretto:11-alpine-jdk
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /NWT_Projekat/system_events

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean install -DskipTests

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#COPY --from=ghcr.io/ufoscout/docker-compose-wait:latest /wait /wait
#
#COPY entrypoint.sh /srvc/entrypoint.sh
#
#ENTRYPOINT ["/bin/sh", "/srvc/entrypoint.sh"]


FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar system_events.jar
#CMD /wait
ENTRYPOINT ["java","-jar","/system_events.jar"]