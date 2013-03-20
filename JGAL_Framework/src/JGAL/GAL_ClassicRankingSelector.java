package JGAL;

/**The GAL_ClassicRankingSelector extends from GAL_RankingSelector and is one of the Selection Operators implemented by default.*/
public class GAL_ClassicRankingSelector extends GAL_RankingSelector{

	/**Upper limit for the expected number of offspring.*/
	protected int max;
	
	/**Initialize a new GAL_ClassicRankingSelector.
	*@param max upper limit for the expected number of offspring.
	*@throws NotValidOperationException If the upper limit is not a possitive number.
	*/
	public GAL_ClassicRankingSelector(int max)throws NotValidOperationException{
		this.max= max;
		if(max<1)
			throw new NotValidOperationException("The upper limit must be a possitive number for a Classic Ranking Selector");
	}
	
	/**Selects the new population to be used in the next generation by using the tournament selection.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperationException{
		GAL_Chromosome[] ranked= origin.clone().getChromosomes();
		sortByRank(ranked);
		int size= origin.size();
		double differential= (double) max / (double)size;
		for(int i=0; i<size; i++)
			ranked[i].setFitness((i+1)*differential);
			
		GAL_Population rankedPopulation;
		try{
			rankedPopulation= new GAL_Population(ranked,config);
		}catch(NotValidChromosomeException e){
			throw new NotValidOperationException("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperationException("Not Valid Population Exception Catched");
		}
		return rs.selectNewPopulation(rankedPopulation,config); 
	}
	
	/**Gets the max expected number of offsprings.
	*@return The max expected number of offsprings.
	*/
	public int getMax(){
		return max;
	}
}