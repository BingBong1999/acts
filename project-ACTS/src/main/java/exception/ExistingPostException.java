package exception;

public class ExistingPostException extends Exception {

	private static final long serialVersionUID = 2L;

	public ExistingPostException() {
		super();
	}

	public ExistingPostException(String arg0) {
		super(arg0);
	}
}
