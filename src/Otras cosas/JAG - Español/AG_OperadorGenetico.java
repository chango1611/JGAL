package JAG;

import java.util.Random;
import java.util.LinkedList;

public abstract class AG_OperadorGenetico{
	
	protected Random rand;
	protected double[] prob;
	
	public AG_OperadorGenetico(double[] prob){
		rand= new Random();
		this.prob= prob;
	}
	
	//Divide un arreglo de cromosomas en 2 arreglos de cromosomas distribuidos segun la probabilidad `prob[p]`
	public AG_Cromosoma[][] distribuirCromosomas(AG_Cromosoma[] cromosomas, double prob){
		LinkedList<AG_Cromosoma> crom1= new LinkedList<AG_Cromosoma>(), crom2= new LinkedList<AG_Cromosoma>();
		for(int i=0; i<cromosomas.length; i++){
			if(rand.nextDouble()<=prob)
				crom1.add(cromosomas[i]);
			else
				crom2.add(cromosomas[i]);
		}
		AG_Cromosoma[][] ret= {crom1.toArray(new AG_Cromosoma[0]), crom2.toArray(new AG_Cromosoma[0])};
		return ret;
	}
	
	//Enpareja cromosomas en un arreglo de parejas, trios o mas
	public AG_Cromosoma[][] agruparCromosomas(AG_Cromosoma[] crom,int enp){
		int tam= crom.length/enp, i, j;
		AG_Cromosoma[][] ret= new AG_Cromosoma[tam][enp];
		AG_Util.barajear(crom);
		//Coloca los cromosomas en parejas, trios o mas
		for(i=0;i*enp<=tam;i++)
			for(j=0;j<enp;j++)
				ret[i][j]= crom[i*enp+j];
		return ret;
	}
	
	//Si el primer grupo de cromosomas no es agrupable en n grupos, entonces corrige el error en la agrupacion 
	public void corregirAgrupabilidad(AG_Cromosoma[][] crom, int n){
		int p= crom[0].length%n;
		if(p!=0){
			AG_Cromosoma[][] aux= AG_Util.divisorAleatorio(crom[0],p,new AG_Cromosoma[0][0]);
			crom[0]= aux[0];
			crom[1]= AG_Util.concatenarArreglos(crom[1],aux[1],new AG_Cromosoma[0]);
		}
	}
	
	public abstract AG_Poblacion aplicarOperador(AG_Poblacion padres);
}