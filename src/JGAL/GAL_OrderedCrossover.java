package JGAL;

import java.util.LinkedList;
import java.util.Arrays;

/**The GAL_OrderedCrossover extends from GAL_Crossover and is one of the Genetic Operators implemented by default.
*<p>
*To create the first offspring p1 is copied and the selected subset of cites is reordered to match that found in p2.
*The second offspring is similar, a copy of p2 with p1's ordering imposed.
*/
public class GAL_OrderedCrossover extends GAL_Crossover{

	/**Constructs a new GAL_OrderedCrossover with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the Ordered Crossover.
	*/
	public GAL_OrderedCrossover(double prob){
		super(prob);
	}
	
	/**Applies the ordered crossover for a Population under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the ordered crossover.
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
			chrom2[i]= crossover(chrom2[i],createPositions(chrom2[i][0].size()+1),config); //Aplica la funcion de cruzar
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
	*@param pos The positions where the crossover is going to be applied.
	*@param config The chromosome configuration.
	*@return The offsprings for the two fathers.
	*@throws NotValidOperationException If a NotValidChromosomeException or ClassCastException gets catched.
	*/
	protected GAL_Chromosome[] crossover(GAL_Chromosome[] fathers, int[] pos, GAL_ChromosomeConfig config)throws NotValidOperationException{
		LinkedList<GAL_Gene> offspring1= new LinkedList<GAL_Gene>(), offspring2= new LinkedList<GAL_Gene>();
		GAL_Chromosome[] offsprings= {fathers[0].clone(),fathers[1].clone()};
		int i;
		for(i=0;i<pos[0];i++){
			offspring1.add(offsprings[0].getGene(i));
			offspring2.add(offsprings[1].getGene(i));
		}
		for(i=pos[1];i<fathers[0].size();i++){
			offspring1.add(offsprings[0].getGene(i));
			offspring2.add(offsprings[1].getGene(i));
		}
		for(i=pos[0];i<pos[1];i++){
			offspring1.add(offsprings[0].getGene(i));
			offspring2.add(offsprings[1].getGene(i));
		}
		try{
			offsprings= new GAL_Chromosome[] {new GAL_Chromosome(offspring1.toArray(new GAL_Gene[0])),new GAL_Chromosome(offspring2.toArray(new GAL_Gene[0]))};
		}catch(ClassCastException e){
			throw new NotValidOperationException("Class Cast Exception catched for a Multi-Point Crossover");
		}catch(NotValidChromosomeException e){
			throw new NotValidOperationException("Not Valid Chromosome Exception catched for a Multi-Point Crossover");
		}
		return offsprings;
	}
	
	/**Returns an array with the positions where the crossover is going to be applied.
	*@param size The actual size of a chromosome
	*@return An array with the positions where the crossover is going to be applied.
	*/
	protected int[] createPositions(int size){
		Integer[] ret= new Integer[size];
		for(int i=0;i<size; i++)
			ret[i]= Integer.valueOf(i);
		GAL_Util.shuffle(ret);
		ret= Arrays.copyOfRange(ret,0,2);
		Arrays.sort(ret);
		int[] ret2= new int[ret.length];
		for(int i=0;i<ret.length;i++)
			ret2[i]= ret[i];
		return ret2;
	}
}