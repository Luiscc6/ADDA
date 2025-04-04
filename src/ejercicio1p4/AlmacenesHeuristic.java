package ejercicio1p4;

import java.util.function.Predicate;

public class AlmacenesHeuristic {
	public static Double heuristic(AlmacenesVertex v1, Predicate<AlmacenesVertex> goal, AlmacenesVertex v2) {
		return DatosAlmacenes.getNumProductos()-v1.index()+0.0;
	}
}
