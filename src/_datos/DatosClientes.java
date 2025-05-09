package _datos;

import java.util.ArrayList; 

import java.util.List;
import org.jgrapht.Graph; 
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class DatosClientes { 
	
	
	
	/*Records necesarios para representar los datos del problema. Se ha creado un record Conexio y otro Cliente.*/

	public record Cliente(int id, Double beneficio) {
		public static Cliente of(int id, Double beneficio) {
			return new Cliente(id, beneficio);
		}
		public static Cliente ofFormat(String[] formato) {
			Integer id= Integer.valueOf(formato[0].trim());
			Double benef = Double.valueOf(formato[1].trim());
			return of(id, benef);
		}
		public String toString() {
			return String.valueOf(this.id());
		}
	}
	
	public record Conexion(int id, Double distancia) {
		public static int cont;
		
		public static Conexion of(Double distancia) {
			Integer id = cont;
			cont++;
			return new Conexion(id, distancia);
		}
		public static Conexion ofFormat(String[] formato) {
			Double dist = Double.valueOf(formato[2].trim()); 
			return of(dist);
		}
		public String toString() {
			return "id: " + this.id() + "; distancia: " + this.distancia();
		}
	}
	
	
	
	/*Funcion para mostras los datos en pantalla para realizar el test de la lectura de ficheros*/

	public static Graph<Cliente, Conexion> grafo;
	
	public static void iniDatos(String fichero) {
		grafo = GraphsReader.newGraph(fichero, Cliente::ofFormat,
		Conexion::ofFormat, Graphs2::simpleWeightedGraph);
		
		//Mostrar datos en pantalla
		System.out.println("Número de vértices: "
				+ grafo.vertexSet().size() + "\n\tVértices: " + grafo.vertexSet()
				+ "\nNúmero de aristas: " + grafo.edgeSet().size() +  "\n\tAristas: " + grafo.edgeSet());
	}
	
	
	
	/*Metodos necesarios para realizar el problema propuesto*/

	//n
	public static Integer getNumVertices() {
		return grafo.vertexSet().size();
	}

	//a
	public static Cliente getCliente(Integer i) {
		Cliente c = null;
		List<Cliente> vertices = new ArrayList<>(grafo.vertexSet());
		for (int k = 0; k < vertices.size(); k++) {
			if (vertices.get(k).id() == i) c = vertices.get(k);
		}
		return c;
	}
	
	/*E (metodo que sirve para comprobar si existe una arista, realmente no se necesita crear un metodo para obtener una arista, 
	 para eso, en su lugar usamos el metodo getPeso (W(ij)) que obtiene el peso de la arista, es decir, la distancia en este caso*/
	public static Boolean existeArista(Integer i, Integer j) {
		Cliente c1 = getCliente(i);
		Cliente c2 = getCliente(j);
		return grafo.containsEdge(c1, c2);
	}
	
	//w(ij)
	public static Double getPeso(Integer i, Integer j) {
		Cliente c1 = getCliente(i);
		Cliente c2 = getCliente(j);
		return grafo.getEdge(c1, c2).distancia();
	}
	
	//b(i)
	public static Double getBeneficio(Integer i) {
		Cliente c = getCliente(i);
		return c.beneficio();
	}
	
	 
	
	// Test de la lectura del fichero

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio4DatosEntrada2.txt");
	 }
	
	
	
	
}