package JGAL;

public class NotValidOperation extends Exception{
	private static final long serialVersionUID = 1234;
	public NotValidOperation(){super();}
	public NotValidOperation(String message) { super(message); }
	public NotValidOperation(String message, Throwable cause) { super(message, cause); }
	public NotValidOperation(Throwable cause) { super(cause); }
}