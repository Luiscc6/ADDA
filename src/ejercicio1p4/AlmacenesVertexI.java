package ejercicio1p4;

import java.util.List;
import java.util.Set;

public record AlmacenesVertexI(Integer index, List<List<Integer>> reparto, List<Integer> cpRest) implements AlmacenesVertex {

	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlmacenesVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlmacenesEdge edge(Integer a) {
		return AlmacenesEdge.of(this, neighbor(a), a);
	}

}
