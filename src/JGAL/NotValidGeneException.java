package JGAL;

public class NotValidGeneException extends Exception{
	private static final long serialVersionUID = 1234;
	public NotValidGeneException(){super();}
	public NotValidGeneException(String message) { super(message); }
	public NotValidGeneException(String message, Throwable cause) { super(message, cause); }
	public NotValidGeneException(Throwable cause) { super(cause); }
}