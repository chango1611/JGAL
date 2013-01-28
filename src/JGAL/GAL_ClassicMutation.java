package JGAL;

/**The GAL_ClassicMutation extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.*/
public class GAL_ClassicMutation extends GAL_GeneticOperator{
	
	/**Constructs a new GAL_ClassicMutation with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Mutation.
	*/
	public GAL_ClassicMutation(double prob){
		super(prob);
	}
	
	/**Applies the mutation for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the mutation.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperation{
		int i, size;
		GAL_Population offsprings= fathers.clone();
		for(GAL_Chromosome chrom: offsprings.getChromosomes()){
			size= chrom.size();
			for(i=0;i<size;i++){
				if(rand.nextDouble()<prob)
					config.getGeneConfiguration(i).changeValueTo(chrom.getGene(i));
			}
		}
		return offsprings;
	}
}