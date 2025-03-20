package ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio1.DatosAlmacenes;
import ejercicio1.SolucionAlmacen;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class Ejer3AG implements ValuesInRangeData<Integer, SolucionFestival>  {

	public Ejer3AG(String string) {
		DatosFestival.iniDatos(string);
	}

	@Override
	public Integer size() {
		// TODO Auto-generated m
		return DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
	}

	@Override
	public ChromosomeType type() {
		
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Map<Integer,Integer >solucion = new HashMap<>();
		int goal = 0;
    	for(Integer i=0;i<=DatosFestival.getNumTiposEntrada()*DatosFestival.getNumAreas();i++ ) {
    		Integer k = i/DatosFestival.getNumAreas();
    		Integer v = i%DatosFestival.getNumAreas();
    		solucion.put(k, v);
    		for (int area:solucion.keySet()) {
    			goal += DatosFestival.getCosteAsignacion(solucion.get(area), area);
    			   			
    		}
    	}
    	return (double) (-goal - (R1(value) * 1000 + R2(value) * 1000));
		
	}
		
	private Double R1(List<Integer> value) {
		Map<Integer, Integer> areaCountMap = new HashMap<>();
		int ci = 0;
		int error = 0;
		for (int i = 0; i < value.size(); i++) {
	        Integer area = i / DatosFestival.getNumAreas();  // Calcula el área

	        // Incrementa el contador para cada área
	        areaCountMap.put(area, areaCountMap.getOrDefault(area, 0) + 1);
	    }
		for(int m : areaCountMap.keySet()) {
			ci = areaCountMap.get(m);
			if(ci>= DatosFestival.getAforoMaximoArea(m)) {
				error += ci-DatosFestival.getAforoMaximoArea(m);
			}
			
		}
		return  error > 0 ? Math.pow(error, 2) : 0.0;

	}
	
	private Double R2(List<Integer> value) {
		Map<Integer, Integer> areaCountMap = new HashMap<>();
		int ci = 0;
		int error = 0;
		for (int i = 0; i < value.size(); i++) {
	        Integer area = i / DatosFestival.getNumAreas();  // Calcula el área

	        // Incrementa el contador para cada área
	        areaCountMap.put(area, areaCountMap.getOrDefault(area, 0) + 1);
	    }
		for(int m : areaCountMap.keySet()) {
			ci = areaCountMap.get(m);
			if(DatosFestival.getCuotaMinima(m)> ci) {
				error += DatosFestival.getCuotaMinima(m)-ci;
			}
			
		}
		return  error > 0 ? Math.pow(error, 2) : 0.0;
	}

	@Override
	public SolucionFestival solucion(List<Integer> value) {
		
		return SolucionFestival.create(value);
	}

	@Override
	public Integer max(Integer i) {
		
		return DatosFestival.getAforoMaximoArea(i%DatosFestival.getNumAreas());
	}

	@Override
	public Integer min(Integer i) {
		
		return 0;
	}
	


}
