package entidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import utils.Datos;

public class ComparadorMedallas implements Comparator <Metal>{

	public int compare(Metal m1, Metal m2) {
		
		return (int) (m1.maximaPurezaAlcanzada() - m2.maximaPurezaAlcanzada());
	}


 public void mostrarMedallasOrdenadas(){
	 LinkedList<Metal> medallas;
	 medallas = new LinkedList<Metal>();
	 for(Oro o : Datos.OROS) {
		 medallas.add(o);
	 }
	 for(Plata p: Datos.PLATAS) {
		 medallas.add(p);
	 }
	 for(Bronce b : Datos.BRONCES) {
		 medallas.add(b);
	 }
	 
	 Collections.sort(medallas, new ComparadorMedallas());
	 Iterator it = medallas.iterator();
	 while (it.hasNext()) {
		 System.out.println("Medalla: " + it.next());
	 }
	 
 }

}

