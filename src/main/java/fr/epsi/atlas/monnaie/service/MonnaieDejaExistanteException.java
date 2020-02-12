package fr.epsi.atlas.monnaie.service;

public class MonnaieDejaExistanteException extends Exception {

	private static final long serialVersionUID = 1L;

	public MonnaieDejaExistanteException(String message) {
		super(message);
	}

}
