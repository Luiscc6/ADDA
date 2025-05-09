package ejercicio2p4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.common.Set2;

public record CursosProblem(Integer index, Integer prepres, Set<Integer> areascubi, List<Integer>cursec) {
		
	
	
	public static CursosProblem initial() {
		Integer prepres= DatosCursos.getPresupuestoTotal();
		Set<Integer> areascubi = Set2.empty();
		List<Integer> cursec = List2.empty();
		CursosProblem vertice = of(0,prepres,areascubi,cursec);
		System.out.println(vertice);
		return vertice;
		
	}
	
	public static CursosProblem of (Integer index, Integer prepres, Set<Integer> areascubi, List<Integer>cursec) {
		return new CursosProblem(index, prepres,areascubi, cursec);
	}
	
	
	public List<Integer> actions() {
	    List<Integer> asg = new ArrayList<>();

	    if (index >= DatosCursos.getNumCursos()) {
	        return asg; 
	    }

	    int aux = DatosCursos.getCoste(index);
	    System.out.println(aux);
	    if (aux <= prepres) {
	        asg.add(1); 
	    }
	    asg.add(0); 

	    System.out.println("Soy asg" + asg);
	    return asg;
	}

	
	
	public CursosProblem neighbor(Integer a) {
		   
		Set<Integer >areascubi2 = new HashSet(areascubi);
		List<Integer >cursec2 = new ArrayList(cursec);
		int prepres2 = prepres;
		if(a==0) {
			return of(index+1,prepres2,areascubi2,cursec2);
		}
		
		int prepres3 = prepres2-DatosCursos.getCoste(index);
		areascubi2.add(DatosCursos.getArea(index));
		cursec2.add(index);
		var vertice = of(index+1,prepres3,areascubi2,cursec2);
		System.out.println(vertice);
		return vertice;
	}

	
	
	public Double heuristic() {
		return IntStream.range(index, DatosCursos.getNumCursos())
				.mapToDouble(x->DatosCursos.getRelevancia(index))
				.sum();
	}
	
	public Boolean goal() {
		System.out.println("soy index" + this.index);
		System.out.println("soy cursos" + DatosCursos.getNumCursos());
		
		boolean esSolucion = this.index == DatosCursos.getNumCursos();
		if(esSolucion) {
			System.out.println("He llegado es Goal");
			return true;
			
		}
		return false;
	}
	
	public Boolean goalHasSolution() {
		Boolean esSolucion = false;
		int aux =cursec.stream().mapToInt(i->DatosCursos.getDuracion(i)).sum();
		System.out.println(aux);
		int aux2 = cursec.stream().mapToInt(i->DatosCursos.getCoste(i)).sum();
		System.out.println(aux2);
		if(cursec.size() >0) {
			int media = aux/cursec.size();
			if((media<20) & (aux2 > prepres)) {
				esSolucion = false;
				
			}
		}
		Integer numAreas = DatosCursos.getNumAreas();
		int numareascubi = areascubi().size();
		Integer numCursos = DatosCursos.getNumCursos();
		Integer index2 = this.index;
		 System.out.println("numareas" + numAreas);
		 System.out.println("numareascubi" + numareascubi);
		 System.out.println("numCursos" + numCursos);
		 System.out.println("numindex" + index2);
		if (index2 == numCursos && numareascubi== numAreas){
			System.out.println("he llegado goalhassolution");
			esSolucion = true;
		}
		return esSolucion;
			
		
		}
	
	/*
	public Boolean goalHasSolution() {
		boolean esSolucion = true;
		// 1. Comprobamos que hay al menos un curso por cada área
		for (int area = 0; area < DatosCursos.getNumAreas(); area++) {
		if (!areascubi.contains(area)) {
		esSolucion = false;
		break;
		}
		}
		// 2. Comprobamos duración media >= 20
		int acum = 0;
		for (int i = 0; i < cursec.size(); i++) {
		acum += DatosCursos.getDuracion(cursec.get(i));
		}
		double media = cursec.isEmpty() ? 0.0 : acum / (double) cursec.size();
		if (media < 20.0) {
		esSolucion = false;
		}
		// 3. Comprobamos nº cursos de tecnología >= cualquier otra
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < cursec.size(); i++) {
		int curso = cursec.get(i);
		int area = DatosCursos.getArea(curso);
		map.put(area, map.getOrDefault(area, 0) + 1);
		}
		int numTecnologia = map.getOrDefault(0, 0);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
		int area = entry.getKey();
		int count = entry.getValue();
		if (area != 0 && count > numTecnologia) {
		esSolucion = false;
		break;
		}
		}
		return esSolucion;
		}
		*/
		
	}
