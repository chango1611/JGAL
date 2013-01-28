package JGAL;

/**The GAL_NonLinealRankingSelector extends from GAL_RankingSelector and is one of the Selection Operators implemented by default.*/
public class GAL_NonLinealRankingSelector extends GAL_RankingSelector{

	/**Auxiliar double used for the selective pressure.*/
	private double q;
	
	/**Initialize a new GAL_NonLinealRankingSelector.
	*@param q Auxiliar double used for the selective pressure.
	*@throws NotValidOperation If q is not in the range [0,1].
	*/
	public GAL_NonLinealRankingSelector(double q)throws NotValidOperation{
		this.q= q;
		if(q<0 || q>1)
			throw new NotValidOperation("q is not in the range [1/population_size,2/population_size]");
	}
	
	/**Selects the new population to be used in the next generation by using the Non Lineal Ranking selection.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperation{
		int size= origin.size();
		GAL_Chromosome[] ranked= origin.clone().getChromosomes();
		sortByRank(ranked);
				
		for(int i=0; i<size; i++)
			ranked[i].setFitness(q*Math.pow(1-q,size-i-1));
			
		GAL_Population rankedPopulation;
		try{
			rankedPopulation= new GAL_Population(ranked,config);
		}catch(NotValidChromosomeException e){
			throw new NotValidOperation("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperation("Not Valid Population Exception Catched");
		}
		return rs.selectNewPopulation(rankedPopulation,config);
	}
}