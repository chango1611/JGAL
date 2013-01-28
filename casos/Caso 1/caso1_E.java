import JGAL.*;

class caso1_E{

	public static void main(String[] args)throws Exception{
		GAL_GeneConfig[] geneConfig= new GAL_GeneConfig[20];
		
		//Creo los genes
		for(int i=0;i<20;i++)
			geneConfig[i]= new GAL_IntegerGeneConfig(-512,512);
		
		//Creo la configuracion
		GAL_Configuration config= new GAL_Configuration(new GAL_ChromosomeConfig(geneConfig),new miCondicionTerminacion(),new miFuncionAptitud(), new GAL_TournamentSelector(2),
		new GAL_GeneticOperator[] {new GAL_ClassicCrossover(0.7),new GAL_Mutation(0.015)});
		
		//Creo el handler
		GAL_Handler handler= new GAL_Handler(config,100,50,1);
		handler.runGAL();
		
		System.out.println("Best from all generations:\n" + handler.getBestFromAll());
		System.out.println("Valor: " + (5242880 - handler.getBestFromAll().getFitness()));
				
		System.out.println("\nAverage fitness from all generations:\n" + handler.getAverageFitnessFromAll());
	}

}