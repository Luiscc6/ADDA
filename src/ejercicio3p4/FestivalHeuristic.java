// File: src/ejercicio3p4/FestivalHeuristic.java
package ejercicio3p4;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FestivalHeuristic {

    public static Double heuristic(FestivalVertex v1, Predicate<FestivalVertex> goal, FestivalVertex v2) {
    	/*
        int aux = DatosFestival.getNumAreas() * DatosFestival.getNumTiposEntrada();
        int aux2 = DatosFestival.getNumAreas();
       
        return IntStream.range(v1.index(), aux)
                .mapToDouble(index -> {
                    int tipo = index / aux2;
                    int area = index % aux2;
                    return DatosFestival.getCosteAsignacion(tipo, area);
                })
                .sum();    
         }
         */
    	return 0.0;
    }
}
