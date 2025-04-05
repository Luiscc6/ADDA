package ejercicio1p4;

import java.util.List;
import java.util.Set;

import ejercicio1p4.DatosAlmacenes.Producto;
import us.lsi.common.List2;

public record AlmacenesVertexI(Integer index, List<List<Integer>> reparto, List<Integer> cpRest) implements AlmacenesVertex {

	@Override
	public List<Integer> actions() {
		List<Integer> asg = List2.of();
		int aux = 0;
		if (index == DatosAlmacenes.getNumProductos()) {
			return asg; //Si no podemos seguir ya que index == n, devolvemos una lista vacia
		}
		for (int i = 0; i<DatosAlmacenes.getNumAlmacenes(); i++) {
			aux = cpRest.get(i); //capacidad restante del almacen i
			if (DatosAlmacenes.getMetrosCubicosProducto(index) <= aux) {//verificamos que el producto quepa en el almacen
				for(List<Integer> ls :reparto) {
					for(int e: ls) {
//						Producto aux1 = DatosAlmacenes.getProducto(index);
//						Producto aux2 = DatosAlmacenes.getProducto(e);
						if(!DatosAlmacenes.sonIncompatibles(index, e)) {//verificamos si son compatibles, le añadimos a lista el almacen
							asg.add(i);
							
						}
					}
				}
			}
			else {
				asg.add(-1);//esto es cuando no asignamos nada
			}
		
		}
			
		return asg;
	}

	@Override
	public AlmacenesVertex neighbor(Integer a) {
		
		if (a ==-1) {
			return new AlmacenesVertexI(index+1, reparto, cpRest);
		}
		List<List<Integer>> reparto2 = List2.copy(reparto);
		List<Integer> cpRest2 = List2.copy(cpRest);
		
		int aux = 0;
		int aux2 = cpRest2.get(a);
		aux = DatosAlmacenes.getMetrosCubicosProducto(index);
		cpRest2.set(a, aux2-aux); //restamos m2 del producto al almacen
		reparto2.get(a).add(index);
		
		return new AlmacenesVertexI(index+1, reparto2, cpRest2);
		
		
		
	}

	@Override
	public AlmacenesEdge edge(Integer a) {
		return AlmacenesEdge.of(this, neighbor(a), a);
	}
	
	
//	@Override
//	public Boolean goal() {
//		return index==DatosAlmacenes.getNumProductos();
//	}

}
