package JGAL;

import java.util.Random;

/**The GAL_GeneConfig is an abstract class for the configuration of a GAL_Gene wich trait is of type T.
*<p>
*Subclasses of GAL_GeneConfig must provide methods for setRandomValueTo and setValueTo.
*/
public abstract class GAL_GeneConfig<T>{
	
	/**Random object wich can be used inside the others methods as setRandomValueTo.*/
	protected Random rand;
	
	/**Initializes a GAL_GeneConfig with a new instance of a Random object.*/
	public GAL_GeneConfig(){
		rand= new Random();
	}
	
	/**Creates and returns a new GAL_Gene by using the method setRandomValueTo.
	*@return The new GAL_Gene.
	*/
	public GAL_Gene createNewGene(){
		GAL_Gene ret = new GAL_Gene();
		setRandomValueTo(ret);
		return ret;
	}
	
	/**Assigns the trait value to the first GAL_Gene object from the second GAL_Gene objet.
	*@param gene The GAL_Gene wich trait is going to be modified.
	*@param gene2 The GAL_Gene wich trait is going to be assigned.
	*@throws NotValidException If the first gene is not compatible with the second gene.
	*@throws ClassCastException If the second gene is not castable.
	*/
	@SuppressWarnings("unchecked")
	public void setValueToFrom(GAL_Gene gene, GAL_Gene gene2) throws NotValidGeneException,ClassCastException{
		setValueTo(gene,(T)gene2.getTrait());
	}
	
	/**Assigns a random value to the trait of a GAL_Gene object.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public abstract void setRandomValueTo(GAL_Gene gene);
	
	/**Change the trait value with another one permited in the alleles.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public abstract void changeValueTo(GAL_Gene gene);
	
	/**Assigns a value to the trait of a GAL_Gene object.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*@param val The value to be assigned.
	*@throws NotValidGeneException If the value is not accepted for the GAL_Gene.
	*/
	public abstract void setValueTo(GAL_Gene gene, T val) throws NotValidGeneException;
}