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
	    List<Integer> entraT = List2.empty();
	    List<Integer> entraA = List2.empty();
	    for(int k =0; k<DatosFestival.getNumTiposEntrada(); k++) {
	    	entraT.add(DatosFestival.getCuotaMinima(k));
	    	
	    }
	    for(int z =0; z<DatosFestival.getNumAreas(); z++) {
	    	entraA.add(0);
	    	
	    }
	    var vertice = new FestivalVertexI (0,entraT,entraA);
	    System.out.println(vertice);
	    return vertice;
		
	}
	
}
