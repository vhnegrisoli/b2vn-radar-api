# b2vn-radar-api

Back-end de API de radares do projeto B2VN utilizando Java 11, Spring Boot, Spring Security, OAuth2.0, Spring Cloud OpenFeign, QueryDSL e PostgreSQL 11.

# Dar build no projeto localmente

mvn clean install

# Rodando o projeto localmente

Na raiz da aplicação: 

mvn spring-boot:run

ou

cd /target
java -jar b2vn-radar-api.jar

# Criar rede b2vn overlay para aplicação
docker network create -d overlay --driver=bridge b2vn

# Build do projeto de radares B2VN Radar
docker build -t b2vn-radar-api .

# Rodar o projeto B2VN Radar
docker run --net=b2vn -p 8081:8081 -t b2vn-radar-api  

# Documentação da API com o Swagger
http://localhost:8081/swagger-ui.html
