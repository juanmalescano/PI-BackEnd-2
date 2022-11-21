cd eureka-service
mvn clean && mvn package -DskipTests
cd ..

cd movies-api/
mvn clean && mvn package -DskipTests
cd ..

cd gateway-api/
mvn clean && mvn package -DskipTests
cd ..

cd ms-bills/
mvn clean && mvn package -DskipTests
cd ..

cd users-service/
mvn clean && mvn package -DskipTests
cd ..

docker-compose up