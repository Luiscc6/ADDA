package _datos;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosCursos {
	

	
	/* Ejemplo de datos de entrada
	  	Max_Centros = 1
		{1,2,3,4}:10.0:0
		{1,4}:3.0:0
		{5}:1.5:1
		{5}:5.0:0
	 */

	
	
	/*Records necesarios para el parseo de los datos. Se ha definido un record Curso para cada curso, y una variable aparte maxCentros que indica el nº
	 * maximo de centros permitidos*/
	
	public static record Curso(String codigo, List<Integer> tematicas, Double precio, Integer centro) {
		
		public static Curso create(String s, String c) {
			String[] v = s.split(":");
			return new Curso(c, List2.parse(v[0].replace("{", "").replace("}", "").split(","), Integer::valueOf) , Double.valueOf(v[1]) , Integer.valueOf(v[2]));
		}
	
		public String getCodigo() {
			return codigo;
		}
		public List<Integer> getTematicas() {
			return tematicas;
		}
		public Double getPrecio() {
			return precio;
		}	
		public Integer getCentro() {
			return centro;
		}	
		
		public String toString() {		
			String res = codigo+" : "+tematicas+" : "+precio+" : "+centro;
			return res;
		}	
	}
	
	
	
	/*Funcion para mostras los datos en pantalla para realizar el test de la lectura de ficheros*/
	
	public static List<Curso> cursos = List2.empty(); 
	public static Integer maxCentros;
	
	public static void iniDatos(String fichero) {
		
		int i = 0;
		List<String> aux;
		aux = Files2.streamFromFile(fichero).filter(x->x.contains("{")).toList();
		for(String s:aux) {
			cursos.add(Curso.create(s, "S"+i));
			i++;
		}
		maxCentros = Integer.valueOf( Files2.streamFromFile(fichero).filter(x->x.contains("Max_Centros")).map( x->x.replace("Max_Centros = ", "") ).toList().get(0) ) ;
		
		List<String> s1 = cursos.stream().map(Curso::toString).toList();
		
		System.out.println("maxCentros = "+maxCentros);
		for(String s: s1) {
			System.out.println(s);
		}
				
	}
	
	
	
	/*Metodos necesarios para realizar el problema propuesto*/
	
	public static List<Curso> getCursos(){
		return cursos;
	}
	
	//n
	public static Integer getNumCursos() {
		return cursos.size();
	}
	//m
	public static Integer getNumTematicas() {
		return  (int) cursos.stream().map(x->x.tematicas()).flatMap(t->t.stream()).distinct().count();
	}
	//nc
	public static Integer getNumCentros() {
		return (int) cursos.stream().map(x->x.centro()).distinct().count();
	}
	//maxCentros
	public static Integer getMaxCentros() {
		return maxCentros;
	}
	
	//t(ij)
	public static Boolean existeTematicaJEnCursoI(Integer i, Integer j) {
		return cursos.get(i).tematicas().contains(j);
	}
	//p(i)
	public static Double getPrecio(Integer i) {
		return cursos.get(i).getPrecio();
	}
	//c(ik)
	public static Boolean existeCentroKEnCursoI(Integer i, Integer k) {
		return cursos.get(i).getCentro().equals(k);
	}
		

	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");		
	}
	

}
