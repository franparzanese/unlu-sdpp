Pasos para correr el servidor:
- Tener Docker instalado.
- En la carpeta Server/target correr el comando "java -jar demo-0.0.1-SNAPSHOT".
- Con una plataforma para APIs, como puede ser Postman o Insomnia, o un navegador realizar una petición HTTP POST 
  a http://localhost:8080/genericTask con el siguiente JSON:
  {
	"name": "randomNumberTask"
  }
