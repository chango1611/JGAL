package JAG;

public class AG_CromosomaConfig{
	//configuracion de los genes
	private AG_GenConfig[] configuracion;
	
	//Constructor base para AG_CromosomaConfig
	public AG_CromosomaConfig(AG_GenConfig[] configuracion) throws NotValidCromosomeException{
		this.configuracion= configuracion;
		if(configuracion==null || configuracion.length==0)
			throw new NotValidCromosomeException("El cromosoma debe tener al menos 1 gen");
	}
	
	//Crea un nuevo cromosoma
	public AG_Cromosoma createNewCromosoma() throws NotValidCromosomeException{
		AG_Gen[] genes= new AG_Gen[configuracion.length];
		for(int i=0; i<configuracion.length; i++){
			genes[i]= configuracion[i].createNewGen();
		}
		return new AG_Cromosoma(genes);
	}
	
	//Modifica aleatoriamente el gen de la posicion `pos` en el `cromosoma`
	public void modificarCromosoma(AG_Cromosoma cromosoma, int pos){
		configuracion[pos].setRandomValueTo(cromosoma.getGen(pos));
	}
	
	//Modifica aleatoriamente los genes en el rango [`min`,`max`) en el `cromosoma`
	public void modificarCromosoma(AG_Cromosoma cromosoma, int min, int max){
		for(int pos=min; pos<max; pos++)
			configuracion[pos].setRandomValueTo(cromosoma.getGen(pos));
	}
	
	//Modifica el gen en la posicion `pos` en el `cromosoma` a partir del `cromosoma2`
	public void modificarCromosoma(AG_Cromosoma cromosoma, AG_Cromosoma cromosoma2, int pos)throws NotValidGeneException{
		configuracion[pos].setValueToFrom(cromosoma.getGen(pos),cromosoma2.getGen(pos));
	}
	
	//Modifica los genes en el rango [`min`,`max`) en el `cromosoma` a partir del `cromosoma2`
	public void modificarCromosoma(AG_Cromosoma cromosoma, AG_Cromosoma cromosoma2, int min, int max)throws NotValidGeneException{
		for(int pos=min; pos<max; pos++)
			configuracion[pos].setValueToFrom(cromosoma.getGen(pos),cromosoma2.getGen(pos));
	}
}