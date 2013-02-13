package JGAL;

/**The GAL_ClassicHandler extends from GAL_Handler and it follows the classic Genetic Algorithm structure.*/
public class GAL_ClassicHandler extends GAL_Handler{
	
	/**Initializes a new GAL_ClassicHandler with a configuration and a maximum number of generations.
	*@param configuration The configuration to be used by the Genetic Algorithm.
	*@param maxGenerations The maximum number of Generations allowed.
	*@param populationSize The size of the population.
	*@param windowSize The size for the window of populations to be remembered.
	*/
	public GAL_ClassicHandler(GAL_Configuration configuration, int maxGenerations, int populationSize, int windowSize){
		super(configuration, maxGenerations, populationSize, windowSize);
	}
	
	/**Runs the Genetic Algorithm
	*@throws NotValidChromosomeException If the chromosomes can't be created with the actual configuration.
	*@throws NotValidPopulationException If the population can't be created with the actual configuration.
	*@throws NotValidOperationException If an operation can't be done with the actual configuration.
	*/
	public void runGAL()throws NotValidChromosomeException, NotValidPopulationException, NotValidOperationException{
		GAL_Population population= configuration.createNewPopulation(populationSize);
		lastGeneration= 0;
		
		//Guarda la informacion de la 1ra generacion
		configuration.computeAllFitness(population);
		saveData(population);
		
		while(!configuration.verifyTerminationCondition(window.toArray(new GAL_Population[0]))){
			
			//Incremento en 1 la generacion y verifico si ya me pase del maximo de generaciones
			if(++lastGeneration==maxGenerations)
				break;
				
			//Selecciono la nueva poblacion i+1
			population= configuration.selectNewPopulation(population);
			
			//Aplico operadores geneticos
			population= configuration.operatePopulation(population);
			
			//Calcula la aptitud de los cromosomas para la generacion lastGeneration
			configuration.computeAllFitness(population);
			
			//Guarda la informacion de la nueva generacion
			saveData(population);
		}
		
	}
}