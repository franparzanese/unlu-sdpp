# Ejercicio 4

## Consigna

> Modifique el programa anterior para que el mensaje de la cola sea borrado por el servidor una vez que el cliente confirma, mediante un mensaje de tipo ack, que efectivamente recibió el mensaje que estaba en la cola.

## Consideraciones

- Cuando recibe mensajes, el usuario tiene la posibilidad de confirmarlos o no. Se eliminarán del servidor en base a esta confirmación.

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