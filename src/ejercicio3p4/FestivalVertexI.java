package ejercicio3p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ejercicio1p4.DatosAlmacenes.Producto;
import us.lsi.common.List2;

public record FestivalVertexI(Integer index, List<Integer> entradasTipos, List<Integer> entradasAreas)
		implements FestivalVertex {

	@Override
	public List<Integer> actions() {
		return null;
	}

	@Override
	public FestivalVertex neighbor(Integer a) {
	   
	    return null;
	}

	@Override
	public FestivalEdge edge(Integer a) {
		return FestivalEdge.of(this, neighbor(a), a);
	}

	@Override
	public Boolean goal() {
		return this.index == DatosFestival.getNumAreas();
	}

}
