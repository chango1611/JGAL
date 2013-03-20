package JAG;

public class AG_GenBinarioConfig extends AG_GenConfig<Byte>{
	
	//Constructor base del AG_GenBinarioConfig
	public AG_GenBinarioConfig(){
	}
	
	//Asigna un valor aleatorio al AG_Gen `gen`
	public void setRandomValueTo(AG_Gen gen){
		if(rand.nextDouble()<0.5)
			gen.setRasgo((byte) 0);
		else
			gen.setRasgo((byte) 1);
	}
	
	public void setValueTo(AG_Gen gen, Byte val)throws NotValidGeneException{
		gen.setRasgo(val);
		if(val!=0 && val!=1)
			throw new NotValidGeneException("El rasgo " + val + " no corresponde a los valores 0 o 1 para el Gen Binario");
	}

}