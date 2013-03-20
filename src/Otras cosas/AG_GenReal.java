package JAG;

public class AG_GenReal extends AG_GenNumerico<Double>{
	
	//Constructor base del AG_GenReal
	public AG_GenReal(){
	}
	
	//Constructor del AG_GenReal
	public AG_GenReal(Double rasgo){
		super(rasgo);
	}
	
	//Constructor del AG_GenReal
	public AG_GenReal(Double min, Double max){
		super(min,max);
	}
	
	//Constructor del AG_GenReal
	public AG_GenReal(Double rasgo, Double min, Double max){// throws NotValidGeneException{
		super(rasgo,min,max);
	}

}