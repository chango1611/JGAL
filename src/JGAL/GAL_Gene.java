package JGAL;

/**The GAL_Gene class represents all type of genes.*/
public class GAL_Gene{
	
	/**An Object that represents the trait of the gene.*/
	private Object trait;
	
	/**Initializes a new GAL_Gene wich trait is set on null.*/
	public GAL_Gene(){
		trait= null;
	}
	
	/**Constructs a new GAL_Gene object wich trait field is assigned whith the trait argument.
	* @param trait The trait to be assigned.
	*/
	public GAL_Gene(Object trait){
		this.trait= trait;
	}
	
	/**Gets the trait field value.
	*@return An object representing the trait field.
	*/
	public Object getTrait(){
		return trait;
	}
	
	/**Sets the trait field value.
	*@param trait The trait to be set.
	*/
	public void setTrait(Object trait){
		this.trait= trait;
	}
	
	/**Returns a String object representing this GAL_Gene.
	*@return A string representation of this GAL_Gene.
	*/
	public String toString(){
		return ""+trait;
	}
	
	/**Returns a copy of this GAL_Gene.
	*@return A copy of this GAL_Gene.
	*/
	public GAL_Gene clone(){
		Object trait2= trait;
		return new GAL_Gene(trait2);
	}
}