# Descripcion
Spring Open API Mongo Testing PoC

# Open API documentation
http://localhost:8080/openapi/swagger-ui.html

# Publish in Sonarqube
mvn clean verify sonar:sonar \
-Dsonar.projectKey=testing \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=3d26d849577ae69d4845a565ab1e677bf6a9ca7e

# Sonarqube url
http://localhost:9000