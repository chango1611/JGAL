package JGAL;

/**The GAL_Population represents the population of chromosomes for one generation*/
public class GAL_Population{
	
	/**The chromosomes that represents the population*/
	private GAL_Chromosome[] chromosomes;
	
	/**Constructs a new GAL_Population of size given by the first parameter and chromosomes created under the configuration of the second parameter.
	*@param size The number of chromosomes in the population.
	*@param config The configuration used for the creation of the new chromosomes.
	*@throws NotValidChromosomeException If the configuration has an empty gene configuration.
	*@throws NotValidPopulationException If the size is less than 1. 
	*/
	public GAL_Population(int size, GAL_ChromosomeConfig config) throws NotValidChromosomeException, NotValidPopulationException{
		if(size<1)
			throw new NotValidPopulationException("Population must have at least 1 chromosome");
		chromosomes= new GAL_Chromosome[size];
		for(int i=0; i<size; i++)
			chromosomes[i]= config.createNewChromosome();
	}
	
	/**Constructs a new GAL_Population from an array of chromosomes given by the first parameter under the configuration of the second parameter.
	*@param chromosomes The array of chromosomes to be inserted at the population.
	*@param config The configuration for the chromosomes.
	*@throws NotValidChromosomeException If the array of chromosomes does not match with the configuration. 
	*@throws NotValidPopulationException If the length of the array of chromosomes is less than 1. 
	*/
	public GAL_Population(GAL_Chromosome[] chromosomes, GAL_ChromosomeConfig config)throws NotValidChromosomeException, NotValidPopulationException{
		if(chromosomes.length<1)
			throw new NotValidPopulationException("Population must have at least 1 chromosome");
		this.chromosomes= new GAL_Chromosome[chromosomes.length];
		for(int i=0;i<chromosomes.length;i++){
			this.chromosomes[i]= chromosomes[i];
			try{
				config.modifyChromosome(this.chromosomes[i],chromosomes[i],0,chromosomes[i].size());
			}catch(NotValidGeneException e){
				throw new NotValidChromosomeException("The chromosomes passed by parameter does not match with the configuration");
			}
		}
	}
	
	/**Constructs a new GAL_Population from an array of chromosomes.
	*@param chromosomes The array of chromosomes to be inserted at the population. 
	*/
	private GAL_Population(GAL_Chromosome[] chromosomes){
		this.chromosomes= chromosomes;
	}
	
	/**Gets the array of chromosomes.
	*@return The array of chromosomes.
	*/
	public GAL_Chromosome[] getChromosomes(){
		return chromosomes;
	}
	
	/**Gets the chromosome in the position denoted by the int parameter.
	*@param pos The position of the chromosome to be returned.
	*@return The chromosome in the position denoted by the int parameter.
	*/
	public GAL_Chromosome getChromosome(int pos){
		return chromosomes[pos];
	}
	
	/**Gets the chromosome with the bigger fitness in the population.
	*@return The chromosome with the bigger fitness in the population.
	*/
	public GAL_Chromosome getBestChromosome(){
		GAL_Chromosome winner= chromosomes[0];
		for(int i=1;i<chromosomes.length;i++){
			if(winner.compareTo(chromosomes[i]) < 0)
				winner= chromosomes[i];
		}
		return winner;
	}
	
	/**Returns the summation of all the fitness from a population.
	*@return The summation of all the fitness from a population.
	*/
	public double getTotalFitness(){
		double sum= 0.0;
		for(GAL_Chromosome chrom: chromosomes)
			sum+= chrom.getFitness();
		return sum;
	}
	
	/**Returns the size of the population.
	*@return The size of the population.
	*/
	public int size(){
		return chromosomes.length;
	}
	
	/**Returns a String object representing this GAL_Population.
	*@return A string representation of this GAL_Population.
	*/
	public String toString(){
		String ret= "";
		for(GAL_Chromosome chrom: chromosomes)
			ret+= chrom + "\n";
		return ret;
	}
	
	/**Returns a copy of this GAL_Population.
	*@return A copy of this GAL_Population.
	*/
	public GAL_Population clone(){
		GAL_Chromosome[] chrom2= new GAL_Chromosome[chromosomes.length];
		for(int i=0;i<chromosomes.length;i++)
			chrom2[i]= chromosomes[i].clone();
		return new GAL_Population(chrom2);
	}
}