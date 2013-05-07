package JGAL;

import java.util.Arrays;

/**The GAL_ModHandler extends from GAL_Handler and it follows the GAmod structure:
*<p>
*<b>procedure</b> AGmod<br>
*<b>begin</b><br>
*t = 0;<br>
*initialize P(t);<br>
*evaluate P(t);<br>
*<b>while</b> (<b>not</b> condición de terminación) <b>do</b><br>
*t= t+1;<br>
*select fathers from P(t – 1);<br>
*select deaths from P(t – 1);<br>
*create P(t): reproduce fathers;<br>
*evaluate P(t);<br>
*<b>end;</b><br>
*<b>end;</b><br>
*<p>
*As shown, the selection is divided into two steps:
*<ul type="disc">
*	<li><p><tt>Step 1 – Select Fathers: Independently are selected r chromosomes
*	(Not necessarily distinct) to reproduce.</tt></p></li>
*	<li><p><tt>Step 2 – Select Deaths: r different chromosomes are selected to die.</tt></p></li>
*</ul>
*<p>
*After these two steps, three not necessarily disjoint groups are formed:
*<ul type="disc">
*	<li><p><tt>r chromosomes to reproduce.</tt></p></li>
*	<li><p><tt>r chromosomes to die.</tt></p></li>
*	<li><p><tt>The rest of the chromosomes, called neutral chromosomes.</tt></p></li>
*</ul>
*<p>
*The new population will be created by:
*<ul type="disc">
*	<li><p><tt>The r offsprings formed from the r parents.</tt></p></li>
*	<li><p><tt>The pop_size – r chromosomes that were not chosen to die.</tt></p></li>
*</ul>
*<p>
*<b>Note.</b> The sum of all the probabilities of ocurrence of the genetic operators must be equal to 1,
*since they are going to be applied over all the r chromosomes to reproduce.
*/
public class GAL_ModHandler extends GAL_Handler{
	
	/**The real probability for each operator*/
	protected double[] old_prob;
	
	/**The expected size for each genetic operator to be applied*/
	protected int[] operator_size;
	
	/**The number of fathers to be selected.*/
	protected int r;
	
	/**Initializes a new GAL_ModHandler with a configuration, maximum number of generations, population size, window size and r.
	*@param configuration The configuration to be used by the Genetic Algorithm.
	*@param maxGenerations The maximum number of Generations allowed.
	*@param populationSize The size of the population.
	*@param windowSize The size for the window of populations to be remembered.
	*@param r The number of fathers to be selected.
	*@throws NotValidOperationException If the sum of all probabilities for the genetic operators is not equal to 1 with a lost of precision of 0.01
	*/
	public GAL_ModHandler(GAL_Configuration configuration, int maxGenerations, int populationSize, int windowSize, int r)throws NotValidOperationException{
		super(configuration, maxGenerations, populationSize, windowSize);
		this.r= r;
		if(r>populationSize)
			throw new NotValidOperationException("r must be less or equal than populationSize");
		int i, size= configuration.operatorsArraySize();
		double sum= 0;
		old_prob= new double[size];
		operator_size= new int[size];
		for(i=0;i<size;i++){
			old_prob[i]= configuration.changeProbTo(i,1.0);
			operator_size[i]= (int)Math.round(r*old_prob[i]);
			sum+= old_prob[i];
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
		
		//Verifico si ya me pase del maximo de generaciones
		while(++lastGeneration<maxGenerations){
			
			//Verifico si se cumple la condicion de terminación
			if(configuration.verifyTerminationCondition(window.toArray(new GAL_Population[0])))
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
		
		//restauramos las probabilidades originales
		for(i=0;i<old_prob.length;i++)
			configuration.changeProbTo(i,old_prob[i]);
	}
}