package JAG;

public class AG_Configuracion{

	private AG_CromosomaConfig cromosomaConfig;
	private AG_FuncionAptitud aptitud;

	public AG_Configuracion(AG_CromosomaConfig cromosomaConfig, AG_FuncionAptitud aptitud){
		this.cromosomaConfig= cromosomaConfig;
		this.aptitud= aptitud;
	}
	
	public void calcularAptitudes(AG_Poblacion pob){
		int tam= pob.getTamano();
		for(int i=0;i<tam;i++)
			aptitud.calcularAptitud(pob.getCromosoma(i));
	}
	
	public AG_Poblacion createNewPoblacion(int t)throws NotValidCromosomeException{
		return new AG_Poblacion(t,cromosomaConfig);
	}
}