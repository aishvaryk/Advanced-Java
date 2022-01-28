package in.conceptarchitect.exceptions;

public class InvalidAmountException extends RuntimeException {
	public InvalidAmountException (String errMessage) {
		super(errMessage);
	}
}

