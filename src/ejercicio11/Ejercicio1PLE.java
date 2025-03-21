package ejercicio11;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosCafe;
import _datos.DatosCafe.Tipo;
import _datos.DatosCafe.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;


public class Ejercicio1PLE {
	
	/*1. Un envasador de café mezcla distintas cantidades de n tipos de café para preparar m variedades. Cada kilogramo de
	 *  café de cada una de las variedades contiene un porcentaje de cada tipo de café. El envasador dispone de una cantidad 
	 *  (en kilos) de cada tipo de café, y obtiene un beneficio distinto por cada kilogramo de cada una de las variedades que 
	 *  produce, ¿cuántos kilogramos de cada variedad de café deben producirse para maximizar los beneficios?
	 */
	
	public static void ejercicio1_model(String fichero) throws IOException {
		
		
	}
	
	public static void main(String[] args) throws IOException {
		ejercicio1_model("ficheros/Ejercicio1DatosEntrada1.txt");
		ejercicio1_model("ficheros/Ejercicio1DatosEntrada2.txt");
		ejercicio1_model("ficheros/Ejercicio1DatosEntrada3.txt");
	}
	
	
	

}
