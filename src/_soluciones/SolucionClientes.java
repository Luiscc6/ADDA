package _soluciones;

import java.util.List;
import _datos.DatosClientes;
import _datos.DatosClientes.Cliente;
import us.lsi.common.List2;

public class SolucionClientes {
	
	public static SolucionClientes create(List<Integer> ls) {
		return new SolucionClientes(ls);
	}
	
	private Double penalizacion, beneficio;
	private List<Cliente> clientes;	

	
	private SolucionClientes() {
		penalizacion = 0.;
		beneficio = 0.;
		clientes = List2.empty();
		clientes.add(DatosClientes.getCliente(0));
	}
	
	
	private SolucionClientes(List<Integer> ls) {
//TODO
	}


	public String toString() {
		List<Integer> aux = clientes.stream().map(c -> c.id()).toList();
		
		return "Camino desde 0 hasta 0:\n" + aux + 
				"\nKms: " + penalizacion + 
				"\nBeneficio: " + beneficio;
	}

	

}
