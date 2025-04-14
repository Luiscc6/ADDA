package ejercicio3p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public interface FestivalVertex extends VirtualVertex<FestivalVertex, FestivalEdge, Integer> {
	Integer index();
	List<Integer> entradasTipos();
	List<Integer> entradasAreas();
	
	
	public static FestivalVertexI start() {
	    return null;
	}
	//[0,[[],[]]],[60,40]
}
