package ejercicio3p4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record FestivalEdge(FestivalVertex source, FestivalVertex target, Integer action, Double weight) implements SimpleEdgeAction<FestivalVertex, Integer> {
	public static FestivalEdge of(FestivalVertex s, FestivalVertex t, Integer a) {
		Double w = 0.0;
		Integer curTicket = s.index()/DatosFestival.getNumAreas();
		Integer curArea = s.index()%DatosFestival.getNumAreas();
		w+=a<DatosFestival.getNumTiposEntrada()*DatosFestival.getNumAreas()?(double)(a*DatosFestival.getCosteAsignacion(curTicket, curArea)):0;
		return new FestivalEdge(s,t,a,w);
	}
}
