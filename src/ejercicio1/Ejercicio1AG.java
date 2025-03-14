package ejercicio1;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class Ejercicio1AG {
	public static SolucionAlmacen solucion(String file) {
		DatosAlmacenes.iniDatos(file);
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		var alg = AlgoritmoAG.of(new InRangeAlmacen());
		alg.ejecuta();
		var sol = alg.bestSolution();
		System.out.println(sol);
		return sol;
	}
	
	public static void main(String[] args) {
		solucion("resources/ejercicio1/DatosEntrada2.txt");
		
	}
}
