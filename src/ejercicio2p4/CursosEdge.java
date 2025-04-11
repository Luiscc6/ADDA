package ejercicio2p4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CursosEdge(CursosVertex source, CursosVertex target, Integer action, Double weight) implements SimpleEdgeAction<CursosVertex, Integer> {
	
	public static CursosEdge of(CursosVertex s, CursosVertex t, Integer a) {
		Double w = 0.0;
		w+=a<DatosCursos.getNumAreas()?1:0;
		return new CursosEdge(s,t,a,w);
	}
}
