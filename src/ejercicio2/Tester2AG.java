package ejercicio2;

import java.util.List;
import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class Tester2AG {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		// En Testejer1AG.java:

		AlgoritmoAG.ELITISM_RATE = 0.05;    // Reducir elitismo
		AlgoritmoAG.CROSSOVER_RATE = 0.9;    // Mantener crossover alto
		AlgoritmoAG.MUTATION_RATE = 0.7;     // Aumentar mutación
		AlgoritmoAG.POPULATION_SIZE = 1000;  // Población más grande
		StoppingConditionFactory.NUM_GENERATIONS = 1000; // Más generaciones
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		Ejer2AG p = new Ejer2AG("resources/ejercicio2/DatosEntrada1.txt");
		
		
		AlgoritmoAG<List<Integer>,SolucionCursos> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		

		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
	}

}
