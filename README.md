###shopping-cart
Apis developed in java for a shopping cart

##### Indicaciones e instrucciones para desplegar el proyecto :
- Java JDK en su versión 15 y Maven.

- La base de datos para pruebas se creó en PostreSQL con el nombre de ecommerceDB (se    adjunta una carpeta llamada .*resources* que incluye un script con datos de prueba y diagrama de la base de datos).

- Se deberá generar llaves pública y privada para el correcto funcionamiento de la seguridad en la aplicación. Las llaves las generamos con el mismo JDK de Java con el siguiente comando y agregar a la carpeta resources del proyecto java el .jks generado y modificar las variables security en el properties.
######keytool -genkey -alias "jwt-sign-key" -keyalg RSA -keystore jwt-keystore.jks -keysize 4096

- Si se desea ejecutar la base de datos de una manera mas practica, se adjuntará un scrpit de Docker en la carpeta resources.

#### Path para acceder a la documentación de la API:   http://localhost:8080/swagger-ui/

