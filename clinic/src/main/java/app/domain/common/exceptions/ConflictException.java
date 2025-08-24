package app.domain.common.exceptions;

public class ConflictException extends RuntimeException {
	public ConflictException(String message) {
		super(message);
	}
}
