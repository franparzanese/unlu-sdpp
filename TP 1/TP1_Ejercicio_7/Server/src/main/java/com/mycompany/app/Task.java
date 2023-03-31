package com.mycompany.app;

import java.io.Serializable;

public class Task implements Serializable {

  private String name;

  public Task() {
    super();
  }

  public Task(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "{name: " + this.name + "}";
  }

}
