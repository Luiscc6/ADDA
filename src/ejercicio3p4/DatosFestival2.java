package ejercicio3p4;

import java.io.IOException;
import java.util.ArrayList; // Necesario
import java.util.Comparator; // Necesario
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Necesario
import java.util.stream.IntStream;  // Necesario

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class DatosFestival2 {

    // ... (Record Area y Record TipoEntrada como estaban) ...
     public static record Area(String nombre, Integer aforoMaximo) {
         public static Area create(String s) {
            String[] v0 = s.split(":");
            String[] v1 = v0[1].split(";");
            String a = v1[0].split("=")[1].trim();
            return new Area(v0[0].trim(), Integer.parseInt(a));
         }
         @Override
         public String toString() { return nombre + ": " + aforoMaximo + "; "; }
     }

     public static record TipoEntrada(String tipo, Integer cuotaMinima, Map<Integer,Integer> costeAsignacion) {
         public static TipoEntrada create(String s) {
             String[] v0 = s.split("->");
             String[] v1 = v0[1].trim().split(";");
             Integer a = Integer.parseInt(v1[0].split("=")[1].trim());
             Map<Integer,Integer> b = Map2.empty();
             String[] v2 = v1[1].split("=")[1].trim().split(",");
             for(String e: v2) {
                 String[] v3 = e.trim().split("[:()]");
                 b.put(Integer.parseInt(v3[1].trim()), Integer.parseInt(v3[2].trim()));
             }
             return new TipoEntrada(v0[0].trim(), a, b);
         }
         @Override
         public String toString() { return tipo + ": " + cuotaMinima + "; " + costeAsignacion + "; "; }
     }
    // --- Fin Records ---

    public static Boolean tests = false;
    private static List<Area> areas;
    private static List<TipoEntrada> tiposEntrada;

    // --- NUEVO: Estructura para guardar los índices ordenados ---
    // indOrd[i][j'] -> índice original del área j
    private static List<List<Integer>> sortedAreaIndicesPerType;
    // ----------------------------------------------------------

    // Modificamos iniDatos para incluir el cálculo de la ordenación
    public static void iniDatos(String fichero) {
        // Código original para leer areas y tiposEntrada
        areas = new ArrayList<>(); // Usa ArrayList en lugar de List2 si es más estándar
        tiposEntrada = new ArrayList<>(); // Usa ArrayList
        for(String s: Files2.linesFromFile(fichero)) {
            if(s.startsWith("//"))
                continue;
            else if(s.startsWith("A"))
                areas.add(Area.create(s));
            else if (s.startsWith("T"))
                tiposEntrada.add(TipoEntrada.create(s));
        }

        // --- CÁLCULO DE LA ORDENACIÓN indOrd ---
        int numTipos = tiposEntrada.size();
        int numAreas = areas.size();
        sortedAreaIndicesPerType = new ArrayList<>(numTipos); // Inicializar lista externa

        for (int i = 0; i < numTipos; i++) {
            // Para usar 'i' dentro del lambda, necesitamos una copia final
            final int currentTypeIndex = i;

            // 1. Crear lista de índices de área originales: [0, 1, ..., numAreas-1]
            List<Integer> areaIndices = IntStream.range(0, numAreas)
                                                 .boxed()
                                                 .collect(Collectors.toList());

            // 2. Definir el comparador: compara dos índices de área (j1, j2)
            //    basándose en el coste C[i][j1] vs C[i][j2] para el tipo actual 'i'
            Comparator<Integer> costComparator = Comparator.comparingInt(
                j -> DatosFestival2.getCosteAsignacion(currentTypeIndex, j)
            );

            // 3. Ordenar la lista de índices de área usando el comparador
            areaIndices.sort(costComparator);
            

            // 4. Guardar la lista ordenada de índices de área para este tipo 'i'
            sortedAreaIndicesPerType.add(areaIndices);
            System.out.println(areaIndices);
        }
        // --------------------------------------

        if(!tests)
            toConsole(); // Mantenemos la impresión si no son tests
    }

    // --- Getters originales ---
    public static Integer getNumTiposEntrada() { return tiposEntrada.size(); }
    public static Integer getNumAreas() { return areas.size(); }

    // OJO: Este método asume que j es el índice ORIGINAL del área
    public static Integer getCosteAsignacion(Integer i, Integer j) {
        // Añade comprobaciones si el mapa podría no tener la clave
        return tiposEntrada.get(i).costeAsignacion().getOrDefault(j, Integer.MAX_VALUE); // Devuelve MAX si no existe? O lanza error?
    }

    public static Integer getAforoMaximoArea(Integer j) { return areas.get(j).aforoMaximo(); }
    public static TipoEntrada getTipoEntrada(Integer i) { return tiposEntrada.get(i); }
    public static Area getArea(Integer j) { return areas.get(j); }
    public static Integer getCuotaMinima(Integer i) { return DatosFestival2.getTipoEntrada(i).cuotaMinima(); }

    // --- NUEVO Getter para obtener indOrd[i][j'] ---
    /**
     * Devuelve el índice original del área 'j' que corresponde a la j'-ésima
     * posición cuando las áreas se ordenan por coste creciente para el tipo 'i'.
     * @param typeIndex Índice del tipo de entrada (i)
     * @param sortedIndex Índice en la secuencia ordenada (j')
     * @return Índice original del área (j), o -1 si los índices son inválidos.
     */
    public static Integer getSortedAreaIndex(int typeIndex, int sortedIndex) {
     
        List<Integer> sortedIndices = sortedAreaIndicesPerType.get(typeIndex);
        
        return sortedIndices.get(sortedIndex);
    }
    // ---------------------------------------------

    public static void toConsole() {
        String2.toConsole(areas, "Áreas");
        String2.toConsole(tiposEntrada, "Tipos de Entrada");
        // Opcional: Imprimir la tabla de índices ordenados para verificar
        if (sortedAreaIndicesPerType != null) {
             System.out.println("Índices de Área Ordenados por Coste (para cada Tipo):");
             for(int i=0; i<sortedAreaIndicesPerType.size(); i++) {
                 System.out.println("  Tipo " + i + ": " + sortedAreaIndicesPerType.get(i));
             }
        }
        String2.toConsole(String2.linea());
    }

    // ... (main method si lo tienes) ...
     public static void main(String[] args) throws IOException {
         iniDatos("resources/ejercicio3/DatosEntrada1.txt");
         // Ejemplo de uso del nuevo getter:
         // Obtener el índice del área ORIGINAL que es la SEGUNDA más barata (sortedIndex=1) para el TIPO 0 (typeIndex=0)
          int j = getSortedAreaIndex(0, 1);
          System.out.println("Para tipo 0, la segunda área más barata tiene índice original: " + j);
          System.out.println("Su coste es: " + getCosteAsignacion(0, j));
     }
}