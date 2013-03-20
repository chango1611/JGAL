package JAG;

public class AG_Cromosoma{
	
	private AG_Gen[] genes; //genes
	private double aptitud;
	
	public AG_Cromosoma(AG_Gen[] genes) throws NotValidCromosomeException{
		this.genes= genes;
		aptitud= 0d;
		if(genes==null || genes.length==0)
			throw new NotValidCromosomeException("El cromosoma debe tener al menos 1 gen");
	}
	
	public AG_Gen[] getGenes(){
		return genes;
	}
	
	public AG_Gen getGen(int pos){
		return genes[pos];
	}
	
	public void setGen(AG_Gen gen,int pos){
		genes[pos]= gen;
	}
	
	public void cambiarGenesAPartirDe(AG_Cromosoma ori, int pos){
		AG_Gen[] aux= ori.getGenes();
		System.arraycopy(aux,pos,genes,pos,genes.length-pos);
	}
	
	public double getAptitud(){
		return aptitud;
	}
	
	public void setAptitud(double aptitud){
		this.aptitud= aptitud;
	}
	
	public Object getRasgo(int pos){
		return genes[pos].getRasgo();
	}
	
	public int getTamano(){
		return genes.length;
	}
	
	public String toString(){
		String ret= "";
		for(AG_Gen gen: genes)
			ret+= gen.getRasgo() + ";";
		return ret;
	}
	
	public AG_Cromosoma clone(){
		AG_Gen[] genes2= new AG_Gen[genes.length];
		for(int i=0;i<genes.length;i++)
			genes2[i]= genes[i].clone();
		try{
			return new AG_Cromosoma(genes2);
		}catch(NotValidCromosomeException e){
			return this;
		}
	}
}