package JGAL;

/**The GAL_TournamentSelector extends from GAL_NaturalSelector and is one of the Selection Operators implemented by default.*/
public class GAL_TournamentSelector extends GAL_NaturalSelector{

	/**The size for each tournament.*/
	protected int tournamentSize;
	
	/**Initialize a new GAL_TournamentSelector.
	*@param tournamentSize The size for each tournament.
	*@throws NotValidOperationException If the tournament size is not a possitive number.
	*/
	public GAL_TournamentSelector(int tournamentSize)throws NotValidOperationException{
		this.tournamentSize = tournamentSize;
		if(tournamentSize<1)
			throw new NotValidOperationException("The tournament size must be a possitive number for a Tournament Selector");
	}

	/**Selects the new population to be used in the next generation by using the tournament selection.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperationException{
		int size= origin.size(), i, j;
		GAL_Chromosome[] 	competitors= new GAL_Chromosome[tournamentSize],
							offsprings= new GAL_Chromosome[size];
		
		//Se realizan los torneos
		for(i=0;i<size;i++){
			for(j=0;j<tournamentSize;j++){
				competitors[j]= origin.getChromosome(rand.nextInt(size));
			}
			offsprings[i]= tournament(competitors);
		}
		
		try{
			return new GAL_Population(offsprings,config);
		}catch(NotValidChromosomeException e){
			throw new NotValidOperationException("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperationException("Not Valid Population Exception Catched");
		}
	}
	
	public GAL_Chromosome tournament(GAL_Chromosome[] competitors){
		GAL_Chromosome winner= competitors[0];
		for(int i=1;i<competitors.length;i++){
			if(winner.compareTo(competitors[i]) < 0)
				winner= competitors[i];
		}
		return winner;
	}

	/**Gets the tournament size.
	*@return The tournament size.
	*/
	public int getTournamentSize(){
		return tournamentSize;
	}
}