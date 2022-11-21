Faltante:

Dockerisar todo el proyecto

No funciona el gateway por lo que hay que pegarle a los puertos de cada microservicio

movies 8086
user 8087
facturacion 8088
gateway 8080
Postgres 5432
Keycloak 8082
Mongo 27017
Eureka 8761

Order para levantar los microservicios:

Primero abrir en la carpeta raiz la terminal de git bash y ejecutar el docker compose para la instalacion de keycloak
y si al correr el compose no se levanto el reino, digirse a "add realm" e importar el archivo realm-digitalmedia.json

Despues levantar microservicios:
                                Eureka-service
                                gateway-api
                                movies-api
                                ms-bills
                                users-service



Para el ultimo punto, Feign desde bills lo que hice fue buscar un usuario al microservicio users el endpoint es /bills/findBy
