package JAG;

public class NotValidCromosomeException extends Exception{
	private static final long serialVersionUID = 1234;
	public NotValidCromosomeException(){super();}
	public NotValidCromosomeException(String message) { super(message); }
	public NotValidCromosomeException(String message, Throwable cause) { super(message, cause); }
	public NotValidCromosomeException(Throwable cause) { super(cause); }
}