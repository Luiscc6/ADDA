package ejercicio3p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.common.List2;


public record FestivalProblem(Integer index, List<Integer> entradasTipos, List<Integer> entradasAreas) {
	
	public static FestivalProblem initial() {
	    List<Integer> entraT = List2.empty();
	    List<Integer> entraA = List2.empty();
	    for(int k =0; k<DatosFestival.getNumTiposEntrada(); k++) {
	    	entraT.add(DatosFestival.getCuotaMinima(k));
	    	
	    }
	    for(int z =0; z<DatosFestival.getNumAreas(); z++) {
	    	entraA.add(0);
	    	
	    }
	    var vertice = of(0,entraT,entraA);
	    System.out.println(vertice);
	    return vertice;
		
	}	
	
	public static FestivalProblem of(Integer i, List<Integer> eT, List<Integer> eA) {
		return new FestivalProblem(i, eT, eA);
	}
	
	private Integer i() {
		return DatosFestival.getI(index);
		}
		private Integer jPrima() {
		return DatosFestival.getJPrima(index);
		}
		private Integer j() {
		return DatosFestival.getJ(index);
		}
	
	public List<Integer> actions() {
		List<Integer> asg = List2.empty();	
		//System.out.println(this.index);
		if(goal() || goalHasSolution()) {
						return asg;
		}
//		System.out.println(entradasTipos);
//		System.out.println(entradasAreas);
		
		
		int i = i();//Tipo entrada
		int j = j();//Area`
		
		int minimo2 = entradasTipos.get(i);
		//System.out.println("Soy minimo: " + minimo);
		int maximo = DatosFestival.getAforoMaximoArea(j);
		
		int aux = entradasAreas.get(j);
		int aux2 = maximo-aux;//lo que puedo asignar
		int minimoreal = Math.min(minimo2, aux2);
		
		if(aux<=DatosFestival.getAforoMaximoArea(j)) {
			for(int k = minimoreal;k<=aux2;k++) {
				//System.out.println("Soy k " + k);
				
				asg.add(k);
			}
			
		}
		
		//System.out.println(asg);
		return asg;
		
				
		
	}
	
	public Double heuristic() {
		Double res = 0.;
		for (int i=DatosFestival.getI(index);
		i<DatosFestival.getNumTiposEntrada(); i++) {
		res += DatosFestival.getCuotaMinima(i) -
		entradasTipos.get(i);
		}
		return res;
		}



	    public FestivalProblem neighbor(Integer a) {
	        List<Integer> nextEntradasTipos = new ArrayList<>(this.entradasTipos);
	        List<Integer> nextEntradasAreas = new ArrayList<>(this.entradasAreas);

	        int numAreas = DatosFestival.getNumAreas();
	        int i = this.index / numAreas;
	        int j = this.index % numAreas;

	        nextEntradasTipos.set(i, nextEntradasTipos.get(i) - a);
	        nextEntradasAreas.set(j, nextEntradasAreas.get(j) + a);

	        int nextIndex = this.index + 1;
	        var vertice = of(nextIndex, nextEntradasTipos, nextEntradasAreas);
	        //System.out.println(vertice);
	        return vertice;
	    }
  
    public Boolean goal() {
        
    	return this.index() == DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
    }
    

    public Boolean goalHasSolution() {
    	return IntStream.range(0,
    	DatosFestival.getNumTiposEntrada()).allMatch(i -> entradasTipos.get(i) ==
    	0) &&
    	IntStream.range(0,
    	DatosFestival.getNumAreas()).allMatch(j -> entradasAreas.get(j) <=
    	DatosFestival.getAforoMaximoArea(j));
    	}
	
	
}
