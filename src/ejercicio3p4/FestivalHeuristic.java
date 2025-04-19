package ejercicio3p4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FestivalHeuristic {
	public static Double heuristic(FestivalVertex v1, Predicate<FestivalVertex> goal, FestivalVertex v2) {
		// return
		// DatosFestival.getCosteAsignacion(v1.index()/DatosFestival.getNumTiposEntrada(),
		// v1.index()%DatosFestival.getNumTiposEntrada())-v1.index()+0.0;
		return IntStream.range(v1.index(), DatosFestival.getNumAreas()).mapToDouble(i -> mejorOpcion(i)).sum();
	}

	private static Double mejorOpcion(Integer i) {
	    return IntStream.range(0, DatosFestival.getNumTiposEntrada())
	            .mapToDouble(j -> {
	                Integer cost = DatosFestival.getCosteAsignacion(i, j);
	                return cost != null ? cost.doubleValue() : 1000000.0;
	            })
	            .min().orElse(0.0);
	}
}
