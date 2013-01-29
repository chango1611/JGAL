package JAG;

public class AG_GenEntero extends AG_GenNumerico<Integer>{
	
	//Constructor del AG_GenEntero
	public AG_GenEntero(){
	}
	
	//Constructor del AG_GenEntero
	public AG_GenEntero(Integer rasgo){
		super(rasgo);
	}
	
	//Constructor del AG_GenEntero
	public AG_GenEntero(Integer min, Integer max){
		super(min,max);
	}
	
	//Constructor del AG_GenEntero
	public AG_GenEntero(Integer rasgo, Integer min, Integer max) {//throws NotValidGeneException{
		super(rasgo,min,max);
	}

}