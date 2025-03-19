package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeejer1 implements ValuesInRangeData<Integer, SolucionAlmacen> {

    public InRangeejer1(String linea) {
        DatosAlmacenes.iniDatos(linea);
    }

    @Override
    public Integer size() {
        return DatosAlmacenes.getNumProductos();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    public Double fitnessFunction(List<Integer> value) {


            // 2. Calcular incompatibilidades 
            int incompatibles = 0;
            Map<Integer, List<Integer>> productosPorAlmacen = IntStream.range(0, DatosAlmacenes.getNumProductos())
                .boxed()
                .filter(i -> value.get(i) < DatosAlmacenes.getNumAlmacenes())
                .collect(Collectors.groupingBy(value::get));
            //System.out.println(productosPorAlmacen);

            for (List<Integer> productos : productosPorAlmacen.values()) {
                for (int i = 0; i < productos.size(); i++) {
                    for (int j = i + 1; j < productos.size(); j++) {
                        int p1 = productos.get(i);
                        int p2 = productos.get(j);
                        if (DatosAlmacenes.sonIncompatibles(p1, p2)) {
                            incompatibles++;
                        }
                    }
                }
            }

        
        

        // 3. Calcular exceso de capacidad por almacén
        double penCap = 0.;
        Map<Integer, Double> capacidadUsada = new HashMap<>();
        for (int i = 0; i < DatosAlmacenes.getNumProductos(); i++) {
            int almacen = value.get(i);
            if (almacen >= DatosAlmacenes.getNumAlmacenes()) continue;
            double volumen = DatosAlmacenes.getMetrosCubicosProducto(i);
            capacidadUsada.put(almacen, capacidadUsada.getOrDefault(almacen, 0.0) + volumen);
        }

        for (int almacen : capacidadUsada.keySet()) {
            double capacidadMaxima = DatosAlmacenes.getMetrosCubicosAlmacen(almacen);
            double exceso = Math.max(0, capacidadUsada.get(almacen) - capacidadMaxima);
            penCap += exceso * 100; // ×100 como en InRangeAlmacen
        }

        // 4. Fitness = valor positivo - penalizaciones
     // Penalizaciones ajustadas
        double penIncomp = incompatibles * 10000; // Penalización más alta

        // Fitness = (productos asignados × 100) - penIncomp - penCap
        long productosAsignados = value.stream()
            .filter(almacen -> almacen < DatosAlmacenes.getNumAlmacenes()).count();

        return (productosAsignados * 100.0) - penIncomp - penCap;
    }

    @Override
    public SolucionAlmacen solucion(List<Integer> value) {
        return SolucionAlmacen.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return DatosAlmacenes.getNumAlmacenes() + 1;
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }
}