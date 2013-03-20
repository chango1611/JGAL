package JGAL;

import java.util.Arrays;
import java.util.Collections;

/**The GAL_RankingSelector in an abstract class wich extends from GAL_NaturalSelector.
*<p>
*Subclasses of GAL_RankingSelector must provide methods for selectNewPopulation.
*/
public abstract class GAL_RankingSelector extends GAL_NaturalSelector{

	/**A GAL_RouletteSelector used after changing the fitness by the ranking*/
	protected GAL_RouletteSelector rs;
	
	/**Initialize a new GAL_RankingSelector with a GAL_RouletteSelector object.*/
	public GAL_RankingSelector(){
		rs= new GAL_RouletteSelector();
	}
	
	/**Sorts the array of chromosomes by the rank (fitness)
	*@param origin The array of chromosomes to be sorted.
	*/
	public void sortByRank(GAL_Chromosome[] origin){
		Arrays.sort(origin);
	}
}