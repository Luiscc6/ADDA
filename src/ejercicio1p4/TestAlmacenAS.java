package ejercicio1p4;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.common.String2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAlmacenAS {
	
	public static EGraph<AlmacenesVertex, AlmacenesEdge> iniGraph() {
		return EGraph.virtual(AlmacenesVertex.start())
				.pathType(PathType.Sum)
				.type(Type.Max)
				.edgeWeight(AlmacenesEdge::weight)
				.heuristic(AlmacenesHeuristic::heuristic)
				.build();
	}
	
	public static void print(String alg,GraphPath<AlmacenesVertex, AlmacenesEdge> s) {
		List<Integer> ls=s.getEdgeList().stream().map(e->e.action()).toList();
		String2.toConsole("%s = %.2f:\n%s\n%s", alg,s.getWeight(), SolucionAlmacen.create(ls),String2.linea());
	}

	public static void main(String[] args) {
		DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada1.txt");
		
		var aEstrella= AStar.of(iniGraph());
		print("estrella",aEstrella.search().get());
		
		var voraz=GreedyOnGraph.of(iniGraph());
		print("voraz",voraz.search().get());
		
		var algPDR=PDR.of(iniGraph());
		print("PDR",algPDR.search().get());
		
		var algBT=BT.of(iniGraph());
		print("BT", algBT.search().get());
		
		
	}
}
