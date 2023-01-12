FROM eclipse-temurin
ADD target/backendflightprogram-0.0.1-SNAPSHOT.jar fullstack-backend-flightprogram-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/fullstack-backend-flightprogram-1.0.0.jar"]