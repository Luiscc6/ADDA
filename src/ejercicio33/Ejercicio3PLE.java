package ejercicio33;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosInvestigadores;
import _datos.DatosInvestigadores.Investigador;
import _datos.DatosInvestigadores.Trabajo;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	
	/*3. Existe un grupo de n investigadores para realizar un conjunto de m trabajos durante este curso. De cada investigador se conoce 
	 * su especialidad (cada especialidad se identifica por un entero), y la cantidad de días disponibles que tiene para avanzar en los
	 *  trabajos. De cada trabajo se conoce cuántos días de cada especialidad de investigador son necesarios para llevarlo a cabo (por 
	 *  ejemplo 3 días de investigador de especialidad 3 y 2 de especialidad 5), y su calidad (entero en [5,10]). Teniendo en cuenta los
	 *  días disponibles de los investigadores, puede que no sea posible realizar todos los trabajos, por lo que hay que decidir cuáles
	 *  llevar a cabo. Se desea conocer cuántos días dedicará cada investigador a cada trabajo de forma que se maximice la suma total
	 *  de las calidades de los trabajos llevados a cabo.*/

	

	
	public static void ejercicio3_model(String fichero) throws IOException {
		
	}
	
	public static void main(String[] args) throws IOException {
		ejercicio3_model("ficheros/Ejercicio3DatosEntrada1.txt");
		ejercicio3_model("ficheros/Ejercicio3DatosEntrada2.txt");
		ejercicio3_model("ficheros/Ejercicio3DatosEntrada3.txt");
	}
	
	
	
}
