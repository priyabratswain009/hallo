FROM maven:3.8.4-jdk-11

#COPY settings.xml /usr/share/maven/ref/
COPY . /project/service-registry
WORKDIR /project/service-registry
RUN mvn clean package -e
