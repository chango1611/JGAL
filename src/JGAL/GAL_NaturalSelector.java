package JGAL;

import java.util.Random;

/**The GAL_NaturalSelector is an abstract class for the selection operator.
*<p>
*Subclasses of GAL_NaturalSelector must provide methods for selectNewPopulation.
*/
public abstract class GAL_NaturalSelector{

	/**Random object wich can be used inside the others methods.*/
	protected Random rand;
	
	/**Initialize a new GAL_NaturalSelector with a random object.*/
	public GAL_NaturalSelector(){
		rand= new Random();
	}

	/**Selects the new population to be used in the next generation.
	*@param origin The population thats going to be used for the selection.
	*@return A new population created from the origin population.
	*@throws NotValidOperation If an operation can't be done with the given parameters.
	*/
	public abstract GAL_Population selectNewPopulation(GAL_Population origin, GAL_ChromosomeConfig config)throws NotValidOperation;
}