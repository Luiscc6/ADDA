package ejercicio11;

import java.util.List;


import _datos.DatosCafe;
import _soluciones.SolucionCafe;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;


public class InRangeCafeAG implements ValuesInRangeData<Integer,SolucionCafe> {
	
	public InRangeCafeAG(String linea) {
		DatosCafe.iniDatos(linea);
	}

	@Override
	public Integer size() {
		
		return DatosCafe.getNumVariedades();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double beneficio = 0.;
		for(int i = 0; i<value.size();i++) {
			beneficio += DatosCafe.getBeneficio(i)*value.get(i);
		}
		return beneficio-R1(value)*1000;
	}
	private Double R1 (List<Integer> value) {
		double error = 0.;
		Integer can = 0;
		for (int i =0; i< value.size();i++) {
			Integer cantidad = DatosCafe.getCantidad(i);
			Double por1 = DatosCafe.getPorcentaje(i, value.get(i));
			
			if(value.get(i)*por1<cantidad) {
				 error = value.get(i)*por1 -cantidad;
			}
			
		}
		return Math.pow(error, 2);
	}

	@Override
	public SolucionCafe solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return SolucionCafe.create(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosCafe.getVariedades().get(i).getMax() +1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
