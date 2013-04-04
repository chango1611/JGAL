package JGAL;

import java.util.Arrays;
import java.util.Collections;

/**The GAL_RankingSelector in an abstract class wich extends from GAL_NaturalSelector.
*<p>
*Is a subset of the selectors. Consist of creating artificial fitness
*taking into account the ranking of the chromosomes.
*<p>
*The ranking is generated as follows:
*<ul type="disc">
*	<li><p><tt>The chromosome with the best fitness obtains the rank 1</tt></p></li>
*	<li><p><tt>The chromosome with the second best fitness obtains the rank 2</tt></p></li>
*	<li><p><tt>...</tt></p></li>
*	<li><p><tt>The chromosome with the worst fitness obtains the rank pop_size</tt></p></li>
*</ul>
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