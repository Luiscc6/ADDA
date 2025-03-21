package ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejer3AG implements ValuesInRangeData<Integer, SolucionFestival> {

    public Ejer3AG(String string) {
        DatosFestival.iniDatos(string);
    }

    @Override
    public Integer size() {
        return DatosFestival.getNumTiposEntrada() * DatosFestival.getNumAreas();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    @Override
    public Double fitnessFunction(List<Integer> ls) {
    	Integer numAreas = DatosFestival.getNumAreas();
    	Integer numTiposEntrada = DatosFestival.getNumTiposEntrada();
    	Integer value = 0;
    	Integer restriccionCuota = 0;
    	Integer restriccionAforo = 0;
    	Map<Integer, Integer> totalEntradasPorTipo = new HashMap<>();
    	Map<Integer, Integer> totalEntradasPorArea = new HashMap<>();
    	for (int i = 0; i < ls.size(); i++) {
    	Integer area_i = i % numAreas; // Obtener área correspondiente
    	Integer entrada_i = i / numAreas; // Obtener tipo de entrada correspondiente
    	totalEntradasPorTipo.put(entrada_i, totalEntradasPorTipo.getOrDefault(entrada_i, 0) + ls.get(i));
    	totalEntradasPorArea.put(area_i, totalEntradasPorArea.getOrDefault(area_i, 0) + ls.get(i));
    	Integer costoAsignacion = DatosFestival.getCosteAsignacion(entrada_i, area_i);
    	value += ls.get(i) * costoAsignacion;
    	}
    	for (int i = 0; i < numTiposEntrada; i++) { //Restricción cuota mínima
    	int total = totalEntradasPorTipo.getOrDefault(i, 0);
    	if (total < DatosFestival.getCuotaMinima(i)) {
    	restriccionCuota += (DatosFestival.getCuotaMinima(i) - total);
    	}
    	}
    	for (int j = 0; j < numAreas; j++) { //Restriccón aforo máximo
    	int total = totalEntradasPorArea.getOrDefault(j, 0);
    	if (total > DatosFestival.getAforoMaximoArea(j)) {
    	restriccionAforo += (total - DatosFestival.getAforoMaximoArea(j));
    	}
    	}
    	return (double) -value - 1000 * (restriccionAforo * restriccionAforo + restriccionCuota * restriccionCuota);
    	}

    @Override
    public SolucionFestival solucion(List<Integer> value) {
        return SolucionFestival.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return DatosFestival.getAforoMaximoArea(i % DatosFestival.getNumAreas());
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }
}
