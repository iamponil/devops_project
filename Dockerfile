FROM adoptopenjdk/openjdk11:jre11u-2023-11-12-16-18-beta-ubuntu-nightly
EXPOSE 8888
ADD ./target/DevOps_Project-2.1.jar test-docker.jar 
ENTRYPOINT ["java","-jar","/test-docker.jar"]
