package entidades;

import java.util.Scanner;

import utils.Datos;

public class Colegiado {
	private long id;
	private Categoria categoria; // Examen 3 Ejercicio 3
	private DatosPersona persona;

	public Colegiado(long id, Categoria categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Colegiado(long id, Categoria categoria, DatosPersona dp) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.persona = dp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getPersona() {
		return this.persona.toString();
	}

	// Examen 3 Ejercicio 3
	public static Colegiado nuevoColegiado() {
		Colegiado ret = null;
		long id = -1;
		Categoria cat;
		int elecc = -1;
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo colegiado:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca la categoria del nuevo colegiado:");
			System.out.println("Seleccione el id de entre las siguientes categorias:");
			Categoria.mostrarTodos();
			in = new Scanner(System.in);
			elecc = in.nextInt();
			if (elecc >= 1 && elecc <= Categoria.values().length)
				valido = true;
		} while (!valido);
		cat = Categoria.values()[elecc - 1];

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Colegiado(id, cat, dp);
		return ret;
	}

	// Ejercicio 2 examen 5

	/*
	 * metodo toString que devuelve los datos de un Colegiado. Llamamos a los
	 * m�todos getNombre(), getNifnie(), getFechaNac(), getTelefono() y getClass()
	 * para obtener los resultados La estructura del m�todo toString deber� ser del
	 * tipo: 7. Hector Calvo Blanco (76234511P) nacido el 1984, tfno: 688257823
	 * CAT=Senior
	 */

	@Override
	public String toString() {
		return "" + id + "." + persona.getNombre() + "(" + persona.getNifnie().mostrar() + ")" + "nacido el" + persona.getFechaNac().getYear()
				+ ", tfno: " + persona.getTelefono() + "CAT=" + categoria.getNombre();
	}

}
