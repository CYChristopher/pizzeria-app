package fr.pizzeria.admin.exception;

public class StockageException extends RuntimeException {

	public StockageException() {
		super();
	}

	public StockageException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockageException(String message) {
		super(message);
	}

	public StockageException(Throwable cause) {
		super(cause);
	}

	public StockageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	
	
}
