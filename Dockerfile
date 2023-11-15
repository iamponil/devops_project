FROM openjdk:11.0.3-jdk-slim
EXPOSE 8888
ADD ./target/DevOps_Project-2.1.jar test-docker.jar 
ENTRYPOINT ["java","-jar","/test-docker.jar"]
