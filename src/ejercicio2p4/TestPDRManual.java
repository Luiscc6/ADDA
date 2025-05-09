package ejercicio2p4;


public class TestPDRManual {

	public static void main(String[] args) {
		
			DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada1.txt");
			
			System.out.println("Solucion obtenida: " + CursosPD.search());
		
	}
	
}
