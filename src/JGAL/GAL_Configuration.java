package JGAL;

/**The GAL_Configuration contains all the configuration data needed for running a GA and the methods that requiere of them.*/
public class GAL_Configuration{

	/**The configuration for the chromosomes that will be used by this program.*/
	private GAL_ChromosomeConfig chromosomeConfig;
	/**The termination condition that will be used by this program.*/
	private GAL_TerminationCondition condition;
	/**The fitness function that will be used by this program.*/
	private GAL_FitnessFunction fitnessFunction;
	/**The genetic operators that will be used by this program.*/
	private GAL_GeneticOperator[] operators;
	/**The natural selector that will be useb by this program*/
	private GAL_NaturalSelector selector;

	/**Constructs a new GAL_Configuration with the necessary information for running a GA.
	*@param chromosomeConfig The configuration for the chromosomes that will be used by this program.
	*@param condition The termination condition that will be used by this program.
	*@param fitnessFunction The fitness function that will be used by this program.
	*@param selector The natural selector that will be useb by this program.
	*@param operators The genetic operators that will be used by this program.
	*/
	public GAL_Configuration(GAL_ChromosomeConfig chromosomeConfig, GAL_TerminationCondition condition, GAL_FitnessFunction fitnessFunction, GAL_NaturalSelector selector, GAL_GeneticOperator[] operators){
		this.chromosomeConfig= chromosomeConfig;
		this.condition= condition;
		this.fitnessFunction= fitnessFunction;
		this.selector= selector;
		this.operators= operators;
	}
	
	/**Verifies if the termination condition is archieved.
	*@param window The window of GAL_Population used to verify the termination condition.
	*@return true if the termination condition is archieved, false otherwise.
	*/
	public boolean verifyTerminationCondition(GAL_Population[] window){
		return condition.verifyTerminationCondition(window);
	}
	
	/**Computes the fitness for an entire population.
	*@param population The population that will get its fitness calculated.
	*/
	public void computeAllFitness(GAL_Population population){
		int tam= population.size();
		for(int i=0;i<tam;i++)
			population.getChromosome(i).setFitness(fitnessFunction.computeFitness(population.getChromosome(i)));
	}
	
	/**Creates a new population under the configuration given by chromosomeConfig.
	*@param size The number of chromosomes that will get the population.
	*@return The new GAL_Population.
	*@throws NotValidChromosomeException If the configuration has an empty gene configuration.
	*@throws NotValidPopulationException If the size is less than 1. 
	*/
	public GAL_Population createNewPopulation(int size)throws NotValidChromosomeException,NotValidPopulationException{
		return new GAL_Population(size,chromosomeConfig);
	}
	
	/**Select a new population generated with the natural selector.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin)throws NotValidOperation{
		return selector.selectNewPopulation(origin,chromosomeConfig);
	}
	
	/**Applies all the genetic operators in the order they are in the operators array.
	*@param origin The GAL_Population thats going to be modified by all operators.
	*@return A new GAL_Population wich has been modified by all the genetic operators.
	*/
	public GAL_Population operatePopulation(GAL_Population origin)throws NotValidOperation{
		GAL_Population ret= origin.clone();
		for(GAL_GeneticOperator op: operators)
			ret= op.applyOperator(ret,chromosomeConfig);
		return ret;
	}
}