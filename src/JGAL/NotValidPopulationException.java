package JGAL;

public class NotValidPopulationException extends Exception{
	protected static final long serialVersionUID = 1234;
	public NotValidPopulationException(){super();}
	public NotValidPopulationException(String message) { super(message); }
	public NotValidPopulationException(String message, Throwable cause) { super(message, cause); }
	public NotValidPopulationException(Throwable cause) { super(cause); }
}