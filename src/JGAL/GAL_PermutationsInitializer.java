package JGAL;

/**The GAL_PermutationInitializer is the permutation initializer for the population.
*/
public class GAL_PermutationsInitializer extends GAL_Initializer{
	
	/**Initializes a new GAL_Initializer*/
	public GAL_PermutationsInitializer(){}
	
	/**Initialize a new population.
	*@param chromosomeConfig The chromosome configuration.
	*@param size The population size.
	*@return A new population that follows the chromosome configuration.
	*/
	public GAL_Population initialize(GAL_ChromosomeConfig chromosomeConfig, int size)throws NotValidChromosomeException,NotValidPopulationException{
		GAL_Chromosome[] chromosomes= new GAL_Chromosome[size];
		GAL_IntegerGeneConfig aux= null;
		
		try{
			aux= (GAL_IntegerGeneConfig)chromosomeConfig.getGeneConfiguration(0);
		}catch(Exception e){
			throw new NotValidChromosomeException("The actual configuration can't be used with this initializer.\nAll genes must be Integer");
		}
		
		if((aux.getMax()-aux.getMin())< chromosomeConfig.size())
			throw new NotValidChromosomeException("The actual configuration can't be used with this initializer\nMax-Min>=Size of the chromosome");
		
		for(GAL_GeneConfig<?> cc: chromosomeConfig.getConfiguration()){
			try{
				if(((GAL_IntegerGeneConfig) cc).getMax()!=aux.getMax() || ((GAL_IntegerGeneConfig) cc).getMin()!=aux.getMin())
					throw new NotValidChromosomeException("The actual configuration can't be used with this initializer\nAll genes don't follow the same configuration");
			}catch(Exception e){
				throw new NotValidChromosomeException("The actual configuration can't be used with this initializer.\nAll genes must be Integer");
			}
		}
		
		GAL_Gene[] chrom, chrom2;
		int i,j;
		for(i=0;i<size;i++){
			chrom= new GAL_Gene[aux.getMax()-aux.getMin()];
			for(j=aux.getMin();j<aux.getMax();j++)
				chrom[j-aux.getMin()]= new GAL_Gene((Integer)j);
			GAL_Util.shuffle(chrom);
			chrom2= new GAL_Gene[chromosomeConfig.size()];
			System.arraycopy(chrom,0,chrom2,0,chromosomeConfig.size());
			chromosomes[i]= new GAL_Chromosome(chrom2);
		}
		return new GAL_Population(chromosomes,chromosomeConfig);
	}
}