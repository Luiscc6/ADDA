package ejercicio3p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import us.lsi.common.List2;


public record FestivalVertexI(Integer index, List<Integer> entradasTipos, List<Integer> entradasAreas)
        implements FestivalVertex {

	private Integer i() {
		return DatosFestival.getI(index);
		}
		private Integer jPrima() {
		return DatosFestival.getJPrima(index);
		}
		private Integer j() {
		return DatosFestival.getJ(index);
		}
	
	@Override
	public List<Integer> actions() {
		List<Integer> asg = List2.empty();	
		//System.out.println(this.index);
		if((goal() || goalHasSolution()) == true) {
//			System.out.println("entro");
			return asg;
		}
//		System.out.println("soy et "+ entradasTipos);
//	System.out.println(entradasAreas);
		
		
		int i = i();//Tipo entrada
		int j = j();//Area`
		
		int minimo2 = entradasTipos.get(i);
		//System.out.println("Soy minimo: " + minimo);
		int maximo = DatosFestival.getAforoMaximoArea(j);
//		System.out.println("soy minimo2  "+minimo2);
		int aux = entradasAreas.get(j);
		int aux2 = Math.max(0, maximo - aux);
//		System.out.println(aux2);

		int minimoreal = Math.abs(Math.min(minimo2, aux2));
//		System.out.println(minimoreal);
			for(int k = minimoreal;k<=aux2;k++) {
				//System.out.println("Soy k " + k);
				
				asg.add(k);
			}
			
		
		//System.out.println(asg);
		return asg;
		
				
		
	}


	 @Override
	 public FestivalVertex neighbor(Integer a) {
		 List<Integer> nextEntradasTipos = new ArrayList<>(this.entradasTipos);
	        List<Integer> nextEntradasAreas = new ArrayList<>(this.entradasAreas);
		    nextEntradasTipos.set(i(), nextEntradasTipos.get(i()) - a);
		    
		    nextEntradasAreas.set(j(), nextEntradasAreas.get(j()) + a);

		    Integer index =this.index;
		    if (nextEntradasTipos.get(i()) == 0) {
		        index = DatosFestival.getNumAreas() * (i() + 1);
		    } else {
		        index = index + 1;
		    }

		    return new FestivalVertexI(index, nextEntradasTipos, nextEntradasAreas);
		}



    @Override
    public FestivalEdge edge(Integer a) {
        return FestivalEdge.of(this, neighbor(a), a);
    }



    @Override
    public Boolean goal() {
        
    	return this.index() == DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
    }
    
    @Override
    public Boolean goalHasSolution() {
    	
    	boolean goli = IntStream.range(0,
    	    	DatosFestival.getNumTiposEntrada()).allMatch(i -> entradasTipos.get(i) ==
    	    	0) &&
    	    	(IntStream.range(0,
    	    	DatosFestival.getNumAreas()).allMatch(j -> entradasAreas.get(j) <=
    	    	DatosFestival.getAforoMaximoArea(j)));
//    	System.out.println(goli);
    	return goli;
    	}



}