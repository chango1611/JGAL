package JGAL;

/**An exception that is thrown when a Gene is not valid*/
public class NotValidGeneException extends Exception{
	protected static final long serialVersionUID = 1234;
	public NotValidGeneException(){super();}
	public NotValidGeneException(String message) { super(message); }
	public NotValidGeneException(String message, Throwable cause) { super(message, cause); }
	public NotValidGeneException(Throwable cause) { super(cause); }
}