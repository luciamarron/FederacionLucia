package entidades;

import java.util.Scanner;

import Validaciones.Validacion;
import utils.Datos;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}
	
	//Ejercicio 4 Examen 5
	
	public static Manager nuevoManager() {
		Manager ret = null;
		Scanner in;
		DatosPersona dp = null;
		long id = -1;
		String telefono = "";
		String direccion = "";
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo mánager: ");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el teléfono del nuevo mánager: ");
			in = new Scanner(System.in);
			telefono = in.nextLine();
			valido = Validacion.validarTelefono(telefono); 
			if (telefono.length() > 3)
				valido = true;
		} while (!valido);
		do {
			System.out.println("Introduzca la dirección del nuevo mánager: ");
			in = new Scanner(System.in);
			direccion = in.nextLine();
			if (direccion.length() > 3)
				valido = true;
		} while (!valido);
		
		ret = new Manager(id, telefono, direccion, dp);
		return ret;
	}

	/*
	 * Método toString que devuelve el id, el nombre, la documentación, la fecha de nacimiento y dos teléfonos.
	 * El nombre, la documentación, la fecha de nacimiento y el segundo teléfono los obtendremos a través de los getters de persona, 
	 * mientras que el id y el teléfono 1 los obtendremos directamente de la clase Mánager
	 */
	@Override
	public String toString() {
		return id + persona.getNombre() + "(" + persona.getNifnie() + ")" + "del año " + persona.getFechaNac() + "Tfno1: " + telefono + ",Tfno2: " + persona.getTelefono() ;
	}
	
	

}
