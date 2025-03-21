package _datos;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosInvestigadores {


	
	/* Ejemplo de datos de entrada
	  	// INVESTIGADORES
		INV1: capacidad=6; especialidad=0;
		INV2: capacidad=3; especialidad=1;
		...
		
		// TRABAJOS
		T01 -> calidad=5; reparto=(0:6),(1:0),(2:0);
		T02 -> calidad=10; reparto=(0:0),(1:3),(2:8);
	 */

	
	
	/*Records necesarios para el parseo de los datos. Se ha definido un record Investigador para cada investigador, y otro record Trabajo para cada trabajo 
	 * que los investigadores deben realizar.*/
	
	public static record Investigador(String id, Integer capacidad, Integer especialidad) {
		
		public static Investigador create(String s) {
			String[] v = s.split(" ");
			return new Investigador(v[0].replace(":", ""), 
					Integer.valueOf(v[1].replace("capacidad=", "").replace(";", "")), 
					Integer.valueOf(v[2].replace("especialidad=", "").replace(";", "")));
		}
		
		public Integer getCapacidad() {
			return  capacidad;
		}	
		public String getId() {
			return id;
		}	
		public Integer getEspecialidad() {
			return especialidad;
		}	
		
		public String toString() {		
			String res = id+": "+ capacidad+"; "+especialidad;
			return res;
		}	
	}
	
	public static record Trabajo(String id, Integer calidad, List<Integer> dias) {
		
		public static Trabajo create(String s) {
			String[] v = s.split("; reparto=");
			String[] v1 = v[0].split(" -> calidad=");
			
			return new Trabajo( v1[0], 
					Integer.valueOf(v1[1]), 
					List2.parse(v[1].replace("(", "").replace(")", "").replace(";", "").split(","), x->Integer.valueOf(x.split(":")[1])) );
		}
		
		public Integer getCalidad() {
			return calidad;
		}	
		public String getId() {
			return id;
		}	
		public List<Integer> getDias() {
			return dias;
		}		
		
		public String toString() {		
			String res = id +": calidad= "+ calidad+ ";  " +"reparto= "+ dias;
			return res;
		}
	}
	
	
	
	/*Funcion para mostras los datos en pantalla para realizar el test de la lectura de ficheros*/
	
	public static List<Investigador> investigadores; 
	public static List<Trabajo> trabajos; 
	
	public static void iniDatos(String fichero) {
		investigadores = Files2.streamFromFile(fichero).filter(x->x.contains("capacidad")).map(Investigador::create).toList();
		
		trabajos = Files2.streamFromFile(fichero).filter(x->x.contains("reparto")).map(Trabajo::create).toList();

		String s1 = investigadores.stream().map(Investigador::toString).collect(Collectors.joining("\n"));
				String2.toConsole("%s%s", s1, String2.linea());
		
		String s2 = trabajos.stream().map(Trabajo::toString).collect(Collectors.joining("\n"));
				String2.toConsole("%s%s", s2, String2.linea());
				
	}
	
	
	
	/*Metodos necesarios para realizar el problema propuesto*/
	
	public static List<Investigador> getInvestigadores(){
		return investigadores;
	}
	public static List<Trabajo> getTrabajos(){
		return trabajos;
	}
	
	//n
	public static Integer getNumInvestigadores() {
		return investigadores.size();
	}

	//e
	public static Integer getNumEspecialidades() {
		return trabajos.get(0).getDias().size();
	}
	
	//m
	public static Integer getNumTrabajos() {
		return trabajos.size();
	}
	
	//e(ik)
	public static Integer trabajadorIEspecialidadK (Integer i, Integer k) {
		return investigadores.get(i).getEspecialidad().equals(k) ? 1 : 0;
	}
	
	//dd(i) días disponibles del trabajador i (capacidad)
	public static Integer diasDisponibles(Integer i) {
		return investigadores.get(i).capacidad();
	}
	
	//dd(jk) días necesarios para el trabajo j de investigador con especialidad k
	public static Integer diasNecesarios(Integer j, Integer k) {
		return trabajos.get(j).dias().get(k);
	}
	
	//c(j)
	public static Integer getCalidad (Integer j) {
		return trabajos.get(j).getCalidad();
	}
	
	//capacidad maxima de todos los investigadores (necesario para el AG de Rango)
	public static Integer getMM(Integer j) {
		return trabajos.get(j).getDias().stream().mapToInt(e -> e).sum();
	}
	

	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");		
	}
	

	

}
