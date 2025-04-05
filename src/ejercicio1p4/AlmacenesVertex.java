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
		//TODO
		List<List<Integer>> reparto = List2.of(new ArrayList<Integer>());
		System.out.println(reparto);
		List<Integer> cprestante = List2.of();
		for(int i = 0; i <DatosAlmacenes.getNumAlmacenes(); i++) {
			System.out.println(i);
			cprestante.add(DatosAlmacenes.getMetrosCubicosAlmacen(i));
		}
		System.out.println(cprestante);
		var vertice = new AlmacenesVertexI(0,reparto,cprestante);
		System.out.println(vertice);
		return vertice;
	}
	//[0,[[]],[60,40]
}
