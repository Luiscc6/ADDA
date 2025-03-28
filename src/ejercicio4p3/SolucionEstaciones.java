package ejercicio4p3;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.List2;

public class SolucionEstaciones {

    public static SolucionEstaciones create(List<Integer> ls) {
        return new SolucionEstaciones(ls);
    }
    
    private Integer numEstaciones;
    private List<Estacion> camino;
    private Double tiempoTotal;
    private Double tiempoMedio;

    private SolucionEstaciones(List<Integer> ls) {
    	numEstaciones = 0;
    	List<Estacion> camino = List2.empty();
    	Double tiempoTotal = 0.0;
    	Double tiempoMedio = 0.0;
    	

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Resumen del recorrido:\n");

        result.append("Camino seguido: ").append(camino.stream()
                .map(Estacion::nombre)
                .collect(Collectors.joining(" -> "))).append("\n");

        result.append(String.format("Tiempo total: %.2f min\n", tiempoTotal));
        result.append(String.format("Tiempo medio por estación: %.2f min\n", tiempoMedio));

        return result.toString();
    }

    public Integer getNumEstaciones() {
        return numEstaciones;
    }

    public List<Estacion> getCamino() {
        return camino;
    }

    public Double getTiempoTotal() {
        return tiempoTotal;
    }

    public Double getTiempoMedio() {
        return tiempoMedio;
    }
}
