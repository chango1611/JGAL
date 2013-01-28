package JGAL;

/**The GAL_NominalGeneConfig is an extension from the GAL_GeneConfig that allows the creation of nominal genes using String for the strait.*/
public class GAL_NominalGeneConfig extends GAL_GeneConfig<String>{
	
	/**Array of possible values the strait can get*/
	private String[] alleles;
		
	/**Constructs a new GAL_NominalGeneConfig object with an array of alleles for the trait of a GAL_Gene.
	* @param alleles Array of possible values the strait can get
	* @throws NotValidGeneException If the alleles array is less than 1 or null.
	*/
	public GAL_NominalGeneConfig(String[] alleles) throws NotValidGeneException{
		this.alleles= alleles;
		if(alleles==null || alleles.length<=1)
			throw new NotValidGeneException("Amount of possible alleles must be greater than 1 for a Nominal Gene");
	}
	
	/**Assigns a value to the trait of a GAL_Gene object.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*@param val The value to be assigned.
	*@throws NotValidGeneException If the value is not in the possible alleles.
	*/
	public void setValueTo(GAL_Gene gene, String val)throws NotValidGeneException{
		gene.setTrait(val);
		boolean flag= false;
		for(int i=0;!flag && i<alleles.length;i++)
			flag= val.equals(alleles[i]);
		if(!flag)
			throw new NotValidGeneException("The trait value " + val + " does not belong to the possible alleles for this Nominal Gene");
	}
	
	/**Assigns a random value to the trait of a nominal GAL_Gene object with an equal probability to get any allele in the array of alleles as the trait value.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void setRandomValueTo(GAL_Gene gene){
		int pos= rand.nextInt(alleles.length);
		gene.setTrait(alleles[pos]);
	}
	
	/**Change the trait of an nominal GAL_Gene object, to another one in the alleles.
	*@param gene The Gal_Gene wich trait is going to be modified.
	*/
	public void changeValueTo(GAL_Gene gene){
		String trait= (String)gene.getTrait();
		int pos, i=0;
		pos= rand.nextInt(alleles.length-1);
		while(!alleles[i].equals(trait))
			i++;
		if(pos>=i)
			pos++;
		gene.setTrait(alleles[pos]);
	}
	
	/**Gets the allele in the pos denoted by the int parameter.
	*@param pos The position of the allele to be returned.
	*@return The allele in the pos denoted by the int parameter.
	*/
	public String getAllele(int pos){
		return alleles[pos];
	}

	/**Returns the number of Alleles there are in the alleles array.
	*@return The number of Alleles there are in the alleles array.
	*/
	public int numberOfAlleles(){
		return alleles.length;
	}
}