package ejercicio1p4;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import ejercicio1.DatosAlmacenes;
import ejercicio1.DatosAlmacenes.Producto;
import us.lsi.common.Multiset;

public record SolucionAlmacen(Integer diferencia, List<Integer> solucion) implements Comparable<SolucionAlmacen>{

	
	
	public static SolucionAlmacen of(List<Integer> ls) {
		return null;
		//TODO
		
	}
	
	public static SolucionAlmacen ofEdges(List<Ejer1Vertex> list) {
		return null;
		//TODO
		
	}
	
	public static SolucionAlmacen of(GraphPath<Ejer1Vertex, Ejer1Vertex> path) {
		return SolucionAlmacen.ofEdges(path.getEdgeList());
	}
	
	
	public Integer size() {
		//TODO
		return null;
	}
	
	@Override
	public int compareTo(SolucionAlmacen other) {
		return this.size().compareTo(other.size());
	}
	
	@Override
	public String toString() {
		//TODO ejer1
		return null;
	}

    
    
}

