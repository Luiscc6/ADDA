package ejercicio33;

import java.util.List;
import java.util.Locale;

import _soluciones.SolucionInvestigadores;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestInvestigadores {
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		InRangeInvestigadoresAG p1 = new InRangeInvestigadoresAG("ficheros/Ejercicio3DatosEntrada2.txt");
		
		AlgoritmoAG<List<Integer>,SolucionInvestigadores> ap1= AlgoritmoAG.of(p1);
		
		ap1.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap1.bestSolution());
		System.out.println("================================");

	}
		

}
