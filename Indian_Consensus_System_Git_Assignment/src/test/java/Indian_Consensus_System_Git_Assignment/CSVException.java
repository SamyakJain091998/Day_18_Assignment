package Indian_Consensus_System_Git_Assignment;

public class CSVException extends Exception {
	enum CSVExceptionType {
		UNABLE_TO_PARSE;
	}

	CSVExceptionType type;

	public CSVException(String message, String name) {
		super(message);
		this.type = CSVExceptionType.valueOf(name);
	}

	public CSVException(String message, CSVExceptionType type) {
		super(message);
		this.type = type;
	}

	public CSVException(String message, CSVExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}
}
