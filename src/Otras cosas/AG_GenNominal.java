package JAG;

public class AG_GenNominal extends AG_Gen<String>{
	
	//Lista de posibles valores para el String
	private String[] alelos;
		
	//Constructor del AG_GenNominal
	public AG_GenNominal(String[] alelos){
		this.alelos= alelos;
	}
	
	//Constructor del AG_GenNominal
	public AG_GenNominal(String rasgo, String[] alelos){ //throws NotValidGeneException{
		super(rasgo);
		this.alelos= alelos;
		/*boolean flag= false;
		for(int i=0;!flag && i<alelos.length;i++)
			flag= rasgo.equals(alelos[i]);
		if(!flag)
			throw new NotValidGeneException("Valor del rasgo no pertenece a los alelos  para el Gen Nominal");*/
	}
	
	//Constructor del AG_GenNominal
	public AG_GenNominal(String[] alelos, int pos){
		super(alelos[pos]);
		this.alelos= alelos;
	}
	
	//Asigna un valor al rasgo
	public void setRasgo(String rasgo) {//throws NotValidGeneException{
		this.rasgo= rasgo;
		/*boolean flag= false;
		for(int i=0;!flag && i<alelos.length;i++)
			flag= rasgo.equals(alelos[i]);
		if(!flag)
			throw new NotValidGeneException("Valor del rasgo no pertenece a los alelos para el Gen Nominal");*/
	}
	
	//Asigna un valor al rasgo
	public void setRasgo(int pos){
		this.rasgo= alelos[pos];
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