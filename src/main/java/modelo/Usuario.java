package modelo;

import java.sql.SQLException;

import dao.DaoUsuario;



public class Usuario {

	private int idUsuario;
	private String nombre;
	private String apellidos;
	private String nick;
	private String password;
	private String email;
	private String ciudad;
	private String telefono;
	private int tipoUsuario;
	private String direccionEnvio;
	private int codigoPostal;

	// Constructores
	// Vacio
	public Usuario() {
	}
	
	
	//Todos los atributos:
	public Usuario(int idUsuario, String nombre, String apellidos, String nick, String password, String email,
			String ciudad, String telefono, int tipoUsuario, String direccionEnvio, int codigoPostal) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nick = nick;
		this.password = password;
		this.email = email;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
		this.direccionEnvio = direccionEnvio;
		this.codigoPostal = codigoPostal;
	}



	// Con todos los atributos menos idUsuario:
	public Usuario(String nombre, String apellidos, String nick, String password, String email, String ciudad,
			String telefono, int tipoUsuario, String direccionEnvio, int codigoPostal) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nick = nick;
		this.password = password;
		this.email = email;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
		this.direccionEnvio = direccionEnvio;
		this.codigoPostal = codigoPostal;
	}

	// Con los atributos no null de la base de datos
	public Usuario(int idUsuario, String nombre, String apellidos, String nick, String password, String email,
			int tipoUsuario) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nick = nick;
		this.password = password;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
	}

	// solo con el id para busquedas:
	public Usuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	// INSERTAR USUARIO:

	public void insertar() throws SQLException {
		DaoUsuario.getInstance().daoInsertarUsuario(this);
	}
	// EDITAR USUARIO
	public void editar() throws SQLException {
		DaoUsuario.getInstance().daoEditarUsuario(this);
	}
	
	// OBTENER USUARIO:
	public void obtenerUsuario(int idUsuario) throws SQLException {
		Usuario usu = DaoUsuario.getInstance().daoObtenerUsuario(idUsuario);
		this.idUsuario = usu.getIdUsuario();
		this.nombre = usu.getNombre();
		this.apellidos =usu.getApellidos();
		this.nick = usu.getNick();
		this.password = usu.getPassword();
		this.email = usu.getEmail();
		this.ciudad = usu.getCiudad();
		this.telefono = usu.getTelefono();
		this.tipoUsuario = usu.getTipoUsuario();
		this.direccionEnvio = usu.getDireccionEnvio();
		this.codigoPostal = usu.getCodigoPostal();
	}
	
	public void borrar(int idUsuario) {
		
		try {
			DaoUsuario.getInstance().daoBorrarUsuario(idUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Comprobacion si password y usuario son correctos:
	public int comprobarLoguin(String nombreInsertado, String passInsertado) throws SQLException {
		int idUsuario=0;
		idUsuario = DaoUsuario.getInstance().daoUsuarioValido(nombreInsertado, passInsertado);
		return idUsuario;
	}
	
	
}
