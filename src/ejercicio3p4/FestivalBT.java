package ejercicio3p4;

import java.io.IOException;

public class FestivalBT {

	private static Double mejorValor;
	private static FestivalState estado;
	private static SolucionFestival solucion;
	
	public static void search() {
		solucion = null;
		mejorValor = Double.MAX_VALUE; // Estamos minimizando
		estado = FestivalState.initial();
		bt_search();
	}

	private static void bt_search() {
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido < mejorValor) {  // Estamos minimizando
				mejorValor = valorObtenido;
				solucion = estado.getSolucion();
			}
		} else if(!estado.esTerminal()){
			for (Integer a: estado.alternativas()) {
//				if (estado.cota(a) <= mejorValor) {  // Estamos minimizando
				if (estado.cota(a) < mejorValor) {  // Estamos minimizando
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}

	public static SolucionFestival getSolucion() {
		return solucion;
	}
	
	public static void main(String[] args) throws IOException {
		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada1.txt");
		//DatosFestival.ordenaDatos();
		search();
		System.out.println(FestivalBT.getSolucion()+ "\n");

	}

}
