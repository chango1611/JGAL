package JAG;

public class AG_GenNominalConfig extends AG_GenConfig<String>{
	
	//Lista de posibles valores para el String
	private String[] alelos;
		
	//Constructor del AG_GenNominal
	public AG_GenNominalConfig(String[] alelos) throws NotValidGeneException{
		this.alelos= alelos;
		if(alelos==null || alelos.length==0)
			throw new NotValidGeneException("Cantidad de alelos posibles debe ser mayor a 0 para el AG_GenNominalConfig");
	}
	
	public void setValueTo(AG_Gen gen, String val)throws NotValidGeneException{
		gen.setRasgo(val);
		boolean flag= false;
		for(int i=0;!flag && i<alelos.length;i++)
			flag= val.equals(alelos[i]);
		if(!flag)
			throw new NotValidGeneException("Valor del rasgo " + val + " no pertenece a los alelos para el Gen Nominal");
	}
	
	//Asigna un valor aleatorio al AG_Gen `gen`
	public void setRandomValueTo(AG_Gen gen){
		int pos= rand.nextInt(alelos.length);
		gen.setRasgo(alelos[pos]);
	}
	
	//Retorna el valor del Alelo en la posicion `pos` 
	public String getAlelo(int pos){
		return alelos[pos];
	}

	//Retorna la cantidad de alelos posibles
	public int numberOfAlelos(){
		return alelos.length;
	}
}