# Ejercicio 5

## Consigna

> Escribir un servicio que devuelva información de clima del lugar donde reside el servidor. Esta información podrá generarse de forma aleatoria.
> Deberá ser realizado con un Servidor Web HTTP. Para ello considere la exposición de los métodos necesarios (puede haber alguno oculto) y utilizar un cliente que permita probar este funcionamiento.

## Cómo ejecutar

1. Acceder al directorio `jars`:
```
cd jars/
```

2. Ejecutar el servidor:
```
java -jar Server.jar
```

3. Ejecutar el cliente:
```
java -jar Client.jar
```

4. Ingresar en el cliente la dirección IP del servidor.

## Conclusión

Se pudo emplear el servidor utilizando RMI, permitiendo publicar un objeto en la misma máquina.
Las pruebas realizadas demuestran que el objeto pudo ser utilizado por el cliente, retornando la temperatura actual (generada aleatoriamente).