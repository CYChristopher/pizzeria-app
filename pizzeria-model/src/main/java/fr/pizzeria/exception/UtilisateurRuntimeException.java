package fr.pizzeria.exception;

/**
 * @author Quelqun
 *
 */
public class UtilisateurRuntimeException extends RuntimeException {

	/**
	 * @param message
	 * @param cause
	 */
	public UtilisateurRuntimeException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public UtilisateurRuntimeException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public UtilisateurRuntimeException(Throwable cause) {
		super(cause);

	}

}
