package JGAL;

/**The GAL_ClassicMutation extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.
*The mutation changes the allele that has a gene,
*for example in the case of a bit string,
*applying on a gene mutation change from 0 to 1 or vice versa.
*<p>
*For each gene of each chromosome it must:
*<p>
*<ul type="disc">
*	<li><p><tt>Generate a random number r in the range [0,1]</tt></p></li>
*	<li><p><tt>If r &#60 pm then the gene is mutated</tt></p></li>
*</ul>
*<p>
*Where pm is the probability of occurrence of the mutation.
*/
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
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperationException{
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