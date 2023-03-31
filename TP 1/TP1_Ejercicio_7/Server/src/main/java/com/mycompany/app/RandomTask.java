package com.mycompany.app;

import java.util.Random;

public class RandomTask {

  private String name;
  private int number;

  public RandomTask(String name, int number) {
    this.name = name;
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String ejecutarTareaRemota() {
    Random r = new Random();
    int nro = r.nextInt(this.number);
    return Integer.toString(nro);
  }
}
