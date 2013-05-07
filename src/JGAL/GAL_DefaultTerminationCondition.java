package JGAL;

/**The GAL_DefaultTerminationCondition is an termination condition that will always return false.
*/
public class GAL_DefaultTerminationCondition extends GAL_TerminationCondition{
	
	/**Initializes a new GAL_DefaultTerminationCondition*/
	public GAL_DefaultTerminationCondition(){}
	
	/**Verifies if the termination condition is archieved.
	*@param window The window of GAL_Population used to verify the termination condition.
	*@return true if the termination condition is archieved, false otherwise.
	*/
	public boolean verifyTerminationCondition(GAL_Population[] window){
		return false;
	}
}