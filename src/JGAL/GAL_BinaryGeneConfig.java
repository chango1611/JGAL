package JGAL;

/**The GAL_BinaryGeneConfig is an extension from the GAL_GeneConfig that allows the creation of binary genes using Byte for the strait.*/
public class GAL_BinaryGeneConfig extends GAL_GeneConfig<Byte>{
	
	/**Initializes a new GAL_BinaryGeneConfig by calling super.*/
	public GAL_BinaryGeneConfig(){
	}
	
	/**Initializes a new GAL_BinaryGeneConfig with a name by calling super.
	*@param name The name of the gene.
	*/
	public GAL_BinaryGeneConfig(String name){
		super(name);
	}
	
	/**Assigns a random value to the trait of a binary GAL_Gene object with an equal probability to get 0 or 1 as the trait value.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void setRandomValueTo(GAL_Gene gene){
		if(rand.nextDouble()<0.5)
			gene.setTrait((byte) 0);
		else
			gene.setTrait((byte) 1);
	}
	
	/**Change the trait of a binary GAL_Gene object, if the trait value is 0 it will change to 1 and viceversa.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void changeValueTo(GAL_Gene gene){
		if((byte)gene.getTrait() == (byte) 0)
			gene.setTrait((byte) 1);
		else
			gene.setTrait((byte) 0);
	}
	
	/**Assigns a value to the trait of a binary GAL_Gene object.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*@param val The value to be assigned.
	*@throws NotValidGeneException If the parameter val is not 0 or 1.	
	*/
	public void setValueTo(GAL_Gene gene, Byte val)throws NotValidGeneException{
		gene.setTrait(val);
		if(val!=0 && val!=1)
			throw new NotValidGeneException("The trait " + val + " does not correspond to the possible values 0 o 1 for a Binary Gene");
	}

}