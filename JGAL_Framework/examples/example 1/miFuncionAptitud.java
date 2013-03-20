import JGAL.*;

public class miFuncionAptitud extends GAL_FitnessFunction{

	public miFuncionAptitud(){}
	
	public double computeFitness(GAL_Chromosome chromosome){
		double fitness= 0;
		
		for(int i=0;i<20;i++)
			fitness+= (int)chromosome.getGene(i).getTrait()*(int)chromosome.getGene(i).getTrait();
		
		//Maximo valor posible 512^2 * 20 = 5242880
		return 5242880 - fitness;
	}
}