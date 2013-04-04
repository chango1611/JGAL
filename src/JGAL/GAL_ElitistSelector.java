package JGAL;

import java.util.Arrays;
import java.util.Collections;

/**The GAL_ElitistSelector extends from GAL_NaturalSelector and is one of the Selection Operators implemented by default.
*<p>
*Elitism consists in selecting the best chromosomes of the current generation before using the selector.
*/
public class GAL_ElitistSelector extends GAL_NaturalSelector{

	/**Size of the elitist selection.*/
	protected int elitistSize;
	/**The natural selector to use with the elitist selector.*/
	protected GAL_NaturalSelector selector;
	
	/**Initialize a new GAL_ElitistSelector.
	*@param elitistSize Size of the elitist selection.
	*@param selector The natural selector to use with the elitist selector.
	*@throws NotValidOperationException If the upper limit is not a possitive number.
	*/
	public GAL_ElitistSelector(int elitistSize, GAL_NaturalSelector selector)throws NotValidOperationException{
		this.elitistSize= elitistSize;
		this.selector= selector;
		if(elitistSize<0)
			throw new NotValidOperationException("The elitist size must be a possitive number for an Elitist Selector");
	}
	
	/**Selects the new population to be used in the next generation by using the elitist selection.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperationException If an operation can't be done with the given parameters.
	*/
	public GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperationException{
		//Agarramos el arreglo de origen y lo arreglamos para obtener los elementos elite
		GAL_Chromosome[] elite= origin.getChromosomes();
		Arrays.sort(elite, Collections.reverseOrder());
		
		//Seleccionamos
		GAL_Chromosome[] selection= selector.selectNewPopulation(origin,config).getChromosomes();
		
		//Cambiamos los primeros elementos seleccionados por la elite
		for(int i=0;i<elitistSize;i++)
			selection[i]= elite[i];
		
		try{
			return new GAL_Population(selection,config);
		}catch(NotValidChromosomeException e){
			throw new NotValidOperationException("Not Valid Chromosome Exception Catched");
		}catch(NotValidPopulationException e){
			throw new NotValidOperationException("Not Valid Population Exception Catched");
		}
	}
	
	/**Gets the size of the elitist selection.
	*@return The size of the elitist selection.
	*/
	public int getElitistSize(){
		return elitistSize;
	}
}