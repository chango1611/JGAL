package JAG;

public class AG_CruceClasico extends AG_OperadorGenetico{

	public AG_CruceClasico(double prob){
		super(new double[] {prob});
	}
	
	public AG_Poblacion aplicarOperador(AG_Poblacion padres){
		AG_Cromosoma[][] crom1= distribuirCromosomas(padres.getCromosomas(),prob[0]), //Distribuye segun la prob[0] en padres y sobrevivientes
		crom2;
		corregirAgrupabilidad(crom1,2); //Si el primer grupo no es agrupable, modifica los grupos
		crom2= agruparCromosomas(crom1[0],2); //Crea agrupaciones que pueden ser usadas para operaciones multi-cromosomas
		for(int i=0; i<crom2.length; i++)
			crom2[i]= cruzar(crom2[i],rand.nextInt(crom2[i][0].getTamano()-1)+1); //Aplica la funcion de cruzar
		crom1[0]= AG_Util.concatenarArreglos(crom2,new AG_Cromosoma[0]); //Concatena los hijos
		return new AG_Poblacion(AG_Util.concatenarArreglos(crom1,new AG_Cromosoma[0])); //Concatena sobrevivientes con hijos
	}
	
	private AG_Cromosoma[] cruzar(AG_Cromosoma[] padres, int pos){
		AG_Cromosoma[] hijos= {padres[0].clone(),padres[1].clone()};
		hijos[0].cambiarGenesAPartirDe(padres[1],pos);
		hijos[1].cambiarGenesAPartirDe(padres[0],pos);
		return hijos;
	}
}