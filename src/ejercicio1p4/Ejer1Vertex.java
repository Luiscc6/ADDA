package ejercicio1p4;

import us.lsi.graphs.virtual.VirtualVertex;


public interface Ejer1Vertex extends
VirtualVertex<Ejer1Vertex, Ejer1Edge, Integer> {
	
	Integer indice();
	Integer espaciores();
	Integer espacioini();
	
	public static Ejer1Vertex start() {
		return new Ejer1VertexI(0,DatosAlmacenes.getMetrosCubicosAlmacen(indice()));
	}
	

}
