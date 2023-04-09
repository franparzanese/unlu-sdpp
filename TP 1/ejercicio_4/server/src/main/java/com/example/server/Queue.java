package com.example.server;

import java.util.ArrayList;

/**
 * Cola de mensajes.
 */
public class Queue {

	/**
	 * Lista de mensajes.
	 */
	private ArrayList<Message> messages;

	public Queue() {
		messages = new ArrayList<Message>();
	}

	/**
	 * Retorna la lista de mensajes.
	 */
	private ArrayList<Message> getMessages() {
		return messages;
	}

	/**
	 * Retorna una lista con los mensajes de un usuario.
	 */
	public ArrayList<Message> getMessages(String user) {
		ArrayList<Message> res = new ArrayList<Message>();
		for (Message msg : getMessages()) {
			if (msg.isDestination(user)) res.add(msg);
		}
		return res;
	}

	/**
	 * Retorna la cantidad de mensajes en cola.
	 */
	private int getSize() {
		return getMessages().size();
	}

	/**
	 * Agrega un mensaje a la cola.
	 */
	public void addMessage(Message msg) {
		getMessages().add(msg);
	}

	/**
	 * Elimina de la cola los mensajes indicados.
	 */
	public void removeMessages(ArrayList<Message> messages) {
		getMessages().removeAll(messages);
	}

}
