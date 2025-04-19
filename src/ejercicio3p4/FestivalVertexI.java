package ejercicio3p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import us.lsi.common.List2;


public record FestivalVertexI(Integer index, List<Integer> entradasTipos, List<Integer> entradasAreas)
        implements FestivalVertex {


	@Override
	public List<Integer> actions() {
		List<Integer> asg = List2.empty();	
		if(index == DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada()) {
			return asg;
		}
		int i = index/DatosFestival.getNumAreas(); //Tipo entrada
		int j = index%DatosFestival.getNumAreas();//Area`
		int minimo = 0;
		int minimo2 = entradasTipos.get(i);
		//System.out.println("Soy minimo: " + minimo);
		int maximo = DatosFestival.getAforoMaximoArea(j);
		
		int aux = entradasAreas.get(j);
		int aux2 = DatosFestival.getAforoMaximoArea(j)- aux;//lo que puedo asignar
		//System.out.println("Soy maximo: "  +aux2);
		 boolean esUltimaAreaParaTipo = (j == DatosFestival.getNumAreas() - 1);
		 
	        if (esUltimaAreaParaTipo && minimo2 > 0) {
	            minimo = minimo2;
	        }
	        if (minimo > aux2) {
	        	return asg;
	        }
		if(aux<=DatosFestival.getAforoMaximoArea(j)) {
			for(int k = minimo;k<=aux2;k++) {
				//System.out.println("Soy k " + k);
				
				asg.add(k);
			}
		}
		
		//System.out.println(asg);
		return asg;
		
				
		
	}


	 @Override
	    public FestivalVertex neighbor(Integer a) {
	        List<Integer> nextEntradasTipos = new ArrayList<>(this.entradasTipos);
	        List<Integer> nextEntradasAreas = new ArrayList<>(this.entradasAreas);

	        int numAreas = DatosFestival.getNumAreas();
	        int i = this.index / numAreas;
	        int j = this.index % numAreas;

	        nextEntradasTipos.set(i, nextEntradasTipos.get(i) - a);
	        nextEntradasAreas.set(j, nextEntradasAreas.get(j) + a);

	        int nextIndex = this.index + 1;
	        var vertice = new FestivalVertexI(nextIndex, nextEntradasTipos, nextEntradasAreas);
	        //System.out.println(vertice);
	        return vertice;
	    }


    @Override
    public FestivalEdge edge(Integer a) {
        return FestivalEdge.of(this, neighbor(a), a);
    }



    @Override
    public Boolean goal() {
        
    	return this.index() == DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
    }



}