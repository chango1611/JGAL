package JGAL;

/**The GAL_CharacterGeneConfig is an class for the configuration of a GAL_Gene wich trait is a char*/
public class GAL_CharacterGeneConfig extends GAL_GeneConfig<Character>{

	/**Minimum value that the trait can get*/
	protected short min;
	/**Maximum value that the trait can get*/
	protected short max;
	
	/**Initializes a GAL_CharacterGeneConfig with min field equal 0 and max field equal Integer.MAX_VALUE*/
	public GAL_CharacterGeneConfig()throws NotValidGeneException{
		min= (short)Character.MIN_VALUE; max= (short)Character.MAX_VALUE;
	}
	
	/**Constructs a new GAL_CharacterGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param min The minimum value the trait can get. If it's null it will set min as 0.
	* @param max The maximum value the trait can get. If it's null it will set max as Character.MAX_VALUE.
	* @throws NotValidGeneException The range only accepts one value; or if min > max.
	*/
	public GAL_CharacterGeneConfig(Character min, Character max) throws NotValidGeneException{
		this.min= (short) (min==null?Character.MIN_VALUE:min);
		this.max= (short) (max==null?Character.MAX_VALUE:max);
		if(max-min==(short)1)
			throw new NotValidGeneException("The range [min,max) only accepts one possible value for an Character Gene");
	}
	
	/**Assigns a random value to the trait of a binary GAL_Gene object with an equal probability to get any number in the range [min,max) as the trait value.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void setRandomValueTo(GAL_Gene gene){
		char trait;
		trait= (char)(rand.nextInt(max-min) + min);
		gene.setTrait(trait);
	}
	
	/**Change the trait of an integer GAL_Gene object, to another one in the range [min,max).
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void changeValueTo(GAL_Gene gene){
		char trait;
		trait= (char)(rand.nextInt(max-1-min) + min);
		if(trait>=(char)gene.getTrait())
			trait++;
		gene.setTrait(trait);
	}
	
	/**Assigns a value to the trait of a GAL_Gene object.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*@param val The value to be assigned.
	*@throws NotValidGeneException If the value is not in the range betwen min and max.
	*/
	public void setValueTo(GAL_Gene gene, Character val)throws NotValidGeneException{
		gene.setTrait(val);
		if(val<min || val>max)
			throw new NotValidGeneException("The trait " + val + " is not in the range ["+min+","+max+"] for this Numerical Gene");
	}
	
	/**Gets the min field value.
	*@return The min field.
	*/
	public char getMin(){
		return (char) min;
	}
	
	/**Gets the max field value.
	*@return The max field.
	*/
	public char getMax(){
		return (char) max;
	}
}