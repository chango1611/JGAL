package JGAL;

import java.util.LinkedList;

/**The GAL_Handler is an abstract class for the handler of the Genetic Algorithm.
*<p>
*Subclasses of GAL_Handler must provide methods for runGAL, wich execute the Genetic Algorithm with the given configuration.
*/
public abstract class GAL_Handler{

	//Datos de salida del programa
	/**Stores the best chromosome for each generation.*/
	protected GAL_Chromosome[] bestChromosomeFromGeneration;
	
	/**Stores the average fitness for each generation.*/
	protected double[] averageFitnessFromGeneration;
	
	/**The last generation that has been executed*/
	protected int lastGeneration;
	
	//Datos de configuracion
	/**The configuration to be used by the Genetic Algorithm.*/
	protected GAL_Configuration configuration;
	
	/**The maximum number of Generations allowed.*/
	protected int maxGenerations;
	
	/**The size of the population.*/
	protected int populationSize;
		
	/**The size for the window of populations to be remembered.*/
	protected int windowSize;
	
	/**Stores the window of populations to be remembered.*/
	protected LinkedList<GAL_Population> window;
	
	/**Initializes a new GAL_Handler with a configuration and a maximum number of generations.
	*@param configuration The configuration to be used by the Genetic Algorithm.
	*@param maxGenerations The maximum number of Generations allowed.
	*@param populationSize The size of the population.
	*@param windowSize The size for the window of populations to be remembered.
	*/
	public GAL_Handler(GAL_Configuration configuration, int maxGenerations, int populationSize, int windowSize){
		this.configuration= configuration;
		this.maxGenerations= maxGenerations;
		this.populationSize= populationSize;
		this.windowSize= windowSize;
		this.lastGeneration= 0;
		bestChromosomeFromGeneration= new GAL_Chromosome[maxGenerations];
		averageFitnessFromGeneration= new double[maxGenerations];
		window= new LinkedList<GAL_Population>();
	}
	
	/**Saves the information pertinent to the problem
	*@param population The population wich information is getting saved
	*/
	protected void saveData(GAL_Population population){	
		//Guardo informacion de la generacion lastGeneration
		bestChromosomeFromGeneration[lastGeneration]= population.getBestChromosome();
		averageFitnessFromGeneration[lastGeneration]= population.getTotalFitness()/population.size();
		
		//Guardo en la ventada
		window.addFirst(population.clone());
		//Si la lista se pasa del tamano de la ventana
		if(window.size()>windowSize)
			window.removeLast();
	}
	
	/**Gets the best chromosome from all generations.
	*@return The best chromosome from all generations.
	*/
	public GAL_Chromosome getBestFromAll(){
		GAL_Chromosome winner= bestChromosomeFromGeneration[0];
		for(int i=1;i<maxGenerations && i<=lastGeneration;i++){
			if(winner.compareTo(bestChromosomeFromGeneration[i]) < 0)
				winner= bestChromosomeFromGeneration[i];
		}
		return winner;
	}
	
	/**Gets an array of GAL_Chromosomes where each one represents the best chromosome for each generation.
	*@return Array of GAL_Chromosomes where each one represents the best chromosome for each generation.
	*/
	public GAL_Chromosome[] getBestFromEach(){
		return bestChromosomeFromGeneration;
	}
	
	/**Gets the best chromosome for the generation denoted by the int parameter.
	*@param pos The position of the generation wich best chromosome is going to be returned.
	*@return The best chromosome for the generation denoted by the int parameter.
	*/
	public GAL_Chromosome getBestFrom(int pos){
		return bestChromosomeFromGeneration[pos];
	}
	
	/**Gets the average fitness from all generations.
	*@return The average fitness from all generations.
	*/
	public double getAverageFitnessFromAll(){
		double sum= 0.0;
		for(double fitness: averageFitnessFromGeneration)
			sum+= fitness;
		return sum/maxGenerations;
	}
	
	/**Gets an array of doubles where each one represents the average fitness for each generation.
	*@return Array of doubles where each one represents the average fitness for each generation.
	*/
	public double[] getAverageFitnessFromEach(){
		return averageFitnessFromGeneration;
	}
	
	/**Gets the average fitness for the generation denoted by the int parameter.
	*@param pos The position of the generation wich average fitness is going to be returned.
	*@return The average fitness for the generation denoted by the int parameter.
	*/
	public double getAverageFitnessFrom(int pos){
		return averageFitnessFromGeneration[pos];
	}
	
	/**Gets the window of population.
	*@return The window of population.
	*/
	public GAL_Population[] getWindow(){
		return window.toArray(new GAL_Population[0]);
	}
	
	/**Gets an population from the window of population denoted by the int parameter.
	*@param pos The position of the window wich population is going to be returned.
	*@return The window of population.
	*/
	public GAL_Population getPopulationFromWindow(int pos){
		return window.get(pos);
	}
	
	/**Gets the number for the last generation to be executed.
	*@return The number for the last generation to be executed.
	*/
	public int getLastGenerationNumber(){
		return lastGeneration;
	}
	
	/**Runs the Genetic Algorithm
	*@throws NotValidChromosomeException If the chromosomes can't be created with the actual configuration.
	*@throws NotValidPopulationException If the population can't be created with the actual configuration.
	*@throws NotValidOperationException If an operation can't be done with the actual configuration.
	*/
	public abstract void runGAL()throws NotValidChromosomeException, NotValidPopulationException, NotValidOperationException;
}