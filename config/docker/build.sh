cd ../..
mvn clean package
cd config/docker
docker restart tomcat-dev
