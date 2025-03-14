
package ejercicio1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class ing2 implements ValuesInRangeData<Integer, SolucionAlmacen> {

    public ing2(String string) {
    	DatosAlmacenes.iniDatos(string);
	}

	@Override
    public Integer size() {
        return DatosAlmacenes.getNumProductos();
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }

    @Override
    public Integer max(Integer i) {
        return DatosAlmacenes.getNumAlmacenes() + 1;
    }

    @Override
    public Double fitnessFunction(List<Integer> ls) {
        Long fo = getFo(ls);
        Double r1 = getR1(ls);
        Double r2 = getR2(ls);
        return fo - 1000 * r1 - 1000 * r2;
    }

    private Long getFo(List<Integer> ls) {
        return ls.stream()
                .filter(x -> x != DatosAlmacenes.getNumAlmacenes())
                .count();
    }

    private Double getR1(List<Integer> ls) { // volumes, entering the warehouse
        Double res = 0.0;
        for (int j = 0; j < DatosAlmacenes.getNumAlmacenes(); j++) {
            Integer almacenes = DatosAlmacenes.getMetrosCubicosAlmacen(j);
            Integer productos = 0;
            for (int i = 0; i < DatosAlmacenes.getNumProductos(); i++) {
                productos += ls.get(i) * DatosAlmacenes.getMetrosCubicosProducto(i);
            }
            if (productos < almacenes) {
                res += Math.pow(almacenes - productos, 2);
            }
        }
        return res;
    }

    private Double getR2(List<Integer> ls) { // incompatible products
        Double res = 0.0;
        Integer cuenta = 0;
        for (int j = 0; j < DatosAlmacenes.getNumAlmacenes(); j++) {
            for (int i1 = 0; i1 < DatosAlmacenes.getNumProductos(); i1++) {
                for (int i2 = 0; i2 < DatosAlmacenes.getNumProductos(); i2++) {
                    if (i1 != i2 && ls.get(i1) == j && DatosAlmacenes.sonIncompatibles(i1, i2)) {
                        cuenta++;
                    }
                }
            }
        }
        if (cuenta > 0) {
            res += Math.pow(cuenta, 2);
        }
        return res;
    }

    public static SolucionAlmacen solucion(String file) throws IOException {
        DatosAlmacenes.iniDatos(file); // initialize data
        List<Integer> productos = new ArrayList<>(); // list of products: [0, n)
        for (int i = 0; i < DatosAlmacenes.getNumProductos(); i++) {
            productos.add(i);
        }
        return SolucionAlmacen.create(productos); // create solution
    }

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public SolucionAlmacen solucion(List<Integer> value) {
		return SolucionAlmacen.create(value);
	}
}
