import JGAL.*;

public class miFuncionAptitud extends GAL_FitnessFunction{

	double[] points;
	double[] weight;
	double max_weight;
	
	public miFuncionAptitud(double[] points, double[] weight, double max_weight){
		this.points= points;
		this.weight= weight;
		this.max_weight= max_weight;
	}
	
	public double computeFitness(GAL_Chromosome chromosome){
		double p= 0, w=0;
		
		for(int i=0;i<10;i++)
			if((byte)chromosome.getGene(i).getTrait() == (byte)1){
				p+=points[i]; w+= weight[i];
			}
		
		if(w>max_weight)
			return 0;
		return p;
	}
}