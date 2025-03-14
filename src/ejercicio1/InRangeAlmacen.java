package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeAlmacen implements ValuesInRangeData<Integer, SolucionAlmacen>{

	@Override
	public Integer size() {
		return DatosAlmacenes.getNumProductos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		// TODO Auto-generated method stub
		Double valor = 0.;
		Integer metrosCubicosDeMas = calcularSobrante(value);
		Integer incompatibilidades = calcularIncompatibles(value);
		valor = value.stream().filter(x-> x != DatosAlmacenes.getNumAlmacenes()).mapToDouble(x->100).sum();
		Double finalvalor = valor - (-metrosCubicosDeMas*100 + incompatibilidades*1000);
		return finalvalor;
	}
	
	public Map<Integer, Integer> auxGrouping (List<Integer>  ls) {
		return null;
	}

	private Integer calcularIncompatibles(List<Integer> value) {
		Map<Integer, List<Integer>> productosPorAlmacen = IntStream	.range(0, DatosAlmacenes.getNumProductos())
				.boxed()
				.collect(Collectors.groupingBy(x->value.get(x)));
		int incompatibles = 0;
		for (int l : productosPorAlmacen.keySet()) 
		{
			if ( l!=DatosAlmacenes.getNumAlmacenes()) {
				List<Integer>lista = productosPorAlmacen.get(l);
						for (int i =0; i<lista.size();i++ ) {
							for (int j = 0; j<lista.size(); j++) {
								if (DatosAlmacenes.sonIncompatibles(i, j)) incompatibles++;
							}
						}
			}
		}
		return Integer.valueOf(incompatibles);
	}

	private Integer calcularSobrante(List<Integer> value) {
		Map<Integer, Integer> capDisponibleAlmacen = IntStream	.range(0, DatosAlmacenes.getNumAlmacenes())
				.boxed()
				.collect(Collectors.toMap(x->x, x->DatosAlmacenes.getMetrosCubicosAlmacen(x)));

		for (int i = 0;i<value.size();i++ ) {
			Integer almacen = value.get(i);
			Integer capRestante = almacen != DatosAlmacenes.getNumAlmacenes()? (capDisponibleAlmacen.get(almacen) - DatosAlmacenes.getMetrosCubicosProducto(i)) : 0;
			capDisponibleAlmacen.put(almacen, capRestante);
		}

		return capDisponibleAlmacen.values().stream().parallel().filter(x-> x<0 ).mapToInt(x->x).sum();
	}

	@Override
	public SolucionAlmacen solucion(List<Integer> value) {
		System.out.println(value);
		return SolucionAlmacen.create(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosAlmacenes.getNumAlmacenes()+1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	
}