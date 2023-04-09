package com.mycompany.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boots!";
  }

  @PostMapping("/genericTask")
  @ResponseBody
  public String genericTask(@RequestBody Task task) {
    String response = "";
    try {
      HttpClient client = HttpClient.newHttpClient();

      // Comandos
      // Servicio-tarea corre en el puerto 8080, lo expongo como 9090
      String imageName = "sebmarch/tp1-e7-servicio:latest";
      String port_service = "9090";
      // String command = "docker run --rm -d --name=" + imageName + "_levantado -p "
      // + port_service + ":8080 " + imageName;
      // Crea el comando Docker que quieres ejecutar
      String[] command = { "docker", "run", "-d", "--rm", "-p", port_service + ":8080", imageName };
      for (int i = 0; i < command.length; i++) {
        System.out.print(command[i] + " ");
      }
      ProcessBuilder processBuilder = new ProcessBuilder(command);
      Process process = processBuilder.start();

      // El servicio está lvantandose
      Thread.sleep(20000);

      System.out.println("=====");
      // Obtén la salida del comando
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      // Espera a que el proceso termine
      int exitCode = process.waitFor();
      System.out.println("El comando terminó con el código de salida " + exitCode);
      System.out.println("=====");

      String ip_service = "127.0.0.1";
      String url = "http://" + ip_service + ":" + port_service + "/" + task.getName();
      System.out.println("Connecting with server...");
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .GET()
          .build();
      System.out.println("Peticion armada...");
      HttpResponse<String> response_service = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println("Response body:");
      System.out.println(response_service.body());

      response = response_service.body();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      response = "=== Hubo un error en el sevidor ===";
    }

    return response;
  }

}
