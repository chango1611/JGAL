package JGAL;

/**The GAL_Chromosome represents any kind of Chromosome*/
public class GAL_Chromosome implements Comparable<GAL_Chromosome>{
	
	/**The genes that represents this Chromosome*/
	protected GAL_Gene[] genes;
	/**The fitness value for this Chromosome*/
	protected double fitness;
	
	/**Constructs a new GAL_Chromosome which genes are passed as an argument.
	*@param genes Array of the genes of the Chromosome.
	*@throws NotValidGeneException If the array of genes is empty or null.
	*/
	public GAL_Chromosome(GAL_Gene[] genes) throws NotValidChromosomeException{
		this.genes= genes;
		fitness= 0d;
		if(genes==null || genes.length==0)
			throw new NotValidChromosomeException("The chromosome must have at least one gene");
	}
	
	/**Constructs a new GAL_Chromosome wich genes and fitness are passed as arguments.
	*@param genes Array of the genes of the Chromosome.
	*@param fitness The fitness value for the Chromosome.
	*@throws NotValidGeneException If the array of genes is empty or null.
	*/
	public GAL_Chromosome(GAL_Gene[] genes, double fitness) throws NotValidChromosomeException{
		this.genes= genes;
		this.fitness= fitness;
		if(genes==null || genes.length==0)
			throw new NotValidChromosomeException("The chromosome must have at least one gene");
	}
	
	/**Gets the array of genes.
	*@return The array of genes.
	*/
	public GAL_Gene[] getGenes(){
		return genes;
	}
	
	/**Gets the gene in the position denoted by the int parameter.
	*@param pos The position of the gene to be returned.
	*@return The gene in the position denoted by the int parameter.
	*/
	public GAL_Gene getGene(int pos){
		return genes[pos];
	}
	
	/**Sets a gene to an position of the array of genes.
	*@param gene The GAL_Gene which is going to be assignated.
	*@param pos The position which is going to be modified.
	*/
	public void setGene(GAL_Gene gene,int pos){
		genes[pos]= gene;
	}
	
	/**Gets the fitness of the Chromosome.
	*@return The fitness of the Chromosome.
	*/
	public double getFitness(){
		return fitness;
	}
	
	/**Sets the fitness of the Chromosome.*/
	public void setFitness(double fitness){
		this.fitness= fitness;
	}
	
	/**Gets the trait for the gene in the position denoted by the int parameter.
	*@param pos The position of the gene which trait is going to be returned.
	*@return The trait for the gene in the position denoted by the int parameter.
	*/
	public Object getTrait(int pos){
		return genes[pos].getTrait();
	}
	
	/**Returns the size of the genes array.
	*@return The size of the genes array.
	*/
	public int size(){
		return genes.length;
	}
	
	/**Returns a String object representing this GAL_Chromosome.
	*@return A string representation of this GAL_Chromosome.
	*/
	public String toString(){
		String ret= "";
		for(GAL_Gene gene: genes)
			ret+= gene.getTrait() + ";";
		return ret;
	}
	
	/**Returns a copy of this GAL_Chromosome.
	*@return A copy of this GAL_Chromosome.
	*/
	public GAL_Chromosome clone(){
		GAL_Gene[] genes2= new GAL_Gene[genes.length];
		for(int i=0;i<genes.length;i++)
			genes2[i]= genes[i].clone();
		try{
			return new GAL_Chromosome(genes2,fitness);
		}catch(NotValidChromosomeException e){
			return this;
		}
	}
	
	/**Compares two GAL_Chromosomes objects by their fitness
	*@param other The GAL_Chromosome to be compared.
	*@return the value 0 if this GAL_Chromosome fitness is equal to the argument GAL_Chromosome fitness;
	*a value less than 0 if this GAL_Chromosome fitness is numerically less than the argument GAL_Chromosome fitness;
	*and a value greater than 0 if this GAL_Chromosome fitness is numerically greater than the argument GAL_Chromosome fitness.
	*/
	public int compareTo(GAL_Chromosome other){
		if(fitness > other.getFitness()) return 1;
		if(fitness < other.getFitness()) return -1;
		return 0;
	}
}