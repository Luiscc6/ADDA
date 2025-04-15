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

    // --- CONSTRUCTOR CORREGIDO ---
    private SolucionFestival(List<Integer> ls) { // ls = lista de acciones 'a' del camino
        this.solucion = Map2.empty(); // O new HashMap<>();
        this.costeTotal = 0.;
        this.unidadesTotales = 0;
        int numAreas = DatosFestival.getNumAreas();
        // int numTipos = DatosFestival.getNumTiposEntrada(); // No estrictamente necesario aquí

        // Iteramos sobre las acciones tomadas en el camino 'ls'
        // La longitud de 'ls' es el número de pasos/decisiones tomadas
        for (int index = 0; index < ls.size(); index++) {
            Integer unidades_asignadas = ls.get(index); // La acción 'a' tomada en el paso 'index'

            // Si la acción fue asignar 0 unidades, podrías omitirla del mapa
            // o incluirla si necesitas explícitamente las asignaciones cero.
            // Vamos a incluirla para que el mapa refleje todas las decisiones.
            // if (unidades_asignadas == 0) continue; // Opción: omitir ceros

            // Determinar tipo 'i' y área 'j' para este 'index'
            int tipo = index / numAreas;
            int area = index % numAreas;

            // Guardar la asignación x_ij = unidades_asignadas
            // Usamos 'index' como clave por simplicidad, mapea a i*numAreas+j
            this.solucion.put(index, unidades_asignadas);

            // Calcular coste y unidades totales (solo si se asignaron unidades > 0)
             if (unidades_asignadas > 0) {
                 this.costeTotal += DatosFestival.getCosteAsignacion(tipo, area) * unidades_asignadas;
                 this.unidadesTotales += unidades_asignadas;
             }
             // Si el coste aplica incluso si asignas 0 (raro), quita el if
        }

        // Decide qué significa numAsignaciones. ¿Decisiones tomadas? ¿Asignaciones > 0?
        // Opción 1: Número de decisiones tomadas (longitud del camino)
        this.numAsignaciones = ls.size();
        // Opción 2: Número de asignaciones con unidades > 0
        // this.numAsignaciones = (int) this.solucion.values().stream().filter(u -> u > 0).count();
    }


    // --- MÉTODO toString() ---
    // (Tu método toString parece correcto, ya que itera sobre solucion.entrySet()
    // y maneja bien si el mapa no contiene todas las claves posibles)
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Resumen de asignaciones:\n");

        Map<Integer, Integer> aforoOcupadoPorArea = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> entradasPorArea = new HashMap<>();

        // Itera sobre las entradas del mapa 'solucion' que SÍ existen
        for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
            Integer index = entry.getKey(); // Clave = i * numAreas + j
            Integer unidades = entry.getValue();

            if (unidades > 0) {
                 int tipoEntrada = index / DatosFestival.getNumAreas();
                 int area = index % DatosFestival.getNumAreas();

                aforoOcupadoPorArea.put(area, aforoOcupadoPorArea.getOrDefault(area, 0) + unidades);

                // Inicializa el mapa interno para el área si es necesario
                entradasPorArea.putIfAbsent(area, new HashMap<>());
                // Obtiene el mapa interno y actualiza las unidades para el tipo de entrada
                Map<Integer, Integer> entradasTipoEnArea = entradasPorArea.get(area);
                entradasTipoEnArea.put(tipoEntrada, entradasTipoEnArea.getOrDefault(tipoEntrada, 0) + unidades);
            }
        }

        // Construye el string de salida iterando sobre las áreas CON entradas asignadas
        for (int i = 0; i < DatosFestival.getNumAreas(); i++) {
            Integer aforoOcupado = aforoOcupadoPorArea.getOrDefault(i, 0);
            if (aforoOcupado > 0) {
                result.append(String.format("Aforo área %s: %d/%d\n",
                        DatosFestival.getArea(i).nombre(),
                        aforoOcupado,
                        DatosFestival.getAforoMaximoArea(i)));

                // Accede al mapa de tipos para esta área (puede ser nulo si no hay entradas)
                Map<Integer, Integer> tiposEnArea = entradasPorArea.get(i);
                if (tiposEnArea != null) {
                    tiposEnArea.forEach((tipoEntrada, unidades) ->
                        result.append(String.format("  Tipo de entrada %s asignadas: %d unidades\n", // Añadido indentación
                                DatosFestival.getTipoEntrada(tipoEntrada).tipo(), unidades))
                    );
                }
            }
        }

        result.append(String.format("\nCoste total: %.2f\nUnidades totales: %d\n", costeTotal, unidadesTotales));

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
}