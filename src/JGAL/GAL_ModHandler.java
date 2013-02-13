package JGAL;

import java.util.Arrays;

/**The GAL_ModHandler extends from GAL_Handler and it follows the GAmod structure.*/
public class GAL_ModHandler extends GAL_Handler{
	
	/**The expected size for each genetic operator to be applied*/
	private int[] operator_size;
	
	/**The number of fathers to be selected.*/
	private int r;
	
	/**Initializes a new GAL_ClassicHandler with a configuration and a maximum number of generations.
	*@param configuration The configuration to be used by the Genetic Algorithm.
	*@param maxGenerations The maximum number of Generations allowed.
	*@param populationSize The size of the population.
	*@param windowSize The size for the window of populations to be remembered.
	*@param r The number of fathers to be selected.
	*@throws NotValidOperationException If the sum of all probabilities for the genetic operators is not equal to one with a precision of 0.01
	*/
	public GAL_ModHandler(GAL_Configuration configuration, int maxGenerations, int populationSize, int windowSize, int r)throws NotValidOperationException{
		super(configuration, maxGenerations, populationSize, windowSize);
		this.r= r;
		int i, size= configuration.operatorsArraySize();
		double aux, sum= 0;
		operator_size= new int[size];
		for(i=0;i<size;i++){
			aux= configuration.changeProbTo(i,1.0);
			operator_size[i]= (int)Math.round(r*aux);
			sum+= aux;
		}
		if(Math.abs(1.0-sum)>0.01)
			throw new NotValidOperationException("The sum of all probabilities for the genetic operators must be 1");
	}
	
	/**Runs the Genetic Algorithm
	*@throws NotValidChromosomeException If the chromosomes can't be created with the actual configuration.
	*@throws NotValidPopulationException If the population can't be created with the actual configuration.
	*@throws NotValidOperationException If an operation can't be done with the actual configuration.
	*/
	public void runGAL()throws NotValidChromosomeException, NotValidPopulationException, NotValidOperationException{
		GAL_Population population= configuration.createNewPopulation(populationSize);
		GAL_Chromosome[][] aux= new GAL_Chromosome[operator_size.length+1][];
		GAL_Chromosome[] fathers;
		int i, from, to;
		
		lastGeneration= 0;
		
		//Guarda la informacion de la 1ra generacion
		configuration.computeAllFitness(population);
		saveData(population);
		
		while(!configuration.verifyTerminationCondition(window.toArray(new GAL_Population[0]))){
			
			//Incremento en 1 la generacion y verifico si ya me pase del maximo de generaciones
			if(++lastGeneration==maxGenerations)
				break;
			
			//Selecciono a los padres
			fathers= Arrays.copyOf(configuration.selectNewPopulation(population).getChromosomes(),r);
			
			//Aplico las operaciones sobre los padres
			from=0;
			for(i=0;i<operator_size.length;i++){
				to= from + operator_size[i];
				if(i==operator_size.length-1) to=r;
				aux[i]= configuration.operatePopulation(configuration.createNewPopulation(Arrays.copyOfRange(fathers,from,to)),i).getChromosomes();
				from= to;
			}
			
			//Selecciono a los sobrevivientes
			aux[i]= Arrays.copyOf(configuration.selectNewPopulation(population).getChromosomes(),populationSize - r);
			
			//Agrupo los cromosomas y creo la nueva poblacion
			population= configuration.createNewPopulation(GAL_Util.concatArrays(aux, new GAL_Chromosome[0]));
			
			//Calcula la aptitud de los cromosomas para la generacion lastGeneration
			configuration.computeAllFitness(population);
			
			//Guarda la informacion de la nueva generacion
			saveData(population);
		}
		
	}
}