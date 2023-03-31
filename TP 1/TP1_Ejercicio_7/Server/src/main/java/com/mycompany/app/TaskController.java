package com.mycompany.app;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.WaitContainerResultCallback;
import com.github.dockerjava.api.model.ContainerConfig;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.core.DockerClientBuilder;

@RestController
public class TaskController {

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boots!";
  }

  @PostMapping("/genericTask")
  @ResponseBody
  public String genericTask(@RequestBody Task task) {
    System.out.println("Json que llega: " + task.toString());

    try {
      // Construye el comando para compilar el archivo Java
      String[] javacCmd = { "javac", "MiClase.java" };

      // Compila el archivo Java
      ProcessBuilder pb = new ProcessBuilder(javacCmd);
      pb.directory(new File("ruta/al/directorio/del/archivo/java"));
      Process p = pb.start();
      int exitCode = p.waitFor();

      // Construye el comando para crear y ejecutar el contenedor Docker
      String[] dockerCmd = { "docker", "run", "-it", "--rm", "-v", "/ruta/al/directorio/del/archivo/java:/app",
          "openjdk:latest", "java", "MiClase" };

      // Crea y ejecuta el contenedor Docker
      pb = new ProcessBuilder(dockerCmd);
      p = pb.start();
      exitCode = p.waitFor();

      // Imprime el código de salida del proceso
      System.out.println("Código de salida: " + exitCode);
    } catch (Exception e) {
      System.out.println("=== Hubo un error ===");
    }

    return "Greetings from Spring Bootsa!";
    // System.out.println("Llega un json: " + task);
    // return task.getName();
  }

  public String ejecutarTareaRemota(String taskName) {
    String response = "Ese servicio es invlido";
    try {
      switch (taskName) {
        case "random":
          response = taskRandom();
          break;

        default:
          break;
      }
    } catch (Exception e) {

    }

    return response;
  }

  public String taskRandom() {
    String response = "Hubo un error en servicio Random";
    try {
      // Crea una instancia del cliente de docker
      DockerClient dockerClient = DockerClientBuilder.getInstance().build();

      // Levanta el contenedor Random
      String containerId = dockerClient.createContainerCmd("Task-Random").exec().getId();
      dockerClient.startContainerCmd(containerId).exec();

      dockerClient.waitContainerCmd(containerId).exec(new WaitContainerResultCallback()).awaitStatusCode();
      ContainerExitResult exitResult = dockerClient.inspectContainerCmd(containerId).exec().getState().getExitCode();
      System.out.println("Valor de salida: " + exitResult);

    } catch (Exception e) {

    }
    return response;
  }

}
