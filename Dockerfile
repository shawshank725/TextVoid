FROM eclipse-temurin:22-jdk
LABEL maintainer="bro"
ADD target/TextVoid.jar textvoid.jar
ENTRYPOINT ["java", "-jar", "textvoid.jar"]

