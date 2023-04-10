Pasos para correr el servidor localmente:
- Tener Docker instalado.
- En la carpeta Server/target correr el comando "java -jar demo-0.0.1-SNAPSHOT".
- Con una plataforma para APIs, como puede ser Postman o Insomnia, o un navegador realizar una petición HTTP POST 
  a http://localhost:8080/genericTask con el siguiente JSON:
  {
	"name": "randomNumberTask"
  }
  El servidor levantará un contenedor docker a partir de la imagen sebmarch/tp1-e7-servicio, y luego de 20 segundos hará una petición HTTP a dicho servicio, el cual generará un número aleatorio para luego devolverlo al cliente.