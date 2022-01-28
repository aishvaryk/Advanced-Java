package in.conceptarchitect.exceptions;

public class InvalidAccountNumberException extends RuntimeException {

	public InvalidAccountNumberException(String errMessage) {
		super(errMessage);
	}
}


