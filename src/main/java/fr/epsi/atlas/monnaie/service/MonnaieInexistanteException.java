package fr.epsi.atlas.monnaie.service;

public class MonnaieInexistanteException extends Exception {

	private static final long serialVersionUID = 1L;

	public MonnaieInexistanteException(String message) {
		super(message);
	}

}
