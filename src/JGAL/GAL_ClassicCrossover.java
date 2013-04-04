package JGAL;

/**The GAL_ClassicCrossover extends from GAL_Crossover and is one of the Genetic Operators implemented by default.
*<p>
*The classic crossover consists of choosing a random point p, which indicates the point of crossover between the two chromosomes, as shown below:
*<p>
*B= (b1b2...bpbp+1...bm) and
*<p>
*C= (c1c2...cpcp+1...cm)
*<p>
*Where bi are the genes of B and ci the genes of C. These chromosomes are replaced by new chromosomes of the form:
*<p>
*B'= (b1b2...bpcp+1...cm) and
*<p>
*C'= (c1c2...cpbp+1...bm)
*/
public class GAL_ClassicCrossover extends GAL_Crossover{

	/**Constructs a new GAL_ClassicCrossover with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Classic Crossover.
	*/
	public GAL_ClassicCrossover(double prob){
		super(prob);
	}
	
	/**Applies the classic crossover for a Population under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the classic crossover.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperationException{
		if(config.size()==1)
			return fathers;
			
		GAL_Chromosome[][] chrom1= distributeChromosomes(fathers.getChromosomes(),prob), //Distribuye segun la prob[0] en fathers y sobrevivientes
		chrom2;
		correctSizeFromFirstGroup(chrom1,2); //Si el primer grupo no es agrupable, modifica los grupos
		chrom2= groupChromosomes(chrom1[0],2); //Crea agrupaciones que pueden ser usadas para operaciones multi-chromosomas
		for(int i=0; i<chrom2.length; i++)
			chrom2[i]= crossover(chrom2[i],rand.nextInt(chrom2[i][0].size()-1)+1,config); //Aplica la funcion de cruzar
		chrom1[0]= GAL_Util.concatArrays(chrom2,new GAL_Chromosome[0]); //Concatena los hijos
		try{
			return new GAL_Population(GAL_Util.concatArrays(chrom1,new GAL_Chromosome[0]),config); //Concatena sobrevivientes con hijos
		}catch(NotValidChromosomeException e){
			throw new NotValidOperationException("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperationException("Not Valid Population Exception Catched");
		}
	}
	
	/**Applies the crossover between each pair of chromosomes under the restrictions given by the chromosome configuration.
	*@param fathers An array wich first two positions will be taken to be crossovered.
	*@param pos The position where the crossover is going to be applied.
	*@param config The chromosome configuration.
	*@return The offsprings for the two fathers.
	*@throws NotValidOperationException If an NotValidGeneException or ClassCastException gets catched.
	*/
	protected GAL_Chromosome[] crossover(GAL_Chromosome[] fathers, int pos, GAL_ChromosomeConfig config)throws NotValidOperationException{
		GAL_Chromosome[] offsprings= {fathers[0].clone(),fathers[1].clone()};
		try{
			config.modifyChromosome(offsprings[0],fathers[1],pos,fathers[1].size());
			config.modifyChromosome(offsprings[1],fathers[0],pos,fathers[0].size());
		}catch(NotValidGeneException e){
			throw new NotValidOperationException("Not Valid Gene Exception catched for a Classic Crossover");
		}catch(ClassCastException e){
			throw new NotValidOperationException("Class Cast Exception catched for a Classic Crossover");
		}
		return offsprings;
	}
}