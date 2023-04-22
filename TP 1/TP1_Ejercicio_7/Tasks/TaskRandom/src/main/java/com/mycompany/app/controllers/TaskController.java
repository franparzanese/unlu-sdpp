package com.mycompany.app.controllers;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boots!";
  }

  @GetMapping("/randomNumberTask")
  public String randomNumberTask() {
    System.out.println("===== Random Task reached =====");
    String response = "Hubo un error";
    Random r = new Random();
    int nro = r.nextInt(100);
    response ="Numero aleatorio generado: " + Integer.toString(nro);
    return response;
  }

  // @PostMapping("/primeNumberTask")
  // @ResponseBody
  // public String primeNumberTask(){

  // }

}