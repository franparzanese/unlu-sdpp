package com.mycompany.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  private String ip_Destino;
  private int puerto_Destino;

  BufferedReader reader = null;

  public Client() {
    try {
      System.out.println("Ingrese la IP donde corre el servidor del clima");
      Scanner scanner = new Scanner(System.in);
      this.ip_Destino = scanner.nextLine();

      System.out.println("Ingrese el puerto donde corre el servidor del clima");
      this.puerto_Destino = Integer.parseInt(scanner.nextLine());

      Socket socket = new Socket(ip_Destino, puerto_Destino);

      InputStream inputStream = socket.getInputStream();
      OutputStream outputStream = socket.getOutputStream();

      reader = new BufferedReader(new InputStreamReader(inputStream));
      PrintWriter writer = new PrintWriter(outputStream, true);

      writer.println("");
      String linea;
      while ((linea = reader.readLine()) != null) {
        System.out.println(linea);
      }

      System.out.println("Completado");

      reader.close();
      scanner.close();
      socket.close();
    } catch (Exception e) {
      System.out.println("Error al leer el archivo: " + e.getMessage());
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        System.out.println("Error al cerrar el archivo: " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    Client cli = new Client();
  }
}
