package JGAL;

/**The GAL_Configuration contains all the configuration data needed for running a GA and the methods that requiere of the configuration to work.*/
public class GAL_Configuration{

	/**The configuration for the chromosomes that will be used by this GA.*/
	protected GAL_ChromosomeConfig chromosomeConfig;
	/**The initializer that will be useb by this GA*/
	protected GAL_Initializer initializer;
	/**The termination condition that will be used by this GA.*/
	protected GAL_TerminationCondition condition;
	/**The fitness function that will be used by this GA.*/
	protected GAL_FitnessFunction fitnessFunction;
	/**The genetic operators that will be used by this GA.*/
	protected GAL_GeneticOperator[] operators;
	/**The natural selector that will be useb by this GA*/
	protected GAL_NaturalSelector selector;

	/**Constructs a new GAL_Configuration with the necessary information for running a GA.
	*@param chromosomeConfig The configuration for the chromosomes that will be used by this GA.
	*@param fitnessFunction The fitness function that will be used by this GA.
	*@param selector The natural selector that will be useb by this GA.
	*@param operators The genetic operators that will be used by this GA.
	*/
	public GAL_Configuration(GAL_ChromosomeConfig chromosomeConfig, GAL_FitnessFunction fitnessFunction, GAL_NaturalSelector selector, GAL_GeneticOperator[] operators){
		this.chromosomeConfig= chromosomeConfig;
		initializer= new GAL_DefaultInitializer();
		condition= new GAL_DefaultTerminationCondition();
		this.fitnessFunction= fitnessFunction;
		this.selector= selector;
		this.operators= operators;
	}
	
	/**Constructs a new GAL_Configuration with the necessary information for running a GA.
	*@param chromosomeConfig The configuration for the chromosomes that will be used by this GA.
	*@param condition The termination condition that will be used by this GA.
	*@param fitnessFunction The fitness function that will be used by this GA.
	*@param selector The natural selector that will be useb by this GA.
	*@param operators The genetic operators that will be used by this GA.
	*/
	public GAL_Configuration(GAL_ChromosomeConfig chromosomeConfig, GAL_TerminationCondition condition, GAL_FitnessFunction fitnessFunction, GAL_NaturalSelector selector, GAL_GeneticOperator[] operators){
		this.chromosomeConfig= chromosomeConfig;
		initializer= new GAL_DefaultInitializer();
		this.condition= condition;
		this.fitnessFunction= fitnessFunction;
		this.selector= selector;
		this.operators= operators;
	}
	
	/**Constructs a new GAL_Configuration with the necessary information for running a GA.
	*@param chromosomeConfig The configuration for the chromosomes that will be used by this GA.
	*@param initializer The initializer that will be used by this GA.
	*@param fitnessFunction The fitness function that will be used by this GA.
	*@param selector The natural selector that will be useb by this GA.
	*@param operators The genetic operators that will be used by this GA.
	*/
	public GAL_Configuration(GAL_ChromosomeConfig chromosomeConfig, GAL_Initializer initializer, GAL_FitnessFunction fitnessFunction, GAL_NaturalSelector selector, GAL_GeneticOperator[] operators){
		this.chromosomeConfig= chromosomeConfig;
		this.initializer= initializer;
		condition= new GAL_DefaultTerminationCondition();
		this.fitnessFunction= fitnessFunction;
		this.selector= selector;
		this.operators= operators;
	}
	
	/**Constructs a new GAL_Configuration with the necessary information for running a GA.
	*@param chromosomeConfig The configuration for the chromosomes that will be used by this GA.
	*@param initializer The initializer that will be used by this GA.
	*@param condition The termination condition that will be used by this GA.
	*@param fitnessFunction The fitness function that will be used by this GA.
	*@param selector The natural selector that will be useb by this GA.
	*@param operators The genetic operators that will be used by this GA.
	*/
	public GAL_Configuration(GAL_ChromosomeConfig chromosomeConfig, GAL_Initializer initializer, GAL_TerminationCondition condition, GAL_FitnessFunction fitnessFunction, GAL_NaturalSelector selector, GAL_GeneticOperator[] operators){
		this.chromosomeConfig= chromosomeConfig;
		this.initializer= initializer;
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
		return initializer.initialize(chromosomeConfig,size);
	}
	
	/**Creates a new population under the configuration given by chromosomeConfig and an array of chromosomes.
	*@param chromosomes The chromosomes that will be used for the new population.
	*@return The new GAL_Population.
	*@throws NotValidChromosomeException If the array of chromosomes does not match with the configuration.
	*@throws NotValidPopulationException If the length of the array of chromosomes is less than 1. 
	*/
	public GAL_Population createNewPopulation(GAL_Chromosome[] chromosomes)throws NotValidChromosomeException,NotValidPopulationException{
		return new GAL_Population(chromosomes,chromosomeConfig);
	}
	
	/**Select a new population generated with the natural selector.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin)throws NotValidOperationException{
		return selector.selectNewPopulation(origin,chromosomeConfig);
	}
	
	/**Applies all the genetic operators in the order they are in the operators array.
	*@param origin The GAL_Population thats going to be modified by all operators.
	*@return A new GAL_Population wich has been modified by all the genetic operators.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population operatePopulation(GAL_Population origin)throws NotValidOperationException{
		GAL_Population ret= origin.clone();
		for(GAL_GeneticOperator op: operators)
			ret= op.applyOperator(ret,chromosomeConfig);
		return ret;
	}
	
	/**Applies the genetic operator in the position given by the int parameter.
	*@param origin The GAL_Population thats going to be modified by the operator.
	*@param pos The position of the genetic operator.
	*@return A new GAL_Population wich has been modified by the genetic operator.
	*/
	public GAL_Population operatePopulation(GAL_Population origin, int pos)throws NotValidOperationException{
		GAL_Population ret= origin.clone();
		return operators[pos].applyOperator(ret,chromosomeConfig);
	}
	
	/**Changes the probability of the genetic operator at the position indicated by the first parameter.
	*@param pos The position of the genetic operator.
	*@param prob The new probability for the genetic operator.
	*@return The old probability for the genetic operator.
	*/
	public double changeProbTo(int pos, double prob){
		double ret= operators[pos].getProb();
		operators[pos].setProb(prob);
		return ret;
	}
	
	/**Returns the size of the genetic operators array.
	*@return The size of the genetic operators array.
	*/
	public int operatorsArraySize(){
		return operators.length;
	}
}