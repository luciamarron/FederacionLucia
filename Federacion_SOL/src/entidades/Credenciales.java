package entidades;

public class Credenciales {
	private String usuario;
	private String password;
	private Rol rol;
	

	public Credenciales() {
	}

	public Credenciales(String usuario, String password) {
		super();
		this.setUsuario(usuario);
		this.setPassword(password);
	}
	
	public Credenciales(String usuario, String password, Rol rol) {
		super();
		this.setUsuario(usuario);
		this.setPassword(password);
		this.rol = rol;
	}



	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String data() {
		String ret = "";
		ret = this.usuario + "|" +this.password +"|"+ Rol.values();
		return ret;
	}

}
