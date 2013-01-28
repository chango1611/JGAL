package JGAL;

import java.util.Arrays;

/**The GAL_MultiPointCrossover extends from GAL_Crossover and is one of the Genetic Operators implemented by default.*/
public class GAL_MultiPointCrossover extends GAL_Crossover{
	
	/**Number of crossover points.*/
	private int nPoints;
	
	/**Constructs a new GAL_MultiPointCrossover with a probability of ocurrence given by its first parameter and number of crossover points given by the second parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Multi-Point Crossover. 
	*@param nPoints Number of crossover points. If this value is 1, then it will act similar to a Classic Crossover Operator.
	*@throws NotValidOperation If the number of crossover points is not a pair or less than 1.
	*/
	public GAL_MultiPointCrossover(double prob, int nPoints)throws NotValidOperation{
		super(prob);
		this.nPoints= nPoints;
		if(nPoints%2!= 0)
			throw new NotValidOperation("The number of points must be pair for a Multi-Point Crossover");
		if(nPoints < 1)
			throw new NotValidOperation("The number of points must be greater than 0 for a Multi-Point Crossover");
	}
	
	/**Applies the multi-point crossover for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the Multi-Point crossover.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperation{
		GAL_Chromosome[][] chrom1= distributeChromosomes(fathers.getChromosomes(),prob), //Distribuye segun la prob[0] en fathers y sobrevivientes
		chrom2;
		correctSizeFromFirstGroup(chrom1,2); //Si el primer grupo no es agrupable, modifica los grupos
		chrom2= groupChromosomes(chrom1[0],2); //Crea agrupaciones que pueden ser usadas para operaciones multi-chromosomas
		for(int i=0; i<chrom2.length; i++)
			chrom2[i]= crossover(chrom2[i],createPositions(chrom2[i][0].size()+1),config); //Aplica la funcion de cruzar
		chrom1[0]= GAL_Util.concatArrays(chrom2,new GAL_Chromosome[0]); //Concatena los hijos
		try{
			return new GAL_Population(GAL_Util.concatArrays(chrom1,new GAL_Chromosome[0]),config); //Concatena sobrevivientes con hijos
		}catch(NotValidChromosomeException e){
			throw new NotValidOperation("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperation("Not Valid Population Exception Catched");
		}
	}
	
	/**Applies the crossover betwen each pair of chromosomes under the restrictions given by the chromosome configuration.
	*@param fathers An array wich first two positions will be taken to be crossovered.
	*@param pos The positions where the crossover is going to be applied.
	*@param config The chromosome configuration.
	*@return The offsprings for the two fathers.
	*@throws NotValidOperation If an NotValidGeneException or ClassCastException gets catched; Or if the number of crossover points is bigger than the size.
	*/
	private GAL_Chromosome[] crossover(GAL_Chromosome[] fathers, int[] pos, GAL_ChromosomeConfig config)throws NotValidOperation{
		GAL_Chromosome[] offsprings= {fathers[0].clone(),fathers[1].clone()};
		if(nPoints > fathers[0].size())
			throw new NotValidOperation("The number of points must be less than the size of the chromosome for a Multi-Point Crossover");
		try{
			for(int i=0;i<pos.length;i+=2){
				config.modifyChromosome(offsprings[0],fathers[1],pos[i],pos[i+1]);
				config.modifyChromosome(offsprings[1],fathers[0],pos[i],pos[i+1]);
			}
		}catch(NotValidGeneException e){
			throw new NotValidOperation("Not Valid Gene Exception catched for a Multi-Point Crossover");
		}catch(ClassCastException e){
			throw new NotValidOperation("Class Cast Exception catched for a Multi-Point Crossover");
		}
		return offsprings;
	}
	
	/**Returns an array with the positions where the crossover is going to be applied.
	*@param size The actual size of a chromosome
	*@return An array with the positions where the crossover is going to be applied.
	*/
	private int[] createPositions(int size){
		Integer[] ret= new Integer[size];
		for(int i=0;i<size; i++)
			ret[i]= Integer.valueOf(i);
		GAL_Util.shuffle(ret);
		ret= Arrays.copyOfRange(ret,0,nPoints);
		Arrays.sort(ret);
		int[] ret2= new int[ret.length];
		for(int i=0;i<ret.length;i++)
			ret2[i]= ret[i];
		return ret2;
	}
}