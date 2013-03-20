package JGAL;

/**The GAL_LinealRankingSelector extends from GAL_RankingSelector and is one of the Selection Operators implemented by default.*/
public class GAL_LinealRankingSelector extends GAL_RankingSelector{

	/**Auxiliar double used for the selective pressure.*/
	protected double q;
	
	/**Initialize a new GAL_LinealRankingSelector.
	*<p>
	*It does not throw an Exception at the moment, but q must be in the range [1/population_size,2/population_size], 
	*or it will give an exception at selectNewPopulation.
	*@param q Auxiliar double used for the selective pressure.
	*/
	public GAL_LinealRankingSelector(double q){
		this.q= q;
	}
	
	/**Selects the new population to be used in the next generation by using the Lineal Ranking selection.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperationException If an operation can't be done with the given parameters; or if q is not in the range [1/population_size,2/population_size].
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperationException{
		int size= origin.size();
		if(q<1.0/size || q>2.0/size)
			throw new NotValidOperationException("q is not in the range [1/population_size,2/population_size]");
		GAL_Chromosome[] ranked= origin.clone().getChromosomes();
		sortByRank(ranked);
		
		double r= 2*(q - 1.0/size) / (size-1);
		
		for(int i=0; i<size; i++)
			ranked[i].setFitness(q - (size-i-1)*r);
			
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
	
	/**Gets the q value.
	*@return The q value.
	*/
	public double getQ(){
		return q;
	}
}