package com.mycompany.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WeatherInterface extends Remote{
  
  public String getWeather() throws RemoteException;

}
