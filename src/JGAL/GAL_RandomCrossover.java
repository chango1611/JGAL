package JGAL;

/**The GAL_RandomCrossover extends from GAL_GeneticOperator and is one of the Genetic Operators implemented by default.*/
public class GAL_RandomCrossover extends GAL_GeneticOperator{
	
	/**Intern operator.*/
	private GAL_GeneticOperator operator;
	
	/**Constructs a new GAL_RandomCrossover with another Genetic Operator inside it.
	*@param prob This value is not used by the Random Crossover. 
	*@param operator Intern operator.
	*/
	public GAL_RandomCrossover(double prob, GAL_GeneticOperator operator){
		super(prob);
		this.operator= operator;
	}
	
	/**Applies the segment crossover for a Population given as the first parameter under the restrictions given by the chromosome configuration.
	*@param fathers Population thats going to be modified by the operator.
	*@param config The configuration for the chromosomes of the current and next generation.
	*@return A new population created after applying the Segment crossover.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public GAL_Population applyOperator(GAL_Population fathers, GAL_ChromosomeConfig config)throws NotValidOperation{
		
		Integer[] aux= new Integer[config.size()];
		for(int i=0;i<aux.length;i++)
			aux[i]= Integer.valueOf(i);
		GAL_Util.shuffle(aux);
		int[] order= new int[aux.length],reorder= new int[aux.length];
		for(int i=0;i<aux.length;i++){
			order[i]= aux[i];
			reorder[aux[i]]=i;
		}
		
		GAL_Population fathers2= fathers.clone(), fathers3= fathers.clone();
		GAL_ChromosomeConfig config2= config.clone();
		
		//Ordenamos todo segun el shuffle
		GAL_Util.orderBy(config.getConfiguration(),config2.getConfiguration(),order);
		for(int i=0;i<fathers.size();i++)
			GAL_Util.orderBy(fathers.getChromosome(i).getGenes(),fathers2.getChromosome(i).getGenes(),order);
		
		//Aplicamos operacion
		fathers2= operator.applyOperator(fathers2,config2);
		
		//Reordenamos a la forma correcta
		for(int i=0;i<fathers.size();i++)
			GAL_Util.orderBy(fathers2.getChromosome(i).getGenes(),fathers3.getChromosome(i).getGenes(),reorder);
			
		return fathers3;
	}
	
}