package ejercicio3p4;

import java.util.List;
import java.util.Map;
import us.lsi.common.Map2; // Asegúrate que esta importación sea correcta
import java.util.HashMap; // Necesario para el toString()

public class SolucionFestival {

    // --- MÉTODO create() ---
    public static SolucionFestival create(List<Integer> ls) {
        // ls es la lista de acciones 'a' de la solución encontrada por el grafo
        return new SolucionFestival(ls);
    }

    // --- Atributos ---
    private Integer numAsignaciones; // Considera qué debe representar esto realmente
    private Map<Integer, Integer> solucion; // Clave: index (i*numAreas+j), Valor: unidades asignadas (xij)
    private Double costeTotal;
    private Integer unidadesTotales;
    
    private Integer i(Integer z) {
    	return DatosFestival.getI(z);
    	}
    	private Integer j(Integer z) {
    		System.out.println(DatosFestival.getJ(z));
    	return DatosFestival.getJ(z);
    	}
    	private Integer jPrima(int z) {
    		System.out.println(DatosFestival.getJPrima(z));
			return DatosFestival.getJPrima(z);
		}

   
    		private SolucionFestival(List<Integer> ls) {
    		numAsignaciones = 0;
    		solucion = Map2.empty();
    		costeTotal = 0.;
    		unidadesTotales = 0;
    		for (int z=0; z<ls.size(); z++) {
    		numAsignaciones++;
    		solucion.put(z, ls.get(z));
    		costeTotal += DatosFestival.getCosteAsignacion(i(z), j(z)) *
    				ls.get(z);
    				unidadesTotales += ls.get(z);
    				}
    				}


    
	// --- MÉTODO toString() ---
    // (Tu método toString parece correcto, ya que itera sobre solucion.entrySet()
    // y maneja bien si el mapa no contiene todas las claves posibles)
    @Override
    public String toString() {
    	StringBuilder result = new StringBuilder("Resumen de asignaciones:\n");
    	Map<Integer, Integer> aforoOcupadoPorArea = new HashMap<>();
    	Map<Integer, Map<Integer, Integer>> entradasPorArea = new
    	HashMap<>();
    	for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
    	Integer tipoEntrada = i(entry.getKey());
    	Integer area = j(entry.getKey());
    	Integer unidades = entry.getValue();
    	if (unidades > 0) {
    	aforoOcupadoPorArea.put(area,
    	aforoOcupadoPorArea.getOrDefault(area, 0) + unidades);
    	entradasPorArea.computeIfAbsent(area, k -> new HashMap<>())
    	.put(tipoEntrada,
    	entradasPorArea.get(area).getOrDefault(tipoEntrada, 0) + unidades);
    	}
    	}
    	for (int i = 0; i < DatosFestival.getNumAreas(); i++) {
    	Integer aforoOcupado = aforoOcupadoPorArea.getOrDefault(i, 0);
    	if (aforoOcupado > 0) {
    	result.append(String.format("Aforo área %s: %d/%d\n",
    	DatosFestival.getArea(i).nombre(),
    	aforoOcupado,
    	DatosFestival.getAforoMaximoArea(i)));
    	entradasPorArea.getOrDefault(i, new
    	HashMap<>()).forEach((tipoEntrada, unidades) ->
    	result.append(String.format("Tipo de entrada %s asignadas: %d unidades\n",
    	DatosFestival.getTipoEntrada(tipoEntrada).tipo(), unidades)
    	));
    	}
    	}
    	result.append(String.format("\nCoste total: %.2f\nUnidades totales:%d\n", costeTotal, unidadesTotales));
    	return result.toString();
    	}


  

    // --- Getters --- (Sin cambios)
    public Integer getNumAsignaciones() {
        return numAsignaciones;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getCosteTotal() {
        return costeTotal;
    }

    public Integer getUnidadesTotales() {
        return unidadesTotales;
    }


	public static SolucionFestival of(List<Integer> acciones) {
		
		return new SolucionFestival(acciones);
	}
}