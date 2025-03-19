package ejercicio2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio1.DatosAlmacenes;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejer2AG implements ValuesInRangeData<Integer,SolucionCursos> {
	
	 public Ejer2AG(String linea) {
	        DatosCursos.iniDatos(linea);
	    }

	@Override
	public Integer size() {
		return DatosCursos.getNumCursos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		int goal = 0;
		
		for(int i = 0;i<value.size();i++) {
			if(value.get(i)>=1) {
			goal+= DatosCursos.getRelevancia(i);
			}
		}
		
		return (double) (goal - (R1(value)*1000 +R2(value)*1000+ R3(value)*1000 +R4(value)*1000));
		
	}
	
	private Integer R1(List<Integer> value) {
		
		Map<Integer, Long> cursosporarea = IntStream.range(0, DatosCursos.getNumCursos())
		        .boxed()
		        .filter(i -> value.get(i) >= 1)  // Filtrar valores >= 1
		        .collect(Collectors.groupingBy(i -> value.get(i), Collectors.counting())); // <Area, NumCursos>
		
	int error = 0;
		for(Integer key : cursosporarea.keySet()) {
            if(cursosporarea.get(key) < 1) {
                error+=1;
        
            }
        }
		Math.pow(error, 2);
		return error;
		
	}
	
	private Integer R2(List<Integer> value) {
		
		Map<Integer, Long> cursosporarea = IntStream.range(0, DatosCursos.getNumCursos())
		        .boxed()
		        .filter(i -> value.get(i) >= 1)  // Filtrar valores >= 1
		        .collect(Collectors.groupingBy(i -> value.get(i), Collectors.counting())); 
		
		int error = 0;
		for(Integer key : cursosporarea.keySet()) {
            if(cursosporarea.get(0) <= cursosporarea.get(key) ) {
            	error+=1;
            }
		}
		return error*error;
		
	}
	
	private Integer R3(List<Integer> value) {
			
			int error =0;
			int sum =0;
			for(int i = 0; i<value.size();i++) {
				if(value.get(i)>=1) {
					sum+=DatosCursos.getDuracion(i);
					if(sum<20*DatosCursos.getNumCursos()) {
						error+=1;
					}
				}
				
			}
			return error*error;
	}
	
	private Integer R4(List<Integer> value) {
		
		int error =0;
		int sum =0;
		for(int i = 0; i<value.size();i++) {
			if(value.get(i)>=1) {
				sum+=DatosCursos.getCoste(i);
			}
			if(sum>DatosCursos.getPresupuestoTotal()) {
				error = sum-DatosCursos.getPresupuestoTotal();
			}
		}
		return error*error;
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
	
	

}
