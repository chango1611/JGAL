package JGAL;

/**The GAL_DefaultInitializer is the classic initializer for the population.
*/
public class GAL_DefaultInitializer extends GAL_Initializer{
	
	/**Initializes a new GAL_Initializer*/
	public GAL_DefaultInitializer(){}
	
	/**Initialize a new population.
	*@param chromosomeConfig The chromosome configuration.
	*@param size The population size.
	*@return A new population that follows the chromosome configuration.
	*/
	public GAL_Population initialize(GAL_ChromosomeConfig chromosomeConfig, int size)throws NotValidChromosomeException,NotValidPopulationException{
		return new GAL_Population(size,chromosomeConfig);
	}
}