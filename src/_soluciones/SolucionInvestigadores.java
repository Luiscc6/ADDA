package _soluciones;

import java.util.List;
import _datos.DatosInvestigadores;
import _datos.DatosInvestigadores.Investigador;
import us.lsi.common.List2;

public class SolucionInvestigadores {
	
	public static SolucionInvestigadores create(List<Integer> value) {
		return new SolucionInvestigadores(value);
	}
		
	private Integer calidad;
	private List<Investigador> investigadores;
	private List<List<Integer>> dias;
	
	
	private SolucionInvestigadores() { 
		calidad = 0;
		investigadores = List2.empty(); 
		dias = List2.empty();
	}
	
	
	public static SolucionInvestigadores empty() {
		return new SolucionInvestigadores();
	}
	
	
	private SolucionInvestigadores(List<Integer> ls) {
		Integer numInv = DatosInvestigadores.getNumInvestigadores();
		Integer numTrab = DatosInvestigadores.getNumTrabajos();
		Integer numEsp = DatosInvestigadores.getNumEspecialidades();
		calidad = 0;
		investigadores =List2.empty();
		investigadores.addAll(DatosInvestigadores.getInvestigadores());
		dias = List2.empty();
		
		for (int i=0; i<numInv; i++) {
			dias.add(List2.empty());
		}
		
		for (int j=0; j<numTrab; j++) { 
			Integer jj = j*numInv;
			List<Integer> trab = ls.subList(jj, jj+numInv);
			for (int i=0; i<numInv; i++) { 
				dias.get(i).add(trab.get(i));
			}
			
			Boolean realiza=true;
			for (int k=0; k<numEsp; k++) {
				Integer suma=0;
				for (int i=0; i<numInv; i++) {
					suma += trab.get(i)*DatosInvestigadores.trabajadorIEspecialidadK(i, k);
				}
				if (suma < DatosInvestigadores.diasNecesarios(j, k)) {
					realiza = false;
					k = numEsp;
				}
			}
				
			//  Si se realiza el trabajo, se suma su calidad
			if (realiza) calidad += DatosInvestigadores.getCalidad(j);
		}
	}
		
	public String toString() {
		
		//Mostrar solucion en pantalla		
		String s = "Suma de las calidades de los trabajos realizados:\n";
		
		for(int i=0; i<investigadores.size(); i++) {
			s = s + investigadores.get(i).getId() +": "+ dias.get(i) +"\n";
		}

		return String.format(s + "Reparto de horas: " + calidad);
	}
			

		
		
		
		
		
		
}
