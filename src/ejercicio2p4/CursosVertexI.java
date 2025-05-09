package ejercicio2p4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ejercicio1p4.DatosAlmacenes.Producto;
import us.lsi.common.List2;

public record CursosVertexI(Integer index, Integer prepres, Set<Integer> areascubi, List<Integer>cursec)
		implements CursosVertex {
	
//	Restricciones:
//		• El programa debe incluir al menos un curso de cada área de
//		conocimiento.
//		• El número de cursos de tecnología seleccionados debe ser superior o
//		igual a los seleccionados de cualquiera otra área.
//		• Se debe garantizar que la duración INmedia de los cursos seleccionados sea
//		de al menos 20 horas.
//		• El coste total de los cursos seleccionados no debe superar un presupuesto
//		total asignado para el programa.

	@Override
	public List<Integer> actions() {
		List<Integer> asg = new ArrayList<Integer>();
////		if(index == DatosCursos.getNumCursos()) {
////			return asg;
////		}
////		for(int i = 0; i<DatosCursos.getNumCursos();i++) {
////			if(cursec.get(i)==index) {
////				return asg;
////			}
////		}
////		if (areascubi.contains(0) & (DatosCursos.getCurso(0) !=null)) {
////			int aux =cursec.stream().mapToInt(i->DatosCursos.getDuracion(i)).sum();
////			int aux2 = cursec.stream().mapToInt(i->DatosCursos.getCoste(i)).sum();
////			if(((aux/cursec.size())>=20) & (aux2 <= prepres)) {
////				
////				asg.add(1);
////			}
//		
//		
//		asg.add(0);
//		System.out.println(asg);
//		return asg;
		
		
			int aux = DatosCursos.getCoste(index);
			if(aux <=prepres) {
				asg.add(1);
			}
		
		asg.add(0);
		//System.out.println("Soy asg" + asg);
		
		return asg;
		
	}

	@Override
	public CursosVertex neighbor(Integer a) {
	   
		Set<Integer >areascubi2 = new HashSet(areascubi);
		List<Integer >cursec2 = new ArrayList(cursec);
		int prepres2 = prepres;
		if(a==0) {
			return new CursosVertexI(index+1,prepres2,areascubi2,cursec2);
		}
		
		int prepres3 = prepres2-DatosCursos.getCoste(index);
		areascubi2.add(DatosCursos.getArea(index));
		cursec2.add(index);
		var vertice = new CursosVertexI(index+1,prepres3,areascubi2,cursec2);
		//System.out.println(vertice);
		return vertice;

//		int aux = cursec2.stream().mapToInt(i->DatosCursos.getCoste(i)).sum();
//		cursec2.add(a);
//		int aux2 = DatosCursos.getArea(a);
//		areascubi2.add(aux2);
//		var vertice = new CursosVertexI(index+1,prepres2-aux,areascubi2,cursec2);
//		System.out.println(vertice);
//		return vertice;
		
		
		
		
	}

	@Override
	public CursosEdge edge(Integer a) {
		return CursosEdge.of(this, neighbor(a), a);
	}

	@Override
	public Boolean goal() {
		return this.index == DatosCursos.getNumCursos();
	}
	
	@Override
	public Boolean goalHasSolution() {
		
		
		int aux =cursec.stream().mapToInt(i->DatosCursos.getDuracion(i)).sum();
		System.out.println(aux);
		int aux2 = cursec.stream().mapToInt(i->DatosCursos.getCoste(i)).sum();
		System.out.println(aux2);
		if(cursec.size() >0) {
			if(((aux/cursec.size())<20) & (aux2 > prepres)) {
				System.out.println("entro");
				//return areascubi.size() ==DatosCursos.getNumAreas();
				return false;
			}
		}
		
		
		return true;
	}
			
			
		
		
	


	

}
