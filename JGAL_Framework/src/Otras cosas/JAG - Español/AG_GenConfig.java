package JAG;

import java.util.Random;

public abstract class AG_GenConfig<T>{
	
	protected Random rand;
	
	//Constructor base para AG_GenConfig
	public AG_GenConfig(){
		rand= new Random();
	}
	
	public AG_Gen createNewGen(){
		AG_Gen ret = new AG_Gen();
		setRandomValueTo(ret);
		return ret;
	}
	
	//Asigna el valor del `gen2` al `gen`
	@SuppressWarnings("unchecked")
	public void setValueToFrom(AG_Gen gen, AG_Gen gen2) throws NotValidGeneException,ClassCastException{
		setValueTo(gen,(T)gen2.getRasgo());
	}
	
	//Asigna un valor aleatorio al AG_Gen `gen`
	public abstract void setRandomValueTo(AG_Gen gen);
	
	//Asigna el valor `val` al AG_Gen `gen`
	public abstract void setValueTo(AG_Gen gen, T val) throws NotValidGeneException;
}