package JGAL;

import java.util.Arrays;

/**The GAL_ShuffleMutation extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.
*<p>
*Warning: This operator could give an exception if the genes for the chromosome have not the same configuration.
*/
public class GAL_ShuffleMutation extends GAL_GeneticOperator{
	
	/**Probability of mutating a Gene.*/
	private double prob2;
	
	/**Constructs a new GAL_ShuffleMutation with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Shuffle Mutation.
	*/
	public GAL_ShuffleMutation(double prob){
		super(prob);
	}
	
	/**Applies the Shuffle Mutation for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the mutation.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperationException{
		int i, size, from, to;
		GAL_Population offsprings= fathers.clone();
		GAL_Gene[] shuf;
		GAL_Gene aux;
		for(GAL_Chromosome chrom: offsprings.getChromosomes()){
			if(rand.nextDouble()<prob){
				from= rand.nextInt(chrom.size());
				to= rand.nextInt(chrom.size()-from)+from;
				shuf= Arrays.copyOfRange(chrom.getGenes(),from,to);
				for(i=0;i<shuf.length;i++)
					shuf[i]= shuf[i].clone();
				GAL_Util.shuffle(shuf);
					
				for(i=0;i<shuf.length;i++){
					try{
						config.getGeneConfiguration(from+i).setValueToFrom(chrom.getGene(from+i),shuf[i]);
					}catch(NotValidGeneException e){
						throw new NotValidOperationException("The gene configuration can't be used with this operation");
					}
				}
			}
		}
		return offsprings;
	}
}