FROM openjdk:11
EXPOSE 9091
ADD target/order-service.jar order-service.jar
ENTRYPOINT [ "java", "-jar", "/order-service.jar" ]
