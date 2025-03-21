package ejercicio22;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosCursos;
import _datos.DatosCursos.Curso;
import us.lsi.common.List2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;


public class Ejercicio2PLE {
	
	/*2. Existe una oferta de cursos de verano, en cada uno de los cuales se tratan diversas temáticas, algunas de ellas comunes a varios cursos.
	   Además, de cada curso se conoce su precio de inscripción, y el centro donde se imparte. Se desea conocer la lista de cursos en los que 
	   matricularse, teniendo en cuenta que: 
	   (a) entre todos los cursos seleccionados se deben cubrir todas las temáticas, 
	   (b) no se pueden elegir cursos de más de un número determinado de centros diferentes (maxCentros)
	   (c) se debe minimizar el precio total de inscripción.
	 */
	
	
	public static void ejercicio2_model(String fichero) throws IOException {
	
		
		
	public static void main(String[] args) throws IOException {
		ejercicio2_model("ficheros/Ejercicio2DatosEntrada1.txt");
		ejercicio2_model("ficheros/Ejercicio2DatosEntrada2.txt");
		ejercicio2_model("ficheros/Ejercicio2DatosEntrada3.txt");
	}
	
	
	

}
