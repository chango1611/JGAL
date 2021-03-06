package JGAL;

/**An exception that is thrown when an Operation is not valid*/
public class NotValidOperationException extends Exception{
	protected static final long serialVersionUID = 1234;
	public NotValidOperationException(){super();}
	public NotValidOperationException(String message) { super(message); }
	public NotValidOperationException(String message, Throwable cause) { super(message, cause); }
	public NotValidOperationException(Throwable cause) { super(cause); }
}