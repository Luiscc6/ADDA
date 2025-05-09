package ejercicio3p4;

import java.util.List;



import us.lsi.common.List2;


public class FestivalState {

	FestivalProblem actual;
	Double acumulado;
	List<Integer> acciones;
	List<FestivalProblem> anteriores;
	
	private FestivalState(FestivalProblem p, Double a, 
		List<Integer> ls1, List<FestivalProblem> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}

	public static FestivalState initial() {
		FestivalProblem pi = FestivalProblem.initial();
		return of(pi, 0., List2.empty(), List2.empty());
	}

	public static FestivalState of(FestivalProblem prob, Double acum, List<Integer> lsa,
			List<FestivalProblem> lsp) {
		return new FestivalState(prob, acum, lsa, lsp);
	}
	
	Double pesoArista(FestivalProblem p) {
		return
		DatosFestival.getCosteAsignacion(DatosFestival.getI(p.index()),
		DatosFestival.getJ(p.index())) + 0.;
		}

	public void forward(Integer a) {		
		acumulado += a*pesoArista(actual);
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}

	public void back() {
		int last = acciones.size() - 1;
		var prob_ant = anteriores.get(last);
		
		acumulado -= pesoArista(prob_ant);
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}

	public List<Integer> alternativas() {
		return actual.actions();
	}

	public Double cota(Integer a) {
		Double weight = a > 0 ? a*pesoArista(actual) : 0.;
		return acumulado + weight + actual.neighbor(a).heuristic();
	}

	public Boolean esSolucion() {
		return actual.goal();
	}

	public boolean esTerminal() {
		return actual.goal();
	}
	

	public SolucionFestival getSolucion() {
		return SolucionFestival.of(acciones);
	}

}
