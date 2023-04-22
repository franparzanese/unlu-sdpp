package com.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Clase principal para el cliente.
 */
public class Client {

	/**
	 * Dirección por defecto para el servidor.
	 */
	private static final String DEFAULT_DIRECTION = "localhost";

	/**
	 * Puerto por defecto para el servidor.
	 */
	private static final short DEFAULT_PORT = 5000;

	/**
	 * Socket de la conexión con el servidor.
	 */
	private Socket socket;

	/**
	 * Canal de entrada de datos.
	 */
	private BufferedReader br;

	/**
	 * Canal de salida de datos.
	 */
	private PrintWriter pw;

	/**
	 * Scanner para la entrada de datos del usuario.
	 */
	private Scanner sc;

	public Client(Socket socket) throws Exception {
		this.socket = socket;
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(socket.getOutputStream(), true);
		sc = new Scanner(System.in);
	}

	/**
	 * Espera a recibir algo del servidor, y verifica que sea un "ack".
	 */
	private boolean hasAck() throws IOException {
		return br.readLine().equals("ack");
	}

	/**
	 * Solicita un dato de entrada al usuario, y no lo confirma hasta no recibir
	 * el "ack" del servidor, volviéndolo a solicitar si es necesario.
	 */
	private String nonEmptyUserInput(String prompt) throws IOException {
		String input;
		do {
			do {
				System.out.print(prompt + ": ");
				input = sc.nextLine();
			} while (input.isEmpty());
			pw.println(input);
		} while (!hasAck());
		return input;
	}

	public void run() throws Exception {
		// Se ingresa el nombre.
		String input = nonEmptyUserInput("Ingrese su nombre");
		System.out.println();

		// Menú de opciones.
		while (true) {
			System.out.println("Ingrese una letra correspondiente a una de las siguientes opciones:");
			System.out.print("(L)eer mensajes, (N)uevo mensaje, (D)esconectarse: ");
			input = sc.nextLine().toUpperCase();
			if (input.equals("L")) {
				pw.println("L");
				if (hasAck()) retrieveMessages();
			} else if (input.equals("N")) {
				pw.println("N");
				if (hasAck()) newMessage();
			} else if (input.equals("D")) {
				pw.println("D");
				break;
			}
		}

		// Se solicitó desconectarse, se cierran el socket y el scanner.
		socket.close();
		sc.close();
	}

	/**
	 * Recupera los mensajes para el usuario.
	 */
	private void retrieveMessages() throws IOException {
		System.out.println();
		boolean hasMessages = false;
		String out = br.readLine();
		while (!out.equals("end")) {
			hasMessages = true;
			System.out.println(out);
			out = br.readLine();
		}
		if (hasMessages) {
			System.out.println();
			nonEmptyUserInput("¿Confirma la recepción de los mensajes (se eliminarán del servidor)? (S)í, (N)o");
		} else {
			System.out.println("No tienes mensajes para leer.");
		}
		System.out.println();
	}

	/**
	 * Escribe un nuevo mensaje.
	 */
	private void newMessage() throws IOException {
		System.out.println();
		// Destinatario.
		nonEmptyUserInput("Ingrese el nombre del destinatario");
		// Mensaje.
		nonEmptyUserInput("Ingrese el mensaje");
		System.out.println("Mensaje enviado.");
		System.out.println();
	}

	public static void main(String[] args) {
		// Dirección y puerto del servidor.
		String direction = DEFAULT_DIRECTION;
		short port = DEFAULT_PORT;
		try {
			if (args.length == 1) {
				port = Short.parseShort(args[0]);
			} else if (args.length > 1) {
				direction = args[0];
				port = Short.parseShort(args[1]);
			}
		} catch (Exception ex) {
			System.err.println("No se comprende el número de puerto.");
			System.exit(1);
		}

		// Intenta conectarse al servidor e inicia la aplicación.
		System.out.println("Conectándose a " + direction + ":" + port + "...");
		try {
			Socket socket = new Socket(direction, port);
			Client client = new Client(socket);
			client.run();
		} catch (UnknownHostException ex) {
			System.err.println("No se pudo resolver la dirección IP del servidor indicado.");
			System.exit(1);
		} catch (Exception ex) {
			System.err.println("Ha ocurrido un error en la conexión con el servidor.");
			System.exit(1);
		}
	}

}
