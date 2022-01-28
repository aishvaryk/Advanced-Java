package in.conceptarchitect.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	public InvalidCredentialsException(String errMessage) {
		super(errMessage);
	}
}

