package JGAL;

import java.util.Random;
import java.util.LinkedList;

/**The GAL_GeneticOperator is an abstract class for the genetic operators.
*<p>
*Subclasses of GAL_GeneticOperator must provide methods for applyOperator.
*/
public abstract class GAL_GeneticOperator{
	
	/**Random object wich can be used inside the others methods as distributeChromosomes.*/
	protected Random rand;
	/**Probability of ocurrence for the genetic operator*/
	protected double prob;
	
	/**Constructs a new GAL_GeneticOperator and initializes the rand field.
	*<p>
	*If the probability is out of the range [0,1], the prob will take the closest value in the range. As Example. The probability 1.5 is equal to probability 1 and the probability -1 is equal to probability 0.
	*@param prob Probability of ocurrence for the genetic operator.
	*/
	public GAL_GeneticOperator(double prob){
		rand= new Random();
		this.prob= prob;
	}
	
	//**Sets a new prob field value.
	//*@param prob The probability to be set.
	//*/
	//public void setProb(double prob){
	//	this.prob= prob;
	//}
	
	/**Applies the operator for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the operator.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public abstract GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config) throws NotValidOperationException;
}