FROM openjdk:11.0.7-jre
ADD target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]