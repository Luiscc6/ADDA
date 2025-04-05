package ejercicio1p4;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ejercicio1p4.DatosAlmacenes.Producto;

public class SolucionAlmacen {

    public static SolucionAlmacen create(List<Integer> ls) {
        return new SolucionAlmacen(ls);
    }

    private Integer numproductos;
    private Map<Producto, Integer> solucion;


    private SolucionAlmacen(List<Integer> ls) {
        numproductos = 0;
        solucion = new HashMap<>();
        for (int i = 0; i < ls.size(); i++) {
            Integer e = ls.get(i);
            if (e < DatosAlmacenes.getNumAlmacenes()) {
                Producto v = DatosAlmacenes.getProducto(i);
                solucion.put(v, e);
                numproductos++;
            }
        }
    }


    @Override
    public String toString() {
		return solucion.entrySet().stream()
		.map(p -> p.getKey().producto()+": Almacen "+p.getValue())
		.collect(Collectors.joining("\n", "Reparto de productos y almacen en el que se coloca cada uno de ellos:\n", String.format("\nProductos colocados: %d", numproductos)));
	}
    
    public Integer getNumProductos() {
    	return solucion.size();
    }
    
}

