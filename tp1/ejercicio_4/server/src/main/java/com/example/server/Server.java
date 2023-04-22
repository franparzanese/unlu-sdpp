package com.example.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase principal para el servidor.
 */
public class Server {

	/**
	 * Puerto por defecto del servidor.
	 */
	private static final short DEFAULT_PORT = 5000;

	public static void main(String[] args) {
		// Puerto a utilizar.
		short port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Short.parseShort(args[0]);
			} catch (Exception ex) {
				System.err.println("No se comprende el número de puerto.");
				System.exit(1);
			}
		}

		// Levanta el servidor.
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("Servidor iniciado en el puerto " + port + ".");
		} catch (Exception ex) {
			System.err.println("No se pudo iniciar el servidor en el puerto "
					+ port + ". Verifique que esté libre.");
			System.exit(1);
		}

		// Levanta la cola de mensajes.
		Queue queue = new Queue();

		// Queda en espera de conexiones de clientes.
		while (true) {
			try {
				Socket client = ss.accept();
				System.out.println("Conexión entrante...");
				Thread thread = new Thread(new ServerThread(client, queue));
				thread.start();
				System.out.println("Conexión establecida (se abrió un nuevo hilo).");
			} catch (Exception ex) {
				System.err.println("No se pudo establecer conexión.");
			}
		}
	}

}
