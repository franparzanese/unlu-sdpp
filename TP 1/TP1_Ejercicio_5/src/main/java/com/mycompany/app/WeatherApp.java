package com.mycompany.app;

import java.util.Random;

public class WeatherApp implements WeatherInterface {

  @Override
  public String getWeather() {
    String response = "";
    Random r = new Random();
    int grados = r.nextInt(35);
    String clima = "Templado";
    if (grados <= 15) {
      clima = "Frío";
    } else if (grados >= 30) {
      clima = "Caluroso";
    }
    response = "Clima actual: º" + grados + " --> " + clima;
    return response;
  }

}
