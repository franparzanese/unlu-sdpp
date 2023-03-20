package com.mycompany.app;

import java.util.logging.Logger;
import java.rmi.RemoteException;
import java.util.Random;

public class WeatherApp implements WeatherInterface {

  private Logger log;

  public WeatherApp(Logger log) {
    this.log = log;
  }

  @Override
  public String getWeather() throws RemoteException {
    try {
      log.info("GetWeather has been called!");
      Random r = new Random();
      int grados = r.nextInt(35);
      String clima = "Templado";
      if (grados <= 15) {
        clima = "Frío";
      } else if (grados >= 30) {
        clima = "Caluroso";
      }
      return "Clima actual: º" + grados + " --> " + clima;
    } catch (Exception e) {
      log.severe("Error al obtener la temperatura!\n" + e.getMessage());
      return "Clima actual: °00 ---> nulo";
    }

  }

}
