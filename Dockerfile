FROM maven:3.5-jdk-8
COPY . /app
WORKDIR /app
#EXPOSE 8090:8090
#RUN mvn clean spring-boot:run