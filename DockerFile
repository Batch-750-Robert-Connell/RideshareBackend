FROM openjdk:8-jdk-alpine
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/rideshare-user-service.jar rideshare-user-service.jar
EXPOSE 9002
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar rideshare-user-service.jar


