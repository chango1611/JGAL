package JGAL;

/**The GAL_FitnessFunction is an abstract class for the fitness function.
*<p>
*Is used to calculate the fitness of the chromosomes of each generation
*<p>
*Subclasses of GAL_FitnessFunction must provide methods for computeFitness.
*/
public abstract class GAL_FitnessFunction{
	
	/**Initializes a new GAL_FitnessFunction*/
	public GAL_FitnessFunction(){}
	
	/**Calculates and saves the fitness for a GAL_Chromosome passed by argument.
	*@param chromosome The GAL_Chromosome wich fitness is going to be calculated.
	*@return The fitness for the chromosome passed by argument
	*/
	public abstract double computeFitness(GAL_Chromosome chromosome);
}