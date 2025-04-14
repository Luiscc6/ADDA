package ejercicio2p4;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class AlmacenesHeuristic {
	public static Double heuristic(CursosVertex v1, Predicate<CursosVertex> goal, CursosVertex v2) {
		return IntStream.range(v1.index(), DatosCursos.getNumCursos())
				.mapToDouble(x->DatosCursos.getRelevancia(v1.index()))
				.sum();
	}
}
