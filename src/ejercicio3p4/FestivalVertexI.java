package ejercicio3p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ejercicio1p4.DatosAlmacenes.Producto;
import ejercicio2p4.DatosCursos;
import us.lsi.common.List2;

public record FestivalVertexI(Integer index, List<Integer> entradasTipos, List<Integer> entradasAreas)
		implements FestivalVertex {
	
//	 Propiedades de los vértices (Prop. individuales)
//	 • z, entero: índice que recorre todas las variables del modelo.
//	 • z = m*i + j’
//	 • i = z / n
//	 • j' = z % n
//	 • j = indOrd[i,j’]
//	 • entradasTipos, lista de enteros: cantidad de entradas por asignar de cada tipo
//	 • entradasAreas, lista de enteros: cantidad de entradas asignadas a cada área

	@Override
	public List<Integer> actions() {
		List<Integer> asg = List2.empty();	
		if(index == DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada()) {
			return asg;
		}
		int i = index/DatosFestival.getNumAreas(); //Tipo entrada
		int j = index%DatosFestival.getNumAreas();//Area`
		
		int minimo = entradasTipos.get(i);
		System.out.println("Soy minimo: " + minimo);
		int maximo = DatosFestival.getAforoMaximoArea(j);
		System.out.println("Soy maximo: "  +maximo);
		for(int k = minimo;k<=maximo;k++) {
			//System.out.println("Soy k " + k);
			
			asg.add(k);
		}
		System.out.println(asg);
		return asg;
		
				
		
	}

	@Override
	public FestivalVertex neighbor(Integer a) {
	   
		List<Integer> entraT = new ArrayList(entradasTipos);
		List<Integer> entraA = new ArrayList(entradasAreas);
		int i = index/DatosFestival.getNumAreas(); //Tipo entrada
		int j = index%DatosFestival.getNumAreas();
		int aux = entraT.get(i);
		entraT.set(i,Math.abs(aux-a));
		entraA.set(j,a);
		var vertice = new FestivalVertexI(index+1, entraT, entraA);
		System.out.println(vertice);
		return vertice;
		
		
	}

	@Override
	public FestivalEdge edge(Integer a) {
		return FestivalEdge.of(this, neighbor(a), a);
	}

	@Override
	public Boolean goal() {
		return this.index == DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
	}
	
	@Override
	public Boolean goalHasSolution() {
		
		
		for(int i=0; i< entradasAreas.size();i++) {
			for(int j=0; i< entradasTipos.size();j++) {
			if(DatosFestival.getAforoMaximoArea(i)>=entradasAreas.get(i)) {
				if(DatosFestival.getCuotaMinima(j)<=entradasTipos.get(j)) {
					return index== DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
				}
			}
		}
		}
		
		return true;
	}

}
	 
