# Ejercicio 3

## Consigna

> Escriba un servidor de mensajes en colas, que permita a los clientes dejar un mensaje (identificando de alguna forma a quién se lo dejan), y bajar los mensajes que le están dirigidos.
> La comunicación entre cliente y servidor debe ser mediante sockets, y el servidor debe poder atender varios clientes a la vez.

## Consideraciones

- Los clientes se identifican con un nombre de usuario, solicitado al iniciar el programa.
- Los mensajes no se eliminan del servidor, esto se implementará en el ejercicio 4.

## Cómo ejecutar

### Servidor

Se debe posicionar en el directorio `server` y ejecutar el siguiente comando:
```
java -jar target/server-1.0.0-rc.jar [<puerto_servidor>]
```
Por defecto iniciará en el puerto 5000.

### Cliente

Se debe posicionar en el directorio `client` y ejecutar el siguiente comando:
```
java -jar target/client-1.0.0-rc.jar [[<direccion_servidor>] <puerto_servidor>]
```
Por defecto intentará conectarse a `localhost` al puerto 5000.

### Versiones utilizadas

Para la realización de este ejercicio se utilizó Java 17.0.6 y Maven 3.8.3.