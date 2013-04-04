package JGAL;

/**The GAL_TerminationCondition is an abstract class for the termination condition.
*<p>
*The termination condition is used to end the run of a GA handler before getting to the maximum generation.
*<p>
*Subclasses of GAL_TerminationCondition must provide methods for evaluateTerminationCondition.
*/
public abstract class GAL_TerminationCondition{
	
	/**Initializes a new GAL_TerminationCondition*/
	public GAL_TerminationCondition(){}
	
	/**Verifies if the termination condition is archieved.
	*@param window The window of GAL_Population used to verify the termination condition.
	*@return true if the termination condition is archieved, false otherwise.
	*/
	public abstract boolean verifyTerminationCondition(GAL_Population[] window);
}