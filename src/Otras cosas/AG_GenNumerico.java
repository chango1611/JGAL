package JAG;

public class AG_GenNumerico<T extends Number & Comparable<T>> extends AG_Gen<T>{
	//Rangos a los que pertenece el numero
	protected T min;
	protected T max;
	
	//Constructor base del AG_GenNumerico
	public AG_GenNumerico(){
		this.min= null;
		this.max= null;
	}
	
	//Constructor del AG_GenNumerico
	public AG_GenNumerico(T rasgo){
		super(rasgo);
		this.min= null;
		this.max= null;
	}
	
	//Constructor del AG_GenNumerico
	public AG_GenNumerico(T min, T max){
		this.min= min;
		this.max= max;
	}
	
	//Constructor del AG_GenNumerico
	public AG_GenNumerico(T rasgo, T min, T max){// throws NotValidGeneException{
		super(rasgo);
		this.min= min;
		this.max= max;
		//if((min!=null && rasgo.compareTo(min)<0) || (max!=null && rasgo.compareTo(max)>0))
		//	throw new NotValidGeneException("Valor del rasgo fuera del rango [" + min + "," + max + "] para el Gen Numerico");
	}

	//Asigna un valor al Rasgo
	public void setRasgo(T rasgo){// throws NotValidGeneException{
		this.rasgo= rasgo;
		//if((min!=null && rasgo.compareTo(min)<0) || (max!=null && rasgo.compareTo(max)>0))
		//	throw new NotValidGeneException("Valor del rasgo fuera del rango [" + min + "," + max + "] para el Gen Numerico");
	}
	
	//Retorna el limite inferior para el gen
	public T getMin(){
		return min;
	}
	
	//Retorna el limite superior para el gen
	public T getMax(){
		return max;
	}
}