cd ../..
mvn clean package
cd config/docker
colima start --cpu 4 --memory 8
docker compose up -d
