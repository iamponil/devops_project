FROM iamponil/alpine:1.0.0
RUN apk add openjdk11
EXPOSE 8888
ADD ./target/DevOps_Project-2.1.jar test-docker.jar 
ENTRYPOINT ["java","-jar","/test-docker.jar"]
