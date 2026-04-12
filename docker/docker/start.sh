cd ../..
mvn clean package
cd docker/docker
colima start --cpu 4 --memory 8
docker compose up -d
