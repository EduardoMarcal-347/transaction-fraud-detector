FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY --chown=gradle:gradle .. /app
RUN gradle clean build

FROM gradle:8.5-jdk21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005", "-jar", "app.jar"]