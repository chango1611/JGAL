package JGAL;

import java.util.Random;
import java.util.LinkedList;

/**The GAL_Crossover is an abstract class wich extends from the GAL_GeneticOperator and haves auxiliar methods normaly used by the crossover operators.
*<p>
*Subclasses of GAL_Crossover must provide methods for applyOperator.
*/
public abstract class GAL_Crossover extends GAL_GeneticOperator{
	
	/**Constructs a new GAL_Crossover with a probability of ocurrence given by its only parameter.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the genetic operator
	*/
	public GAL_Crossover(double prob){
		super(prob);
	}
	
	/**Distributes in two groupes the chromosomes given in the first argument by the probability given by the second argument.
	*<p>
	*If the value given by rand is less or equal to prob then the chromosome at the moment gets in the first group else it gets in the second group.
	*@param chromosomes The chromosomes to be distributed.
	*@param prob The probability for the distribution.
	*@return An array of arrays of GAL_Chromosome that represents the distribution. 
	*/
	public GAL_Chromosome[][] distributeChromosomes(GAL_Chromosome[] chromosomes, double prob){
		LinkedList<GAL_Chromosome> chrom1= new LinkedList<GAL_Chromosome>(), chrom2= new LinkedList<GAL_Chromosome>();
		for(int i=0; i<chromosomes.length; i++){
			if(rand.nextDouble()<=prob)
				chrom1.add(chromosomes[i]);
			else
				chrom2.add(chromosomes[i]);
		}
		GAL_Chromosome[][] ret= {chrom1.toArray(new GAL_Chromosome[0]), chrom2.toArray(new GAL_Chromosome[0])};
		return ret;
	}
	
	/**Creates subgroups of chromosomes of size of the second parameter
	*@param chrom The chromosomes to be divided in groups
	*@param size The size of each subgroup
	*@return An array of arrays of GAL_Chromosome where each array of GAL_Chromosome is a subgroup.
	*/
	public GAL_Chromosome[][] groupChromosomes(GAL_Chromosome[] chrom,int size){
		if(chrom.length==0){
			return new GAL_Chromosome[0][0];
		}
		int tam= chrom.length/size, i, j;
		GAL_Chromosome[][] ret= new GAL_Chromosome[tam][size];
		GAL_Util.shuffle(chrom);
		//Coloca los chromosomas en parejas, trios o mas
		for(i=0;i<chrom.length;i++)
			ret[i/size][i%size]= chrom[i];
		
		return ret;
	}
	
	/**Fixes the groupability of the first array in the array of arrays.
	*<p>
	*If the first array can't be grouped into n subgroups, then the code will change the first group so it can be grouped. The elements that
	*were removed from the first group will be concatenated at the end of the second array.
	*@param chrom First array of chromosomes.
	*@param n The number at witch the first group should be subgrouped.
	*/
	public void correctSizeFromFirstGroup(GAL_Chromosome[][] chrom, int n){
		if(chrom.length<n){
			chrom[1]= GAL_Util.concatArrays(chrom[1],chrom[0],new GAL_Chromosome[0]);
			chrom[0]= new GAL_Chromosome[0];
		}
		int p= chrom[0].length%n;
		if(p!=0){
			GAL_Chromosome[][] aux= GAL_Util.extractFrom(chrom[0],p,new GAL_Chromosome[0][0]);
			chrom[0]= aux[0];
			chrom[1]= GAL_Util.concatArrays(chrom[1],aux[1],new GAL_Chromosome[0]);
		}
	}
}