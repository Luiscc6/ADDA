package ejercicio1p4;

import java.util.List;
import java.util.Set;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public interface AlmacenesVertex extends VirtualVertex<AlmacenesVertex, AlmacenesEdge, Integer> {
	Integer index();
	List<List<Integer>> reparto();
	List<Integer> cpRest();
	
	
	public static AlmacenesVertexI start() {
		//TODO
		List<List<Integer>> reparto = List2.of();
		List<Integer> cprestante = List2.of();
		
		return null;
	}
}
