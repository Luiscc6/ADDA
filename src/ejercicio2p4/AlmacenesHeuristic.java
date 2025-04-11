package ejercicio2p4;

import java.util.function.Predicate;

public class AlmacenesHeuristic {
	public static Double heuristic(CursosVertex v1, Predicate<CursosVertex> goal, CursosVertex v2) {
		return DatosCursos.getNumCursos()-v1.index()+0.0;
	}
}
