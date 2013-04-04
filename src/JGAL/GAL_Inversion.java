package JGAL;

/**The GAL_Inversion extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.
*<p>
*It consists of selecting two positions p1 and p2 randomly such that p1 &#60 p2 and
*invert the genes found between p1 and p2.
*<p>
*Warning: This operator could give an exception if the genes for the chromosome have not the same configuration.
*/
public class GAL_Inversion extends GAL_GeneticOperator{
	
	/**Constructs a new GAL_Inversion with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Inversion.
	*/
	public GAL_Inversion(double prob){
		super(prob);
	}
	
	/**Applies the inversion for a Population under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the inversion.
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
				for(;from<to;from++){
					aux= chrom.getGene(from).clone();
					try{
					config.getGeneConfiguration(from).setValueToFrom(chrom.getGene(from),chrom.getGene(to));
					config.getGeneConfiguration(to).setValueToFrom(chrom.getGene(to),aux);
					}catch(NotValidGeneException e){
						throw new NotValidOperationException("The gene configuration can't be used with this operation");
					}
					to--;
				}
			}
		}
		return offsprings;
	}
}