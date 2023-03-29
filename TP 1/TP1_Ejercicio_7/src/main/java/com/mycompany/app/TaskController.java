package com.mycompany.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boots!";
  }

}
