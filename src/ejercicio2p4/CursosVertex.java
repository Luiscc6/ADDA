package ejercicio2p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public interface CursosVertex extends VirtualVertex<CursosVertex, CursosEdge, Integer> {
	Integer index();
	Integer prepres();
	Set<Integer> areascubi();
	List<Integer> cursec();
	
	
	public static CursosVertexI start() {
		
		Integer prepres= DatosCursos.getPresupuestoTotal();
		Set<Integer> areascubi = Set2.empty();
		List<Integer> cursec = List2.empty();
		var vertice = new CursosVertexI(0,prepres,areascubi,cursec);
		System.out.println(vertice);
		return vertice;
		
	   
	}
	//
}
