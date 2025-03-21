package _soluciones;

import java.util.List;

import _datos.DatosCafe;
import _datos.DatosCafe.Variedad;
import us.lsi.common.List2;

public class SolucionCafe {
	
	
	public static SolucionCafe create(List<Integer> ls) {
		return new SolucionCafe(ls);
	}

	private Double beneficioTotal;
	private List<Variedad> soluciones;
	private List<Integer> solucion;
	
	private SolucionCafe() {
		beneficioTotal = 0.;
		solucion = List2.of();
		soluciones = List2.of();
	}

	private SolucionCafe(List<Integer> ls) {
		beneficioTotal = 0.;
		solucion = List2.of();
		soluciones = List2.of();
		for(int i=0; i<ls.size(); i++) { if(ls.get(i)>0) {
		                    Integer p = ls.get(i);
		Integer b = DatosCafe.getVariedades().get(i).getBeneficio();
		soluciones.add(DatosCafe.getVariedades().get(i)); solucion.add(p);
		                    beneficioTotal += p*b;
		               }
		}}
		
		
		
	
	
	public static SolucionCafe empty() {
		return new SolucionCafe();
	}

	public String toString() {
		
		//Mostrar solucion en pantalla		
		String s = "Variedades de café seleccionadas:\n";
		
		for(int p=0; p<soluciones.size(); p++) {
			s = s + soluciones.get(p).getCodigo() +": "+ solucion.get(p) +" kgs\n";
		}
		
		return String.format(s +
				"Beneficio: "+ beneficioTotal +" €\n");
			
	}
	
	
	
	
	

}
