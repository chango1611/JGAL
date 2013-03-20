package JGAL;

/**The GAL_IntegerGeneConfig is an extension from the GAL_NumericalGeneConfig that allows the creation of Integer genes.*/
public class GAL_IntegerGeneConfig extends GAL_NumericalGeneConfig<Integer>{
	
	/**Initializes a GAL_IntegerGeneConfig with min field equal 0 and max field equal Integer.MAX_VALUE*/
	public GAL_IntegerGeneConfig()throws NotValidGeneException{
		super(0,Integer.MAX_VALUE);
	}
	
	/**Initializes a GAL_IntegerGeneConfig with min field equal 0 and max field equal Integer.MAX_VALUE
	* @param name The name of the gene.
	*/
	public GAL_IntegerGeneConfig(String name)throws NotValidGeneException{
		super(name,0,Integer.MAX_VALUE);
	}
	
	/**Constructs a new GAL_IntegerGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param min The minimum value the trait can get. If it's null it will set min as 0.
	* @param max The maximum value the trait can get. If it's null it will set max as Integer.MAX_VALUE.
	* @throws NotValidGeneException The range only accepts one value; or if min > max.
	*/
	public GAL_IntegerGeneConfig(Integer min, Integer max) throws NotValidGeneException{
		super(min==null?0:min, max==null?Integer.MAX_VALUE:max);
		if(max-min==1)
			throw new NotValidGeneException("The range [min,max) only accepts one possible value for an Integer Gene");
	}
	
	/**Constructs a new GAL_IntegerGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param name The name of the gene.
	* @param min The minimum value the trait can get. If it's null it will set min as 0.
	* @param max The maximum value the trait can get. If it's null it will set max as Integer.MAX_VALUE.
	* @throws NotValidGeneException The range only accepts one value; or if min > max.
	*/
	public GAL_IntegerGeneConfig(String name, Integer min, Integer max) throws NotValidGeneException{
		super(name,min==null?0:min, max==null?Integer.MAX_VALUE:max);
		if(max-min==1)
			throw new NotValidGeneException("The range [min,max) only accepts one possible value for an Integer Gene");
	}
	
	/**Assigns a random value to the trait of a binary GAL_Gene object with an equal probability to get any number in the range [min,max) as the trait value.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void setRandomValueTo(GAL_Gene gene){
		int trait;
		trait= rand.nextInt(max-min)+min;
		gene.setTrait(trait);	
	}
	
	/**Change the trait of an integer GAL_Gene object, to another one in the range [min,max).
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void changeValueTo(GAL_Gene gene){
		int trait;
		trait= rand.nextInt(max-1-min)+min;
		if(trait>=(int)gene.getTrait())
			trait++;
		gene.setTrait(trait);
	}

}