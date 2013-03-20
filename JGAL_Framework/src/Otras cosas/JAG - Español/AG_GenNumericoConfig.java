package JAG;

public abstract class AG_GenNumericoConfig<T extends Number & Comparable<T>> extends AG_GenConfig<T>{
	//Rangos a los que pertenece el numero
	protected T min;
	protected T max;
	
	//Constructor del AG_GenNumericoConfig
	public AG_GenNumericoConfig(T min, T max)throws NotValidGeneException{
		this.min= min;
		this.max= max;
		if(min!=null && max!=null && max.compareTo(min)<0)
			throw new NotValidGeneException("Valor de min("+min+") > max("+max+") para el AG_GenNumericoConfig");
	}
	
	public void setValueTo(AG_Gen gen, T val)throws NotValidGeneException{
		gen.setRasgo(val);
		if(val.compareTo(min)<0 && val.compareTo(max)>0)
			throw new NotValidGeneException("El rasgo " + val + " no se encuentra en el rango ["+min+","+max+"] para el Gen Numerico");
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