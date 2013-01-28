import JGAL.*;

class caso2_A{

	public static void main(String[] args)throws Exception{
		GAL_GeneConfig[] geneConfig= new GAL_GeneConfig[10];
		
		//Creo los genes
		for(int i=0;i<10;i++)
			geneConfig[i]= new GAL_BinaryGeneConfig();
		
		double[] p= {45,10,71,49,41,26,32,18,23,15}, w={12,30,54,23,56,42,23,60,54,10};
		double mw= 250;
		
		//Creo la configuracion
		GAL_Configuration config= new GAL_Configuration(new GAL_ChromosomeConfig(geneConfig),new miCondicionTerminacion(),new miFuncionAptitud(p,w,mw), new GAL_ClassicRankingSelector(2),
		new GAL_GeneticOperator[] {new GAL_SegmentCrossover(0.7,0.5),new GAL_Mutation(0.0175)});
		
		//Creo el handler
		GAL_Handler handler= new GAL_Handler(config,20,50,1);
		handler.runGAL();
		
		GAL_Chromosome c= handler.getBestFromAll();
		double tw=0;
		for(int i=0;i<10;i++)
			if((byte)c.getGene(i).getTrait() == (byte) 1)
				tw+= w[i];
				
		System.out.println("Best from all generations:\n" + c);
		System.out.println("Valor: " + c.getFitness());
		System.out.println("Peso: " + tw);

		System.out.println("\nAverage fitness from all generations:\n" + handler.getAverageFitnessFromAll());
	}

}