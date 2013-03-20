package JAG;

public class AG_Poblacion{
	//Arreglo de Cromosomas
	private AG_Cromosoma[] cromosomas;
	
	//Constructor base para AG_Poblacion usando un cromosoma de configuracion
	public AG_Poblacion(int tamano, AG_CromosomaConfig config) throws NotValidCromosomeException{
		cromosomas= new AG_Cromosoma[tamano];
		for(int i=0; i<tamano; i++)
			cromosomas[i]= config.createNewCromosoma();
	}
	
	//Constructor base para AG_Poblacion usando un arreglo de cromosomas
	public AG_Poblacion(AG_Cromosoma[] cromosomas){
		this.cromosomas= cromosomas;
	}
	
	public AG_Cromosoma getCromosoma(int pos){
		return cromosomas[pos];
	}
	
	public AG_Cromosoma[] getCromosomas(){
		return cromosomas;
	}
	
	public int getTamano(){
		return cromosomas.length;
	}
	
	public String toString(){
		String ret= "";
		for(AG_Cromosoma crom: cromosomas)
			ret+= crom + "\n";
		return ret;
	}
}