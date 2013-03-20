package JAG;

public class AG_GenEnteroConfig extends AG_GenNumericoConfig<Integer>{
	
	//Constructor del AG_GenEnteroConfig
	public AG_GenEnteroConfig() throws NotValidGeneException{
		super(0,Integer.MAX_VALUE);
	}
	
	//Constructor del AG_GenEnteroConfig
	public AG_GenEnteroConfig(Integer min, Integer max) throws NotValidGeneException{
		super(min==null?0:min, max==null?Integer.MAX_VALUE:max);
		if(min!=null && min<0)
			throw new NotValidGeneException("Valor de min("+min+") < 0 para el AG_GenEnteroConfig");
		if(max!=null && max<0)
			throw new NotValidGeneException("Valor de max("+max+") < 0 para el AG_GenEnteroConfig");
	}
	
	//Asigna un valor aleatorio al AG_Gen `gen`
	public void setRandomValueTo(AG_Gen gen){
		int rasgo;
		rasgo= rand.nextInt(max-min)+min;
		gen.setRasgo(rasgo);	
	}

}