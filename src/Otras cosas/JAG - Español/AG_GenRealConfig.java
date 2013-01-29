package JAG;

public class AG_GenRealConfig extends AG_GenNumericoConfig<Double>{
	
	//Constructor del AG_GenRealConfig
	public AG_GenRealConfig() throws NotValidGeneException{
		super(0d,Double.MAX_VALUE);
	}
	
	//Constructor del AG_GenRealConfig
	public AG_GenRealConfig(Double min, Double max) throws NotValidGeneException{
		super(min==null?0:min, max==null?Double.MAX_VALUE:max);
		if(min!=null && min<0)
			throw new NotValidGeneException("Valor de min("+min+") < 0 para el AG_GenRealConfig");
		if(max!=null && max<0)
			throw new NotValidGeneException("Valor de max("+max+") < 0 para el AG_GenRealConfig");
	}
	
	//Asigna un valor aleatorio al AG_Gen `gen`
	public void setRandomValueTo(AG_Gen gen){
		double rasgo;
		rasgo= rand.nextDouble()*(max-min)+min;
		gen.setRasgo(rasgo);	
	}

}