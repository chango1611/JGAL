package JGAL;

/**The GAL_ChromosomeMutation extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.*/
public class GAL_ChromosomeMutation extends GAL_GeneticOperator{
	
	/**Probability of mutating a Gene.*/
	protected double prob2;
	
	/**Constructs a new GAL_ChromosomeMutation with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Mutation.
	*@param prob2 Probability of mutating a Gene.
	*/
	public GAL_ChromosomeMutation(double prob, double prob2){
		super(prob);
		this.prob2= prob2;
	}
	
	/**Applies the mutation for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the mutation.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperationException{
		int i, size;
		GAL_Population offsprings= fathers.clone();
		for(GAL_Chromosome chrom: offsprings.getChromosomes()){
			if(rand.nextDouble()<prob){
				size= chrom.size();
				for(i=0;i<size;i++){
					if(rand.nextDouble()<prob2)
						config.getGeneConfiguration(i).changeValueTo(chrom.getGene(i));
				}
			}
		}
		return offsprings;
	}
	
	/**Gets the value from the field prob2.
	*@return The value from the field prob2.
	*/
	public double getSecondProb(){
		return prob2;
	}
}