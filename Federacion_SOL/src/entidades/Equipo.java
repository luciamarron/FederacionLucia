package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;

import Validaciones.Validacion;
import utils.Datos;
import utils.Utilidades;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	public String nombre;
	private Manager manager;
	private Atleta[] atletas;
	
	public Equipo() {
		super();
	}

	public Equipo(long id, String nombre, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.nombre = nombre;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}

	//Examen 1 Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret+= "EQ"+idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion()+") " + atletas.length + " componentes en el equipo:\n";
		for(Atleta a: atletas) {
			ret += a.getId()+". " + a.getPersona().getNombre() + "(" + a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ ") "
					+ " Datos físicos:\t"+ a.getPeso()+ "Kgs.\t" + a.getAltura()+"m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}
	
	//Ejercicio 1 examen 8 a)
	/*
	 * Método estático Equipo nuevoEquipo que pregunta al usuario el id, la altura y el peso del atleta
	 * y crea un nuevoAtleta a partir de los datos proporcionados.
	 */

	public static Equipo nuevoEquipo() {
		Equipo ret = new Equipo();
		boolean valido = false;
		String nombre = "";
		long id = 0;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("Introduzca el nombre del nuevo equipo:");
			nombre = in.nextLine();
			valido = Validacion.validarNombreEquipo(nombre);
			if (!valido)
				System.out.println("El nombre " + nombre + " no es válido.");
			else {
				System.out.println("¿Es correcto el nombre elegido:" + nombre + "?");
				valido = Utilidades.leerBoolean();
			}
		} while (!valido);
		valido = false;
		int anio = LocalDate.now().getYear();
		Manager manager = Manager.nuevoManager();
		boolean resp = true;
		HashSet<Atleta> atletas = new HashSet<Atleta>();
		System.out.println("Introduzca los datos de los atletas del equipo (entre 3 y 5)");
		for(int i=1; resp; i++) {
			System.out.println("Introduzca datos del Atleta "+i+":");
			Atleta a = Atleta.nuevoAtleta();
			atletas.add(a);
			if(atletas.size()>=3) {
				System.out.println("Ya tendría un equipo válido.");
				if(atletas.size()<5) {
					System.out.println("¿Desea introducir otro atleta al equipo?");
					resp= Utilidades.leerBoolean();
				}
				else
					System.out.println("Ya ha completado el equipo. No puede añadir más atletas.");
				resp = false;
			}
		}
		Atleta[] atletasArray = new Atleta[atletas.size()];
		atletas.toArray(atletasArray);
		ret = new Equipo(id, nombre, anio, manager, atletasArray);
		return ret;
	}

	
	//apartado b) y c)
	public static void mostrarManagers() {
		Scanner in = new Scanner(System.in);
		Equipo nuevo;
		boolean valido = false;
		int subelecc = -1;
	do {
		System.out.println("Introduzca los datos del Atleta:");
		nuevo = Equipo.nuevoEquipo();
		System.out.println("¿Son correctos los datos del atleta introducido?" + nuevo);
		if (valido = Utilidades.leerBoolean()) {
			valido = true;
		}
	} while (!valido);
	/// Se muestran las pruebas colectivas importadas desde el fichero de
	/// caracteres pruebas.txt
	valido = false;
	Prueba[] colectiva = new Prueba[256];
	File fichero = new File("pruebas.txt");
	FileReader lector = null;
	BufferedReader buffer = null;
	int c = 0; /// contador de pruebas colectivas
	try {
		try {
			lector = new FileReader(fichero);
			buffer = new BufferedReader(lector);
			String linea;
			while ((linea = buffer.readLine()) != null) {
				String[] campos = linea.split("\\|");
				long idPrueba = Long.valueOf(campos[0]);
				String nombrePrueba = campos[1];
				LocalDate fecha = LocalDate.parse(campos[2], DateTimeFormatter.ofPattern("dd/MM/YYYY"));
				String lugarString = campos[3];
				/// Hay que convertir el String con el lugar a su correspondiente valor de la
				/// enum Lugar
				Lugar lugar = null;
				for (Lugar l : Lugar.values()) {
					if (l.name().equalsIgnoreCase(lugarString)) {
						lugar = l;
					}
				}
				boolean colectiva1 = Boolean.valueOf(campos[4]);
				Prueba p = new Prueba(idPrueba, nombrePrueba, fecha, lugar, colectiva1);
				/// Solo se muestran al usuario las pruebas individuales, que se van guardando
				/// en el array individuales
				if (p.isIndividual()) {
					System.out.println("" + p);
					colectiva[c] = p;
					c++;
				}
			}
		} finally {
			if (buffer != null) {
				buffer.close();
			}
			if (lector != null) {
				lector.close();
			}
		}
	} catch (FileNotFoundException e) {
		System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
	} catch (IOException e) {
		System.out.println("Se ha producido una IOException" + e.getMessage());
	} catch (Exception e) {
		System.out.println("Se ha producido una Exception" + e.getMessage());
	}
	/// Se pide al usuario que elija una de las pruebas y se comprueba que es un
	/// valor correcto
	Prueba pruebaSelecc = null;
	do {
		System.out.println("Introduzca el id de la prueba en que desea inscribirse:");
		subelecc = in.nextInt();
		for (int j = 0; j < c; j++) {
			if (((Prueba) colectiva[j]).getId() == subelecc) {
				/// El valor introducido es alguno de los idPrueba colectivas
				pruebaSelecc = colectiva[j];
				valido = true;
				break;
			}
		}
		if (!valido) {
			System.out.println("El valor " + subelecc
					+ " no es válido. Se le mostrarán de nuevo las pruebas colectivas:");
			for (Prueba aux : colectiva) {
				if (aux != null) {
					System.out.println("" + aux);
				}
			}
		} else {
			System.out.println("Se ha elegido la prueba de id:" + subelecc + ". ¿Es correcto?");
			if (valido = Utilidades.leerBoolean()) {
				break; /// confirmacion de idPrueba seleccionado correcto
			} else {
				System.out.println("Se le mostrarán de nuevo las pruebas individuales:");
				for (Prueba aux : colectiva) {
					if (aux != null) {
						System.out.println("" + aux);
					}
				}
			}
		}
	} while (!valido);
	/// Ahora se crea el fichero con la inscripcion
	valido = false;
	String path = "inscrip_" + pruebaSelecc.getId() + "_" + nuevo.getPersona().getNifnie().mostrar() + ".dat";
	try {
		FileOutputStream ficheroInscrip = new FileOutputStream(path, false);
		ObjectOutputStream escritor = new ObjectOutputStream(ficheroInscrip);
		escritor.writeObject((Equipo) nuevo);
		escritor.writeObject((Long) pruebaSelecc.getId());
		LocalDateTime ahora = LocalDateTime.now();
		escritor.writeObject((LocalDateTime) ahora);
		escritor.flush();
		valido = true;
		escritor.close();
	} catch (FileNotFoundException e) {
		System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
	} catch (IOException e) {
		System.out.println("Se ha producido una IOException" + e.getMessage());
	} catch (Exception e) {
		System.out.println("Se ha producido una Exception" + e.getMessage());
	}
	/// Si el fichero se creó exitosamente, se lee su contenido y se muestra el
	/// mensaje
	if (!valido) {
		System.out.println("ERROR: No se creó el fichero con la inscripcion.");
	} else {
		try {
			File ficheroLeido = new File(path);
			FileInputStream ficheroInscrip = new FileInputStream(ficheroLeido);
			ObjectInputStream lectorFichInsc = new ObjectInputStream(ficheroInscrip);
			Atleta atletaLeido = (Atleta) lectorFichInsc.readObject();
			Long idPruebaLeido = (Long) lectorFichInsc.readObject();
			LocalDateTime fechahoraLeida = (LocalDateTime) lectorFichInsc.readObject();
			System.out.println("Se ha creado el fichero " + path + " a "
					+ fechahoraLeida.format(DateTimeFormatter.ofPattern("dd/MM/YY hh:mm:ss"))
					+ ", en el que el atleta " + atletaLeido.getId() + " de nombre "
					+ atletaLeido.getPersona().getNombre() + " y NIF/NIE "
					+ atletaLeido.getPersona().getNifnie().mostrar() + " queda" + "inscrito en la prueba "
					+ idPruebaLeido + " de nombre " + pruebaSelecc.getNombre() + " a celebrar en "
					+ pruebaSelecc.getLugar().getNombre() + " el día "
					+ pruebaSelecc.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ".");
			valido = true;
			lectorFichInsc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
	}

}

	
	
	
	private DatosPersona getPersona() {
		// TODO Auto-generated method stub
		return null;
	}

	//getters y setters
	public long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getAnioinscripcion() {
		return anioinscripcion;
	}

	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Atleta[] getAtletas() {
		return atletas;
	}

	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}
	



}
