package com.example.server;

/**
 * Mensaje con un destinatario.
 */
public class Message {

	/**
	 * Nombre del destinatario.
	 */
	private String destination;

	/**
	 * Texto del mensaje.
	 */
	private String message;

	public Message(String destination, String message) throws IllegalArgumentException {
		if (destination.isEmpty()) throw new IllegalArgumentException("Se debe especificar el destinatario.");
		if (message.isEmpty()) throw new IllegalArgumentException("El mensaje no debe ser vacío.");
		this.destination = destination.trim().toLowerCase();
		this.message = message;
	}

	/**
	 * Retorna el nombre del destinatario.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Retorna el texto del mensaje.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Determina si el mensaje es para este usuario.
	 */
	public boolean isDestination(String user) {
		return getDestination().equals(user);
	}

}
