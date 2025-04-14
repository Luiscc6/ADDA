package ejercicio3p4;

import java.util.function.Predicate;

public class FestivalHeuristic {
	public static Double heuristic(FestivalVertex v1, Predicate<FestivalVertex> goal, FestivalVertex v2) {
		return DatosFestival.getNumTiposEntrada()-v1.index()+0.0;
	}
}
