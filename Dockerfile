#base image | intsalls OS + maven + java 17
FROM maven:3.9-eclipse-temurin-17 AS build  
WORKDIR /app 


COPY pom.xml . 



# build the app | runs maven build 
COPY src ./src
#download all project dependencies  
RUN mvn -DskipTests package

# run stage 
FROM eclipse-temurin:17-jre
WORKDIR /app 

COPY --from=build /app/target/javamavenapp-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]