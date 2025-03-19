package ejercicio2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Map2;

public class SolucionCursos {

    public static SolucionCursos create(List<Integer> ls) {
        return new SolucionCursos(ls);
    }

    private Integer numCursos;
    private Map<Integer, Integer> solucion;
    private Double puntuacionTotal;
    private Integer costeTotal;

    /*
    private SolucionCursos(List<Integer> ls) {
        solucion = Map2.empty();
        puntuacionTotal = 0.;
        costeTotal = 0;
        
        for (int i = 0; i<ls.size(); i ++) {
        	if(ls.get(i)>=1) {
        	Integer curso = i;
        	Integer selec = ls.get(i);
        	costeTotal = costeTotal +(curso * DatosCursos.getCoste(i));
        	puntuacionTotal += DatosCursos.getRelevancia(i);
        	solucion.put(curso, selec);
        	numCursos++;
        	}
        }
        	
    }
    */
    
    private SolucionCursos(List<Integer> ls) { //PLE
    	//TODO
    	puntuacionTotal = 0.;
    	costeTotal = 0;
    	solucion = Map2.empty();
    	for(int i=0; i<DatosCursos.getNumCursos(); i++) {
    	if(ls.get(i)>0) {
    	solucion.put(i, 1);
    	puntuacionTotal+=DatosCursos.getRelevancia(i);
    	costeTotal+=DatosCursos.getCoste(i);
    	}
    	}
    	numCursos = solucion.size();
    	}

    @Override
    public String toString() {
        return solucion.entrySet().stream()
                .map(p -> "Curso " + p.getKey() + ": Seleccionado")
                .collect(Collectors.joining("\n", "Cursos seleccionados:\n", String.format("\nTotal de cursos seleccionados: %d\nPuntuación total: %.2f\nCoste total: %d", numCursos, puntuacionTotal, costeTotal)));
    }

    public Integer getNumCursos() {
        return numCursos;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public Integer getCosteTotal() {
        return costeTotal;
    }
}
