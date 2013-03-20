package JGAL;

/**The GAL_ChromosomeConfig is a class for the configuration of a GAL_Chromosome*/
public class GAL_ChromosomeConfig{
	/**Array with the configuration of each gene of the chromosome*/
	protected GAL_GeneConfig[] configuration;
	
	/**Constructs a new GAL_ChromosomeConfig objet with an array of GAL_GeneConfig.
	*@param configuration Array of GAL_GeneConfig wich correspond with each gene configuration for the Chromosome.
	*@throws NotValidChromosomeException If the configuration array is empty or null
	*/
	public GAL_ChromosomeConfig(GAL_GeneConfig[] configuration) throws NotValidChromosomeException{
		this.configuration= configuration;
		if(configuration==null || configuration.length==0)
			throw new NotValidChromosomeException("The chromosome must hava at least one gene");
	}
	
	/**Creates and returns a new GAL_Chromosome using the method createNewGene.
	*@return The new GAL_Chromosome.
	*@throws NotValidChromosomeException If the configuration array is empty.
	*/
	public GAL_Chromosome createNewChromosome() throws NotValidChromosomeException{
		GAL_Gene[] genes= new GAL_Gene[configuration.length];
		for(int i=0; i<configuration.length; i++){
			genes[i]= configuration[i].createNewGene();
		}
		return new GAL_Chromosome(genes);
	}
	
	/**Gets the array of genes configuration.
	*@return The array of configuration.
	*/
	public GAL_GeneConfig[] getConfiguration(){
		return configuration;
	}
	
	/**Returns the size of the configuration array.
	*@return The size of the configuration array.
	*/
	public int size(){
		return configuration.length;
	}
	
	/**Gets the gene configuration in the position denoted by the int parameter.
	*@param pos The position of the gene configuration to be returned.
	*@return The gene configuration in the position denoted by the int parameter.
	*/
	public GAL_GeneConfig<?> getGeneConfiguration(int pos){
		return configuration[pos];
	}
	
	/**Modifies randomly a Chromosome at the gene position given by the integer argument.
	*@param chromosome The GAL_Chromosome to be modified.
	*@param pos The position where the GAL_Chromosome will be modified.
	*/
	public void modifyChromosome(GAL_Chromosome chromosome, int pos){
		configuration[pos].setRandomValueTo(chromosome.getGene(pos));
	}
	
	/**Modifies randomly a Chromosome at the genes between the positions min and max.
	*@param chromosome The GAL_Chromosome to be modified.
	*@param min The first position to be modified.
	*@param max The first position to not be modified after min.
	*/
	public void modifyChromosome(GAL_Chromosome chromosome, int min, int max){
		for(int pos=min; pos<max; pos++)
			configuration[pos].setRandomValueTo(chromosome.getGene(pos));
	}
	
	/**Modifies the first Chromosome by the second Chromosome at the gene position given by the integer argument.
	*@param chromosome The GAL_Chromosome to be modified.
	*@param chromosome2 The GAL_Chromosome to be used as modifier.
	*@param pos The position where the GAL_Chromosome will be modified.
	*@throws NotValidGeneException If the gene at pos from the first chromosome is not compatible with the gene from the second one.
	*@throws ClassCastException If the gene at pos from the second chromosome is not castable.
	*/
	public void modifyChromosome(GAL_Chromosome chromosome, GAL_Chromosome chromosome2, int pos)throws NotValidGeneException,ClassCastException{
		configuration[pos].setValueToFrom(chromosome.getGene(pos),chromosome2.getGene(pos));
	}
	
	/**Modifies the first Chromosome by the second Chromosome at the genes between the positions min and max.
	*@param chromosome The GAL_Chromosome to be modified.
	*@param chromosome2 The GAL_Chromosome to be used as modifier.
	*@param min The first position to be modified.
	*@param max The first position to not be modified after min.
	*@throws NotValidGeneException If at least one of the genes between min and max from the first chromosome is not compatible with the genes from the second one.
	*@throws ClassCastException If at least one of the genes between min and max from the second chromosome is not castable.
	*/
	public void modifyChromosome(GAL_Chromosome chromosome, GAL_Chromosome chromosome2, int min, int max)throws NotValidGeneException,ClassCastException{
		for(int pos=min; pos<max; pos++)
			configuration[pos].setValueToFrom(chromosome.getGene(pos),chromosome2.getGene(pos));
	}
	
	/**Returns a copy of this GAL_ChromosomeConfig.
	*@return A copy of this GAL_ChromosomeConfig.
	*/
	public GAL_ChromosomeConfig clone(){
		GAL_GeneConfig<?>[] configuration2= new GAL_GeneConfig<?>[configuration.length];
		for(int i=0;i<configuration.length;i++)
			configuration2[i]= configuration[i];
		try{
			return new GAL_ChromosomeConfig(configuration2);
		}catch(NotValidChromosomeException e){
			return this;
		}
	}
}