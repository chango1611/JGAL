package JGAL;

/**The GAL_DoubleGeneConfig is an extension from the GAL_NumericalGeneConfig that allows the creation of Double genes.*/
public class GAL_DoubleGeneConfig extends GAL_NumericalGeneConfig<Double>{
	
	/**Initializes a GAL_IntegerGeneConfig with min field equal 0 and max field equal Double.MAX_VALUE*/
	public GAL_DoubleGeneConfig()throws NotValidGeneException{
		super(0d,Double.MAX_VALUE);
	}
	
	/**Initializes a GAL_IntegerGeneConfig with min field equal 0 and max field equal Double.MAX_VALUE
	* @param name The name of the gene.
	*/
	public GAL_DoubleGeneConfig(String name)throws NotValidGeneException{
		super(name,0d,Double.MAX_VALUE);
	}
	
	/**Constructs a new GAL_DoubleGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param min The minimum value the trait can get. If it's null it will set min as 0.
	* @param max The maximum value the trait can get. If it's null it will set max as Double.MAX_VALUE.
	* @throws NotValidGeneException If min > max.
	*/
	public GAL_DoubleGeneConfig(Double min, Double max) throws NotValidGeneException{
		super(min==null?0:min, max==null?Double.MAX_VALUE:max);
	}
	
	/**Constructs a new GAL_DoubleGeneConfig object with minimum and maximum possible value for the trait of a GAL_Gene.
	* @param name The name of the gene.
	* @param min The minimum value the trait can get. If it's null it will set min as 0.
	* @param max The maximum value the trait can get. If it's null it will set max as Double.MAX_VALUE.
	* @throws NotValidGeneException If min > max.
	*/
	public GAL_DoubleGeneConfig(String name, Double min, Double max) throws NotValidGeneException{
		super(name,min==null?0:min, max==null?Double.MAX_VALUE:max);
	}
	
	/**Assigns a random value to the trait of a binary GAL_Gene object with an equal probability to get any number in the range [min,max] as the trait value.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void setRandomValueTo(GAL_Gene gene){
		double trait;
		trait= rand.nextDouble()*(max-min)+min;
		gene.setTrait(trait);	
	}
	
	/**Change the trait of an double GAL_Gene object, to another one in the range [min,max).
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void changeValueTo(GAL_Gene gene){
		setRandomValueTo(gene); //Probabilidad de que caiga el mismo valor extremadamente baja.
	}

}