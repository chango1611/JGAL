package JGAL;

/**The GAL_RouletteSelector extends from GAL_NaturalSelector and is one of the Selection Operators implemented by default.*/
public class GAL_RouletteSelector extends GAL_NaturalSelector{

	/**Initialize a new GAL_RouletteSelector.*/
	public GAL_RouletteSelector(){}

	/**Selects the new population to be used in the next generation by using the roulette selection.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperation{
		int size= origin.size(), i, j;
		double[] roullete= new double[size];
		double sum= 0.0, aux;
		//Se suman todas las aptitudes de los cromosomas
		for(GAL_Chromosome chrom: origin.getChromosomes())
			sum+= chrom.getFitness();
		
		//Se crea la ruleta
		roullete[0]= origin.getChromosome(0).getFitness()/sum;
		for(i=1; i<size;i++){
			roullete[i]= origin.getChromosome(i).getFitness()/sum;
			roullete[i]+= roullete[i-1];
		}
		
		//Se crean los hijos
		GAL_Chromosome[] offsprings= new GAL_Chromosome[size];
		for(i=0;i<size;i++){
			aux= rand.nextDouble();
			j=0;
			//Como la suma de doubles no puede dar 1, entonces se llega hasta size-1, ya que si sigue, implica que pertenece al último elemento de la ruleta.
			while(j<size-1 && aux>roullete[j]) j++;
			offsprings[i]= origin.getChromosome(j);
		}
		
		try{
			return new GAL_Population(offsprings,config);
		}catch(NotValidChromosomeException e){
			throw new NotValidOperation("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperation("Not Valid Population Exception Catched");
		}
	}
	
}