package JAG;

public class AG_Gen{
	private Object rasgo;
	
	//Constructor base para AG_Gen
	public AG_Gen(){
		rasgo= null;
	}
	
	//Constructor para AG_Gen incluyendo el valor del rasgo
	public AG_Gen(Object rasgo){
		this.rasgo= rasgo;
	}
	
	//Retorna el valor actual del Rasgo
	public Object getRasgo(){
		return rasgo;
	}
	
	//Asigna un valor al rasgo
	public void setRasgo(Object rasgo){
		this.rasgo= rasgo;
	}
	
	public String toString(){
		return ""+rasgo;
	}
	
	public AG_Gen clone(){
		Object rasgo2= rasgo;
		return new AG_Gen(rasgo2);
	}
}