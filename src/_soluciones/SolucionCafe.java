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
		//TODO
	}
	
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
