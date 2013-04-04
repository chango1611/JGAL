package JGAL;

/**The GAL_UniformCrossover extends from GAL_Crossover and is one of the Genetic Operators implemented by default.
*<p>
*The uniform crossover is a generalization of the Multi-Points Crossover,
*where for each bit in the offspring is decided with probability p (uniform probability),
*which parent will collaborate with that position.
*The second offspring will get the bits from the other parent.
*/
public class GAL_UniformCrossover extends GAL_Crossover{
	
	/**Uniform Probability.*/
	protected double uniformProb;
	
	/**Constructs a new GAL_UniformCrossover with a probability of ocurrence given by its first parameter and uniform probability given by the second parameter.
	*<p>
	*If any of the probabilities are out of the range [0,1], the probability will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Uniform Crossover. 
	*@param uniformProb Uniform Probability.
	*/
	public GAL_UniformCrossover(double prob, double uniformProb){
		super(prob);
		this.uniformProb= uniformProb;
	}
	
	/**Applies the uniform crossover for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the uniform crossover.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperationException{
		if(config.size()==1)
			return fathers;
			
		GAL_Chromosome[][] chrom1= distributeChromosomes(fathers.getChromosomes(),prob), //Distribuye segun la prob[0] en fathers y sobrevivientes
		chrom2;
		correctSizeFromFirstGroup(chrom1,2); //Si el primer grupo no es agrupable, modifica los grupos
		chrom2= groupChromosomes(chrom1[0],2); //Crea agrupaciones que pueden ser usadas para operaciones multi-chromosomas
		int i, j, size;
		for(i=0; i<chrom2.length; i++){
			size= chrom2[i][0].size();
			for(j=0;j<size;j++){
				if(rand.nextDouble()<uniformProb) //Si cambia de segmento
					chrom2[i]= crossover(chrom2[i],j,config); //Aplica la funcion de cruzar en la pos j
			}
		}
		chrom1[0]= GAL_Util.concatArrays(chrom2,new GAL_Chromosome[0]); //Concatena los hijos
		try{
			return new GAL_Population(GAL_Util.concatArrays(chrom1,new GAL_Chromosome[0]),config); //Concatena sobrevivientes con hijos
		}catch(NotValidChromosomeException e){
			throw new NotValidOperationException("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperationException("Not Valid Population Exception Catched");
		}
	}
	
	/**Applies the crossover betwen each pair of chromosomes under the restrictions given by the chromosome configuration.
	*@param fathers An array wich first two positions will be taken to be crossovered.
	*@param pos The position where the crossover is going to be applied.
	*@param config The chromosome configuration.
	*@return The offsprings for the two fathers.
	*@throws NotValidOperationException If an NotValidGeneException or ClassCastException gets catched.
	*/
	protected GAL_Chromosome[] crossover(GAL_Chromosome[] fathers, int pos, GAL_ChromosomeConfig config)throws NotValidOperationException{
		GAL_Chromosome[] offsprings= {fathers[0].clone(),fathers[1].clone()};
		try{
			config.modifyChromosome(offsprings[0],fathers[1],pos);
			config.modifyChromosome(offsprings[1],fathers[0],pos);
		}catch(NotValidGeneException e){
			throw new NotValidOperationException("Not Valid Gene Exception catched for a Uniform Crossover");
		}catch(ClassCastException e){
			throw new NotValidOperationException("Class Cast Exception catched for a Uniform Crossover");
		}
		return offsprings;
	}
	
	/**Gets the value from the field uniformProb.
	*@return The value from the field uniformProb.
	*/
	public double getUniformProb(){
		return uniformProb;
	}
}