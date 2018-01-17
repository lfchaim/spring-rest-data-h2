FROM java:8
VOLUME /tmp
ADD target/spring-rest-data-h2-1.0.0.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
