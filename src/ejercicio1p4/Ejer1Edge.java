package ejercicio1p4;

import us.lsi.graphs.virtual.SimpleEdgeAction;


public record Ejer1Edge(Ejer1Vertex source,Ejer1Vertex target,Integer action, Double weight) 
implements SimpleEdgeAction<Ejer1Vertex, Integer> {
	
	//hay que hacer el of
	public static Ejer1Edge of (Ejer1Vertex s, Ejer1Vertex t, Integer a) {
		Double w = f(a,s);
		return new Ejer1Edge(s,t,a,w);
	}
	
	
}
