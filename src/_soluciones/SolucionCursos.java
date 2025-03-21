package _soluciones;

import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosCursos;
import _datos.DatosCursos.Curso;
import us.lsi.common.List2;

public class SolucionCursos {
	
	public static SolucionCursos create(List<Integer> ls) {
		return new SolucionCursos(ls);
	}
	
	private Double coste;
	private List<Curso> cursos;	

	private SolucionCursos(List<Integer> ls) {
//TODO
	}

	public String toString() {
		String s = cursos.stream().map(e -> e.codigo())
		.collect(Collectors.joining(", ", "Cursos elegidos: {", "}\n"));
		return String.format(s + "Coste Total: " + coste + "€");	
	}
	
	

}
