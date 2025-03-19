package ejercicio2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio2.DatosCursos;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejer2AG implements ValuesInRangeData<Integer, SolucionCursos> {

    public Ejer2AG(String linea) {
        DatosCursos.iniDatos(linea);
    }

    @Override
    public Integer size() {
        return DatosCursos.getNumCursos();
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        int goal = 0;
        for (int i = 0; i < value.size(); i++) {
            if (value.get(i) >= 1) {
                goal += DatosCursos.getRelevancia(i);
            }
        }
        return (double) (goal - (R1(value) * 1000 + R2(value) * 1000 + R3(value) * 1000 + R4(value) * 1000));
    }

    private Integer R1(List<Integer> value) {
        Map<Integer, Integer> cursosporarea = IntStream.range(0, DatosCursos.getNumCursos())
            .boxed()
            .filter(i -> value.get(i) >= 1) // Filtrar cursos seleccionados (valor >= 1)
            .collect(Collectors.groupingBy(
                i -> DatosCursos.getArea(i), // Agrupar por área
                Collectors.summingInt(i -> 1) // Contar cuántos cursos por área
            ));

        int error = 0;
        
        for (int area = 0; area < DatosCursos.getNumAreas(); area++) {
            if (!cursosporarea.containsKey(area) || cursosporarea.get(area) < 1) {
                error += 1;
            }
        }
        return (int) Math.pow(error, 2);
    }

    private Double R2(List<Integer> value) {
        // Agrupar cursos por área (si están seleccionados)
        Map<Integer, Integer> cursosporarea = IntStream.range(0, DatosCursos.getNumCursos())
            .boxed()
            .filter(i -> value.get(i) >= 1)  // Filtrar cursos seleccionados (valor >= 1)
            .collect(Collectors.groupingBy(
                i -> DatosCursos.getArea(i), // Agrupar por área
                Collectors.summingInt(i -> 1) // Contar cuántos cursos por área
            ));

        // Inicializamos el error, y los contadores para los cursos de tecnología (área 0) y otros
        double error = 0.;
        int tecno = cursosporarea.getOrDefault(0, 0); // Cursos seleccionados en tecnología (área 0)
        int otros = 0;

        // Iteramos sobre las claves del mapa (las áreas)
        for (Integer area : cursosporarea.keySet()) {
            if (area != 0) { // Excluimos el área 0 (tecnología)
                otros += cursosporarea.get(area); // Sumar los cursos en otras áreas
            }
        }

        // Comparar el número de cursos seleccionados en tecnología con los seleccionados en otras áreas
        if (tecno >= otros) {
            return 0.;
        } else {
            // Si el número de cursos seleccionados en otras áreas es mayor que en tecnología, calculamos el error
            error = Math.pow(otros - tecno, 2);
            return error;
        }
    }


    private Integer R3(List<Integer> value) {
        int duracion = 0;
        int cursos = 0;

        for (int i = 0; i < value.size(); i++) {
            if (value.get(i) >= 1) {
                duracion += DatosCursos.getDuracion(i);
                cursos++;
            }
        }

        if (duracion >= 20 * cursos) {
            return 0;
        } else {
            return (int) Math.pow(20 * cursos - duracion, 2);
        }
    }

    private Integer R4(List<Integer> value) {
        int coste = 0;

        for (int i = 0; i < value.size(); i++) {
            if (value.get(i) >= 1) {
                coste += DatosCursos.getCoste(i);
            }
        }

        if (coste <= DatosCursos.getPresupuestoTotal()) {
            return 0;
        } else {
            return (int) Math.pow(DatosCursos.getPresupuestoTotal() - coste, 2);
        }
    }

    @Override
    public SolucionCursos solucion(List<Integer> value) {
        return SolucionCursos.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return DatosCursos.getNumCursos();
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Binary;
    }
}
