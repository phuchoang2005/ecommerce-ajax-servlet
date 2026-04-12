cd ../..
mvn clean package
cd docker/docker
docker restart tomcat-dev
