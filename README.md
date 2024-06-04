# NWT_Projekat
Key Card Management System

# Run debug mode -X
mvn spring-boot:run -X

# gRPC protoc download & path var setup:
https://grpc.io/docs/protoc-installation/

# gRPC class generation
mvn compile -X
This will generate Java classes in the target/generated-sources/protobuf directory.

# gRPC client import fix: https://github.com/grpc/grpc-java/issues/5990#issuecomment-513208422

# Delete / Clean the maven build
mvn clean

# Compile project to .jar
mvn clean install -DskipTests

# Docker build
docker-compose up --build
