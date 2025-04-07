package ejercicio1p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ejercicio1p4.DatosAlmacenes.Producto;
import us.lsi.common.List2;

public record AlmacenesVertexI(Integer index, List<List<Integer>> reparto, List<Integer> cpRest)
		implements AlmacenesVertex {

	@Override
	public List<Integer> actions() {
		List<Integer> asg = List2.of();
		int aux = 0;
		if (index == DatosAlmacenes.getNumProductos()) {
			return asg; // Si no podemos seguir ya que index == n, devolvemos una lista vacia
		}
		for (int i = 0; i < DatosAlmacenes.getNumAlmacenes(); i++) {
			aux = this.cpRest.get(i); // capacidad restante del almacen i
			if (DatosAlmacenes.getMetrosCubicosProducto(index) <= aux) {// verificamos que el producto quepa en el											// almacen
				System.out.println("soy reparto:   -  " + this.reparto);
				if (this.reparto.get(i).stream().allMatch(x -> !DatosAlmacenes.sonIncompatibles(index, x))) {
					asg.add(i);
				} else {
					asg.add(-1); // si no son incompatibles, le añadimos a la lista el -1
				}
			}

		}
		System.out.println("soy asg:  -  " + asg);
		return asg;
	}

	@Override
	public AlmacenesVertex neighbor(Integer a) {
	   
	    List<List<Integer>> reparto2 = new ArrayList<>();
	    for (List<Integer> listaInterna : reparto) {
	        reparto2.add(new ArrayList<>(listaInterna));
	    }
	    List<Integer> cpRest2 = new ArrayList<>(cpRest);

	    if (a == -1) {
	        return new AlmacenesVertexI(index + 1, reparto2, cpRest2);
	    }

	    int metrosProducto = DatosAlmacenes.getMetrosCubicosProducto(index);
	    int capActual = cpRest2.get(a);

	    cpRest2.set(a, capActual - metrosProducto); 
	    reparto2.get(a).add(index);                 

	    return new AlmacenesVertexI(index + 1, reparto2, cpRest2);
	}

	@Override
	public AlmacenesEdge edge(Integer a) {
		return AlmacenesEdge.of(this, neighbor(a), a);
	}

	@Override
	public Boolean goal() {
		return this.index == DatosAlmacenes.getNumProductos();
	}

}
