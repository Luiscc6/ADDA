package ejercicio1p4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AlmacenesEdge(AlmacenesVertex source, AlmacenesVertex target, Integer action, Double weight) implements SimpleEdgeAction<AlmacenesVertex, Integer> {
	public static AlmacenesEdge of(AlmacenesVertex s, AlmacenesVertex t, Integer a) {
		Double w = 0.0;
		w+=a<DatosAlmacenes.getNumAlmacenes()?1:0;
		return new AlmacenesEdge(s,t,a,w);
	}
}
