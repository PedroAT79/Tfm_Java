package modelo;

import java.sql.SQLException;

import dao.DaoTipoUsuario;

public class TipoUsuario {
	
	private int idTipoUsuario;
	private String nombreTipoUsuario;
	
	//Constructores
	public TipoUsuario() {
	}


	public TipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}


	public TipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	public TipoUsuario(int idTipoUsuario, String nombreTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
		this.nombreTipoUsuario = nombreTipoUsuario;
	}

	//Setter y getter:
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}


	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}


	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}


	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}
	
	//metodos crud:
	
	public void insertarTp() throws SQLException {
		DaoTipoUsuario.getInstance().daoInsertarTipo(this);
	}
	
		
	public void obtenerTp(int idTipoUsuario) throws SQLException {
		TipoUsuario tp = DaoTipoUsuario.getInstance().daoObtenerTipo(idTipoUsuario);
		this.idTipoUsuario = tp.getIdTipoUsuario();
		this.nombreTipoUsuario = tp.getNombreTipoUsuario();
	}
	
	public void editarTp() throws SQLException {
		DaoTipoUsuario.getInstance().daoEditarTipo(this);
	}
	
	public void eliminarTp() throws SQLException {
		DaoTipoUsuario.getInstance().daoBorrarTipo(this);
	}
	
	public boolean buscarIdTipo(int idtipoUsuario) throws SQLException {
		
		boolean encontrado = false;
		encontrado = DaoTipoUsuario.getInstance().buscaridTipo(idtipoUsuario);
		System.out.println("id tipoencontrado: "+ encontrado);
		return encontrado;
	}
	
	public boolean buscarNombreTipo(String tipoUsuario) throws SQLException {
		
		boolean encontrado = false;
		encontrado = DaoTipoUsuario.getInstance().buscarNombreTipo(tipoUsuario);
		System.out.println("nombre tipoencontrado: "+encontrado);
		return encontrado;
	}
	
	public void obtenerTipoUsuario(int idTipoUsuario) throws SQLException {
		TipoUsuario tp = DaoTipoUsuario.getInstance().daoObtenerTipo(idTipoUsuario);
		this.idTipoUsuario = tp.getIdTipoUsuario();
		this.nombreTipoUsuario = tp.getNombreTipoUsuario();
		
		
	}
	
	
	
	
	

}
