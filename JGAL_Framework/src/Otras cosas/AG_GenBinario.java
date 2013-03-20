package JAG;

public class AG_GenBinario extends AG_Gen<Byte>{
	
	//Constructor base del AG_GenBinario
	public AG_GenBinario(){
		super((byte) 0);
	}
	
	//Constructor del AG_GenBinario
	public AG_GenBinario(Byte rasgo){// throws NotValidGeneException{
		super(rasgo);
		//if(rasgo!=0 && rasgo!=1)
		//	throw new NotValidGeneException("Valor del rasgo no es binario para el Gen Binario");
	}
	
	//Asigna un valor al rasgo
	public void setRasgo(Byte rasgo){// throws NotValidGeneException{
		this.rasgo= rasgo;
		//if(rasgo!=0 && rasgo!=1)
		//	throw new NotValidGeneException("Valor del rasgo no es binario para el Gen Binario");
	}

}