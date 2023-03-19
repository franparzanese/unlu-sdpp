package com.mycompany.app;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 * Hello world!
 */
public class ServerClima {

	private int portNumber = 8080;

	private String craftResponse(String text) {
		String response = "HTTP/1.1 200 OK\n";
		response += "Content-Type: text/htm\n";
		response += "<html>\n<body>\n";
		response += "<h1>" + text + "</h1>\n";
		response += "</body>\n</html>\n";
		return response;
	}

	private void handleRequest(Socket clientSocket) throws IOException {
		System.out.println("Cliente entrante!");

		InputStream inputStream = clientSocket.getInputStream();
		OutputStream outputStream = clientSocket.getOutputStream();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter writer = new PrintWriter(outputStream, true);

		try {
			System.out.println("Leyendo...");
			reader.readLine();

			WeatherApp app = new WeatherApp();
			String res = craftResponse(app.getWeather());
			System.out.println("===");
			System.out.println(res);
			System.out.println("===");

			writer.println(res);
		} catch (Exception e) {
			writer.println("Hubo un error.");
		}

		System.out.println("Cerrando socket");
		writer.close();
		reader.close();
		clientSocket.close();
	}

	public ServerClima() {
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				handleRequest(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Error al crear el servidor: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ServerClima serv = new ServerClima();
	}
}
