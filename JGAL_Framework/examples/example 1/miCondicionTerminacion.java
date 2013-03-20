import JGAL.*;

public class miCondicionTerminacion extends GAL_TerminationCondition{
	
	public miCondicionTerminacion(){}
	
	public boolean verifyTerminationCondition(GAL_Population[] window){
		return false; //No se quiere condicion de terminacion
	}
}