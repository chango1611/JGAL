package JGAL;

/**An exception that is thrown when a Chromosome is not valid*/
public class NotValidChromosomeException extends Exception{
	protected static final long serialVersionUID = 1234;
	public NotValidChromosomeException(){super();}
	public NotValidChromosomeException(String message) { super(message); }
	public NotValidChromosomeException(String message, Throwable cause) { super(message, cause); }
	public NotValidChromosomeException(Throwable cause) { super(cause); }
}