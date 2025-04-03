#BackChallenge

Se aplico Arquitectura Hexagonal, Seguridad basada en Oauth2 con Keycloak, Junits, y una base de datos H2 (por tratarse de un desarrollo de un challenge. Podria implementarse con cualquier otra DB.).
Se implementaron Junits para algunas de las clases mas significativas.
Se realizaron pruebas desde postman

![image](https://github.com/user-attachments/assets/f4b80c94-8e06-4b32-ad62-c080c8da8032)

**INSTALACIÓN DE AMBIENTE**

1. Clonar el repositorio
2. Descomprimir **/keycloak-data.zip** y ejecutar **docker-compose up -d**
3. Ejecutar **./mvnw clean install**
4. Correr la aplicacion ejecutando ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
5. Verificar que la aplicación este corriendo en el puerto 8081

**EJECUCIÓN DE UNIT TESTS**
1. Correr ./mvnw test
   
<img width="1267" alt="image" src="https://github.com/user-attachments/assets/7907cae2-4107-4afa-879f-c9c1843083bd" />

**DOCUMENTACION DE LA API**

Los Endpoints disponibles estan documentados con swagger

http://localhost:8081/swagger-ui/index.html

Para utilizar la API, se puede utilizar la collection de postman que se encuentra en **/BankApp/BankChallenge.postman_collection.json**
Todos los endpoints requieren de Oauth2.
Para ello, se debe primero obtener el token de este modo:
POST http://localhost:8080/realms/bank-realm/protocol/openid-connect/token

<img width="700" alt="image" src="https://github.com/user-attachments/assets/0a743849-78bd-43ca-a4e3-cf9d90251b71" />


**ACERCA  DE LA SEGURIDAD Y KEYCLOAK**

La aplicación utiliza Keycloak.
Keycloak esta instalado en el docker que se levanta en el paso 2 de instalación de ambiente.
La configuracion de keycloak consiste de:
1. Un realm llamado bank-realm
2. Un Cliente bank-client-id y secret jX99XftE4g0SeDtb1QvG0mGcR7aKe7rx
3. Un Rol asociado al cliente, bankRole
   
   

Notas.
1. Se implementaron Interactor y Adapter tambien para Transferencias, pero finalmente no fue necesaria su utilizacion. Decidi mantenerlo igualmente en el repo.
