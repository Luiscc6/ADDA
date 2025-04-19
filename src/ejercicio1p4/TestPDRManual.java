package ejercicio1p4;


public class TestPDRManual {

	public static void main(String[] args) {
		
			DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada1.txt");
			
			System.out.println("Solucion obtenida: " + AlmacenPD.search());
		
	}
	
}
