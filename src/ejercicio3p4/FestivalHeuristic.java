package ejercicio3p4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FestivalHeuristic {
	public static Double heuristic(FestivalVertex v1,
			Predicate<FestivalVertex> goal, FestivalVertex v2) {
			Double res = 0.;
			for (int i=v1.index()/DatosFestival.getNumAreas();
			i<DatosFestival.getNumTiposEntrada(); i++) {
			res += DatosFestival.getCuotaMinima(i) -
			v1.entradasTipos().get(i);
			}
			return res;
	}
}
