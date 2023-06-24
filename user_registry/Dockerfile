FROM maven:3.3-jdk-8

MAINTAINER <Harsh (kumarp148@gmail.com)>

RUN mkdir employee_registry_backend
RUN mkdir employee_registry_backend/src

ADD ./src /employee_registry_backend/src/
ADD ./pom.xml /employee_registry_backend/pom.xml
ADD ./target /employee_registry_backend/target
#RUN mvn -f /employee_registry_backend/pom.xml clean install
EXPOSE 8080

ENTRYPOINT [ "sh", "-c", " nohup java -XX:-HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/dumps/ -Djava.security.egd=file:/dev/./urandom -jar /employee_registry_backend/target/*.jar --server.port=8080" ]
