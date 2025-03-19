package ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio1.DatosAlmacenes;
import ejercicio1.SolucionAlmacen;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejer3AG implements ValuesInRangeData<Integer, SolucionFestival>  {

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
		
		Integer aforo = 0;
		Map<Integer, List<Integer>> entradaporarea = IntStream.range(0, DatosFestival.getNumAreas())
                .boxed()
                .filter(i -> value.get(i) < DatosFestival.getNumTiposEntrada())
                .collect(Collectors.groupingBy(value::get));
		
		for(Integer i = 0; i<=DatosFestival.getNumTiposEntrada();i++) {
			for(Integer j = 0 ; j*i+DatosFestival.getNumAreas()<=entradaporarea.keySet().size();j++ ) {
					
			
		}
		if(DatosFestival.getAforoMaximoArea(a)) {
			
			
		}
	}

	@Override
	public SolucionFestival solucion(List<Integer> value) {
		
		return SolucionFestival.create(value);
	}

	@Override
	public Integer max(Integer i) {
		
		return DatosFestival.getAforoMaximoArea(i);
	}

	@Override
	public Integer min(Integer i) {
		
		return DatosFestival.getCuotaMinima(i);
	}
	


}
