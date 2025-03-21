package _datos;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosCafe {
	

	
	/* Ejemplo de datos de entrada
	  	// TIPOS
		C01: kgdisponibles=5;
		C02: kgdisponibles=4;
		...
		
		// VARIEDADES
		P01 -> beneficio=20; comp=(C01:0.5),(C02:0.4),(C03:0.1);
		P02 -> beneficio=10; comp=(C04:0.2),(C05:0.8);
		...
	 */

	
	
	/*Records necesarios para el parseo de los datos. Se ha definido un record Tipo para cada tipo de cafe, y otro record Variedad para cada variedad 
	 * que se puede formar. Además, en una clase auxiliar se ha creado otro record Composicion para la composicion de las cariedades*/
	
	public static record Tipo(String codigo, Integer cantidad) {
		
		public static Tipo create(String s) {
			String[] v = s.split(": kgdisponibles=");
			return new Tipo(v[0], Integer.valueOf(v[1].replace(";", "")));
		}
		
		public Integer getCantidad() {
			return cantidad;
		}	
		public String getCodigo() {
			return codigo;
		}	
		
		public String toString() {		
			String res = codigo+": "+cantidad+" kgs";
			return res;
		}	
	}
	
	public static record Variedad(String codigo, Integer beneficio, List<Composicion> composiciones) {
		
		public static Variedad create(String s) {
			String[] v = s.split("; comp=");
			String[] v1 = v[0].split(" -> beneficio=");
			
			return new Variedad( v1[0], Integer.valueOf(v1[1]), List2.parse(v[1].replace(";", "").split(","), Composicion::create) );
		}
		
		public Integer getBeneficio() {
			return beneficio;
		}	
		
		public String getCodigo() {
			return codigo;
		}	
		
		public List<Composicion> getComposicion() {
			List<String> aux = composiciones.stream().map(x->x.getCafe()).toList();
			Comparator<Composicion> cmp = (Composicion c1, Composicion c2) -> c1.cafe().compareTo(c2.cafe());
			
			for (Tipo t: tipos) {
				if( !aux.contains(t.codigo()) ) composiciones.add( Composicion.create("("+t.codigo()+":0.0)") );
			}
			return composiciones.stream().sorted(cmp).toList();
		}	
		
		public Integer getMax() {
			List<Double> ls = List2.empty();
			for (Composicion c:composiciones) {
				ls.add(c.getPeso());
			}
			return (int) (ls.stream().collect(Collectors.summarizingDouble(x->x)).getSum() * getBeneficio());
		}
		
		public String toString() {		
			String res = codigo +": ben= "+ beneficio+ ";  " +"comp= "+ getComposicion();
			return res;
		}
	}
	
	/*Record auxiliar para obtener la composicion de cada variedad de cafe*/
	
	public record Composicion(String cafe, Double porcentaje) {
		
		/*  Ejemplo de datos de entrada
		 * (C04:0.2) */
		
		
		public static Composicion create(String s) {
			String[] v = s.replace("(", "").replace(")", "").split(":");
			return new Composicion(v[0], Double.valueOf(v[1]));
		}
		
		public Double getPorcentaje() {
			return porcentaje;
		}	
		
		public String getCafe() {
			return cafe;
		}	
		
		public Double getPeso() {
			Double res = null;
			for(Tipo t: tipos) {
				if(t.codigo().equals(cafe)) res = t.getCantidad()*getPorcentaje();
			}
			return res;
		}
		
		public String toString() {		
			String res = "("+cafe+":"+porcentaje+")";
			return res;
		}	
	}
	
	
	
	/*Funcion para mostras los datos en pantalla para realizar el test de la lectura de ficheros*/
	
	public static List<Tipo> tipos; 
	public static List<Variedad> variedades; 
	
	public static void iniDatos(String fichero) {
		tipos = Files2.streamFromFile(fichero).filter(x->x.contains("kgdisponibles")).map(Tipo::create).toList();
		
		variedades = Files2.streamFromFile(fichero).filter(x->x.contains("beneficio")).map(Variedad::create).toList();

		String s1 = tipos.stream().map(Tipo::toString).collect(Collectors.joining("\n"));
				String2.toConsole("%s%s", s1, String2.linea());
		
		String s2 = variedades.stream().map(Variedad::toString).collect(Collectors.joining("\n"));
				String2.toConsole("%s%s", s2, String2.linea());
				
	}
	
	
	
	/*Metodos necesarios para realizar el problema propuesto*/
	
	public static List<Tipo> getTipos(){
		return tipos;
	}
	public static List<Variedad> getVariedades(){
		return variedades;
	}
	
	//n
	public static Integer getNumTipos() {
		return tipos.size();
	}
	//m
	public static Integer getNumVariedades() {
		return variedades.size();
	}
	//c(j)
	public static Integer getCantidad(Integer j) {
		return tipos.get(j).cantidad();
	}
	//b(i)
	public static Integer getBeneficio(Integer i) {
		return variedades.get(i).beneficio();
	}
	//p(ij)
	public static Double getPorcentaje(Integer i, Integer j) {
		return variedades.get(i).composiciones().get(j).porcentaje();
	}
	

	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");		
	}
	

}
