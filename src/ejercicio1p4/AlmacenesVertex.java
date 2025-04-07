package ejercicio1p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public interface AlmacenesVertex extends VirtualVertex<AlmacenesVertex, AlmacenesEdge, Integer> {
	Integer index();
	List<List<Integer>> reparto();
	List<Integer> cpRest();
	
	
	public static AlmacenesVertexI start() {
	    List<List<Integer>> reparto = new ArrayList<>();
	    for (int i = 0; i < DatosAlmacenes.getNumAlmacenes(); i++) {
	        reparto.add(new ArrayList<Integer>()); 
	    }
	    List<Integer> cprestante = new ArrayList<>(); 
	    
	    
	    for (int i = 0; i < DatosAlmacenes.getNumAlmacenes(); i++) {
	        cprestante.add(DatosAlmacenes.getMetrosCubicosAlmacen(i));
	    }
	    
	    var vertice = new AlmacenesVertexI(0, reparto, cprestante);
	    System.out.println("soy vertice:  -  " + vertice);
	    return vertice;
	}
	//[0,[[],[]]],[60,40]
}
