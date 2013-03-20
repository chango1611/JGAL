package JGAL;

/**The GAL_NumericalGeneConfig is an abstract class for the configuration of a GAL_Gene wich trait is a number
*<p>
*Subclasses of GAL_NumericalGeneConfig must provide method for setRandomValueTo
*/
public abstract class GAL_NumericalGeneConfig<T extends Number & Comparable<T>> extends GAL_GeneConfig<T>{
	/**Minimum value that the trait can get*/
	protected T min;
	/**Maximum value that the trait can get*/
	protected T max;
	
	/**Initializes a new GAL_NumericalGeneConfig wich min and max set on null.*/
	public GAL_NumericalGeneConfig(){
		this.min= null;
		this.max= null;
	}
	
	/**Initializes a new GAL_NumericalGeneConfig wich min and max set on null.
	*@param name The name of the gene.
	*/
	public GAL_NumericalGeneConfig(String name){
		super(name);
		this.min= null;
		this.max= null;
	}
	
	/**Constructs a new GAL_NumericalGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param min The minimum value the trait can get.
	* @param max The maximum value the trait can get.
	* @throws NotValidGeneException If min > max.
	*/
	public GAL_NumericalGeneConfig(T min, T max)throws NotValidGeneException{
		this.min= min;
		this.max= max;
		if(min!=null && max!=null && max.compareTo(min)<=0)
			throw new NotValidGeneException("min value ("+min+") > max value ("+max+") for a Numerical Gene");
	}
	
	/**Constructs a new GAL_NumericalGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param name The name of the gene.
	* @param min The minimum value the trait can get.
	* @param max The maximum value the trait can get.
	* @throws NotValidGeneException If min > max.
	*/
	public GAL_NumericalGeneConfig(String name, T min, T max)throws NotValidGeneException{
		super(name);
		this.min= min;
		this.max= max;
		if(min!=null && max!=null && max.compareTo(min)<=0)
			throw new NotValidGeneException("min value ("+min+") > max value ("+max+") for a Numerical Gene");
	}
	
	/**Assigns a value to the trait of a GAL_Gene object.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*@param val The value to be assigned.
	*@throws NotValidGeneException If the value is not in the range betwen min and max.
	*/
	public void setValueTo(GAL_Gene gene, T val)throws NotValidGeneException{
		gene.setTrait(val);
		if(val.compareTo(min)<0 || val.compareTo(max)>0)
			throw new NotValidGeneException("The trait " + val + " is not in the range ["+min+","+max+"] for this Numerical Gene");
	}
	
	/**Gets the min field value.
	*@return The min field.
	*/
	public T getMin(){
		return min;
	}
	
	/**Gets the max field value.
	*@return The max field.
	*/
	public T getMax(){
		return max;
	}
}