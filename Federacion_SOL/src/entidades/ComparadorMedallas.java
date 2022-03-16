package entidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import utils.Datos;

public class ComparadorMedallas implements Comparator<Metal> {

	public int compare(Metal m1, Metal m2) {

		return (int) (m1.maximaPurezaAlcanzada() - m2.maximaPurezaAlcanzada());
	}

	/**
	 * Funcion que muestra por la salida estándar la lista de medallas almacenadas
	 * en la clase Datos.java en los arrays OROS, PLATAS y BRONCES ordenadas en
	 * función de la pureza (a través de este comparador)
	 */
	public void mostrarMedallasOrdenadas() {
		LinkedList<Metal> medallas;
		medallas = new LinkedList<Metal>();
		for (Oro o : Datos.OROS) {
			medallas.add(o);
		}
		for (Plata p : Datos.PLATAS) {
			medallas.add(p);
		}
		for (Bronce b : Datos.BRONCES) {
			medallas.add(b);
		}

		Collections.sort(medallas, new ComparadorMedallas());
		System.out.println("La lista ordenada de todas las medallas es: ");
		Iterator <Metal> it = medallas.iterator();
		int i = 1; //Marcador de posicion de la medalla (orden)
		while (it.hasNext()) {
			System.out.println(i + ":" + "Medalla: " + ((Metal)it.next()).toString() + " ");
			i++;
		}

	}

}
