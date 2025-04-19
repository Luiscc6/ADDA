package ejercicio1p4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;


public class AlmacenPD {
	
	
	public static record Spm(Integer a, Integer weight) implements Comparable<Spm> {
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static Map<AlmacenProblem, Spm> memory;
	public static Integer mejorValor = Integer.MAX_VALUE; 

	public static SolucionAlmacen search() {
		memory =  Map2.empty();
		mejorValor = Integer.MAX_VALUE; // Estamos minimizando
		
		pdr_search(AlmacenProblem.initial(), 0, memory);
		return getSolucion();
	}

	private static Spm pdr_search(AlmacenProblem prob, Integer acumulado, Map<AlmacenProblem, Spm> memoria) {

		Spm res = null;
		Boolean esTerminal = prob.index().equals(DatosAlmacenes.getNumProductos());
		Boolean esSolucion = prob.index().equals(DatosAlmacenes.getNumProductos());

		if (memory.containsKey(prob)) {
			res = memory.get(prob);
			
		} else if (esTerminal && esSolucion) {
			res = Spm.of(null, 0);
			memory.put(prob, res);
			if (acumulado < mejorValor) { // Estamos minimizando
				mejorValor = acumulado;
			}
		} else {
			List<Spm> soluciones = List2.empty();
			for (Integer action : prob.actions()) {
				Double cota = acotar(acumulado, prob, action);   		
				if (cota > mejorValor) {
					continue;
				}
				AlmacenProblem vecino = prob.neighbor(action);
				Spm s = pdr_search(vecino, acumulado + action, memory);
				if (s != null) {
					Spm amp = Spm.of(action, s.weight() + action);
					soluciones.add(amp);
				}
			}
			// Estamos minimizando
			res = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
			if (res != null)
				memory.put(prob, res);
		}

		return res;
	}

	private static Double acotar(Integer acum, AlmacenProblem p, Integer a) {
		return acum + a + p.neighbor(a).heuristic();
	}

	public static SolucionAlmacen getSolucion() {
		List<Integer> acciones = List2.empty();
		AlmacenProblem prob = AlmacenProblem.initial();
		Spm spm = memory.get(prob);
		while (spm != null && spm.a != null) {
			AlmacenProblem old = prob;
			acciones.add(spm.a);
			prob = old.neighbor(spm.a);
			spm = memory.get(prob);
		}
		return SolucionAlmacen.of(acciones);
	}

}
