package ejercicio3p4;

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
	
	public static EGraph<FestivalVertex, FestivalEdge> iniGraph() {
		return EGraph.virtual(FestivalVertex.start())
				.pathType(PathType.Sum)
				.type(Type.Max)
				.edgeWeight(FestivalEdge::weight)
				.heuristic(FestivalHeuristic::heuristic)
				.build();
	}
	
	public static void print(String alg,GraphPath<FestivalVertex, FestivalEdge> s) {
		List<Integer> ls=s.getEdgeList().stream().map(e->e.action()).toList();
		String2.toConsole("%s = %.2f:\n%s\n%s", alg,s.getWeight(), SolucionFestival.create(ls),String2.linea());
	}

	public static void main(String[] args) {
		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada1.txt");
		
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
