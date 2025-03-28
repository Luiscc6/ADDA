package ejercicio3p4;

import java.io.IOException;
import java.util.Locale;

import ejercicio1.DatosAlmacenes;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejer3PLE {
	
public static void sol_ejer1() throws IOException {
		
		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada1.txt");
		
		AuxGrammar.generate(DatosFestival.class, "resources/ejercicio3/ejer3.lsi", "resources/ejercicio3/modelo1.lp");
		GurobiSolution solution = GurobiLp.gurobi("resources/ejercicio3/modelo1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	
	}
	
	public static void main(String[] args) throws IOException {
		sol_ejer1();
	}

}
