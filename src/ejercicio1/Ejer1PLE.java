package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ejercicio1.DatosAlmacenes.Almacen;
import ejercicio1.DatosAlmacenes.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejer1PLE {
	
	public static void sol_ejer1() throws IOException {
		
		DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada1.txt");
		
		AuxGrammar.generate(DatosAlmacenes.class, "resources/ejercicio1/modelo2.lsi", "resources/ejercicio1/modelo1.lp");
		GurobiSolution solution = GurobiLp.gurobi("resources/ejercicio1/modelo1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	
	}
	
	public static void main(String[] args) throws IOException {
		sol_ejer1();
	}
	

}
