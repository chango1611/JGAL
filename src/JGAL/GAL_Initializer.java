package JGAL;

/**The GAL_Initializer is an abstract class for the population initializer.
*<p>
*The initializer is used to start the population for a GA.
*<p>
*Subclasses of GAL_Initializer must provide methods for initialize.
*/
public abstract class GAL_Initializer{
	
	/**Initializes a new GAL_Initializer*/
	public GAL_Initializer(){}
	
	/**Initialize a new population under the configuration given by chromosomeConfig.
	*@param chromosomeConfig The chromosome configuration.
	*@param size The population size.
	*@return A new population that follows the chromosome configuration.
	*/
	public abstract GAL_Population initialize(GAL_ChromosomeConfig chromosomeConfig, int size)throws NotValidChromosomeException,NotValidPopulationException;
}