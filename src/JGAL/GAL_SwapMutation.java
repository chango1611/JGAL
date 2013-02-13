package JGAL;

/**The GAL_SwapMutation extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.
*<p>
*Warning: This operator could give an exception if the genes for the chromosome have not the same configuration.
*/
public class GAL_SwapMutation extends GAL_GeneticOperator{
	
	/**Probability of mutating a Gene.*/
	private double prob2;
	
	/**Constructs a new GAL_SwapMutation with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Swap.
	*/
	public GAL_SwapMutation(double prob){
		super(prob);
	}
	
	/**Applies the swap for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the mutation.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperationException{
		int i, size, from, to;
		GAL_Population offsprings= fathers.clone();
		GAL_Gene aux;
		for(GAL_Chromosome chrom: offsprings.getChromosomes()){
			if(rand.nextDouble()<prob){
				from= rand.nextInt(chrom.size());
				to= rand.nextInt(chrom.size()-from)+from;
				aux= chrom.getGene(from).clone();
				try{
					config.getGeneConfiguration(from).setValueToFrom(chrom.getGene(from),chrom.getGene(to));
					config.getGeneConfiguration(to).setValueToFrom(chrom.getGene(to),aux);
				}catch(NotValidGeneException e){
					throw new NotValidOperationException("The gene configuration can't be used with this operation");
				}
			}
		}
		return offsprings;
	}
}