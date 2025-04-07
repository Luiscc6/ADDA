package ejercicio1p4;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio1p4.DatosAlmacenes.Producto;

public class SolucionAlmacen {

    public static SolucionAlmacen create(List<Integer> ls) {
        return new SolucionAlmacen(ls);
    }

    private Integer numproductos;
    private Map<Producto, Integer> solucion;


    private SolucionAlmacen(List<Integer> ls) {
		numproductos=(int) IntStream.range(0, ls.size()).filter(x->ls.get(x)!=-1).count();
		Map<Producto, Integer> res=new HashMap<DatosAlmacenes.Producto, Integer>();
		for(Integer i=0;i<ls.size();i++) {
			if(ls.get(i)!=-1) {
				res.put(DatosAlmacenes.getProducto(i), ls.get(i));
			}
		}
		solucion=res;
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

