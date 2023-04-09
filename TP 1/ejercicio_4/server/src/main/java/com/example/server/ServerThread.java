package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hilo encargado de controlar la conexión de un cliente.
 */
public class ServerThread implements Runnable {

	/**
	 * Socket de la conexión con el cliente.
	 */
	private Socket client;

	/**
	 * Cola de mensajes.
	 */
	private Queue queue;

	/**
	 * Canal de entrada de datos.
	 */
	private BufferedReader br;

	/**
	 * Canal de salida de datos.
	 */
	private PrintWriter pw;

	/**
	 * Nombre del usuario.
	 */
	private String user;

	public ServerThread(Socket client, Queue queue) throws Exception {
		this.client = client;
		this.queue = queue;
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		pw = new PrintWriter(client.getOutputStream(), true);
		user = "";
	}

	public void run() {
		try {
			// Solicita el nombre de usuario.
			user = br.readLine();
			while (user.isEmpty()) {
				pw.println("err");
				user = br.readLine();
			}
			pw.println("ack");
			// Sanitiza el nombre de usuario.
			user = user.trim().toLowerCase();

			// Solicita una opción del menú.
			while (true) {
				String op = br.readLine().toUpperCase();
				if (op.equals("L")) {
					retrieveMessages();
				} else if (op.equals("N")) {
					newMessage();
				} else if (op.equals("D")) {
					break;
				} else {
					pw.println("err");
				}
			}

			// El cliente solicitó desconectarse del servidor.
			client.close();
			System.out.println("Se ha desconectado un cliente (se cerrará un hilo).");
		} catch (Exception ex) {
			System.err.println("Ha ocurrido un error en una de las conexiones y se cerrará.");
		}
	}

	/**
	 * Envía los mensajes del usuario al cliente.
	 */
	private void retrieveMessages() throws IOException {
		pw.println("ack");
		ArrayList<Message> messages = queue.getMessages(user);
		if (messages.isEmpty()) {
			pw.println("end");
		} else {
			pw.println("Tienes " + messages.size() + " mensajes:");
			for (int i = 0; i < messages.size(); i++) {
				pw.println();
				pw.println(">>> " + messages.get(i).getMessage());
			}
			pw.println("end");
			// Espera respuesta de confirmación de los mensajes.
			String confirm = br.readLine().toUpperCase();
			while (!confirm.equals("S") && !confirm.equals("N")) {
				pw.println("err");
				confirm = br.readLine().toUpperCase();
			}
			if (confirm.equals("S")) {
				queue.removeMessages(messages);
			}
			pw.println("ack");
		}
	}

	/**
	 * Escribe un nuevo mensaje.
	 */
	private void newMessage() throws Exception {
		pw.println("ack");
		// Solicita el destinatario.
		String destination = br.readLine();
		while (destination.isEmpty()) {
			pw.println("err");
			destination = br.readLine();
		}
		pw.println("ack");
		// Solicita el mensaje.
		String message = br.readLine();
		while (message.isEmpty()) {
			pw.println("err");
			message = br.readLine();
		}
		// Instancia el mensaje y lo agrega a la cola.
		queue.addMessage(new Message(destination, message));
		pw.println("ack");
	}

}
