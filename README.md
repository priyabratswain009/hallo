# sk-dme-rcm - The root directory of the SUNKNOWLEDGE DME RCM Suite of Applications

## Prerequisites
### 1. Install NodeJS and JHipster (https://www.jhipster.tech/)
### 2. Install Docker Desktop (https://docs.docker.com/desktop/windows/install/)

## How To Run
The Service Registry resides in the service-registry folder.

Before running the service-registry, run: npm install from its path.

The API Gateway resides in the api-gateway folder.

While creating a microservice, follow this naming convention for the folder: ms-service_name

To build a specific microservice, go to the specific folder,  and run: ./mvnw clean package -Pprod jib:dockerBuild

To build everything from the root folder, run:  mvn clean package -Pprod jib:dockerBuild

#### To run the entire infrastructure with a single command, run the following command from the docker-compose folder:
#### docker-compose up