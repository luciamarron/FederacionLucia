package entidades;

import java.time.format.DateTimeFormatter;

public class Palmares <S extends Participante, T extends Metal> {
	
	private long idPalmares;
	private T medalla;
	private S participante;
	private Prueba prueba;
	private String observaciones;
	
	
	public Palmares (long idPalmares, T medalla, S participante, Prueba prueba, String observaciones){
		this.idPalmares = idPalmares;
		this.medalla = medalla;
		this.participante = participante;
		this.prueba = prueba;
		this.observaciones = observaciones;
		
	}
	

	public long getIdPalmares() {
		return idPalmares;
	}


	public void setIdPalmares(long idPalmares) {
		this.idPalmares = idPalmares;
	}


	public T getMedalla() {
		return medalla;
	}


	public void setMedalla(T medalla) {
		this.medalla = medalla;
	}


	public S getParticipante() {
		return participante;
	}


	public void setParticipante(S participante) {
		this.participante = participante;
	}


	public Prueba getPrueba() {
		return prueba;
	}


	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public String mostrarPalmares() {
		String ret="";
		
		ret+= this.idPalmares + "," + this.getMedalla() + ", " +this.getPrueba().getNombre() + "," +this.getPrueba().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ", " +this.getPrueba().getLugar(); 
		ret += "," + this.getParticipante().getDorsal() + "," + this.getParticipante().getCalle();
		return ret;
		
		//para el último apartado habrá que hacer un if para comprobar si el participante es atleta o equipo y en
		//función de eso que retorne un dato u otro
	
	}
		
	
	

}
