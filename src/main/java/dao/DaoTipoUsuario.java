package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.DBConection;
import modelo.TipoUsuario;

public class DaoTipoUsuario {
	private Connection conn = null;

	public static DaoTipoUsuario instance = null;

	public DaoTipoUsuario() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoTipoUsuario getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoTipoUsuario();
		}
		return instance;

	}

// SENTENCIAS SQL:

	private static final String SQL_SELECT = "SELECT * FROM tipousuario2";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM tipousuario2 WHERE idTipoUsuario=?";
	private static final String SQL_INSERT = "INSERT INTO tipousuario2(nombreTipoUsuario) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE tipousuario2 SET nombreTipoUsuario=? WHERE idTipoUsuario=?";
	private static final String SQL_DELETE = "DELETE FROM tipousuario2 WHERE idTipoUsuario=?";

//METODOS CRUD:

	// Insertar:
	public int daoInsertarTipo(TipoUsuario tp) throws SQLException {
		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setString(1, tp.getNombreTipoUsuario());

		filas = ps.executeUpdate();
		ps.close();
		return filas;
	}

	// Listar
	public ArrayList<TipoUsuario> daoListarTipoUsuario() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();

		ArrayList<TipoUsuario> result = null;
		while (rs.next()) {
			if (result == null)
			result = new ArrayList<>();
			result.add(new TipoUsuario(rs.getInt("idTipoUsuario"), rs.getString("nombreTipoUsuario")));
		}
		rs.close();
		ps.close();
		return result;

	}

	// Obtener Tipo de usuario a partir del id:
	public TipoUsuario daoObtenerTipo(int idTipoUsuario) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setInt(1, idTipoUsuario);
		ResultSet rs = ps.executeQuery();

		TipoUsuario tp = null;
		if (rs.next()) {
			tp = new TipoUsuario(rs.getInt("idTipoUsuario"), rs.getString("nombreTipoUsuario"));
			System.out.println("Tipo usuario obtenido:" + tp.getIdTipoUsuario() + " / " + tp.getNombreTipoUsuario());

		}
		rs.close();
		ps.close();
		return tp;
	}

	// Borrar Tipo de usuario:
	public void daoBorrarTipo(TipoUsuario tp) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		ps.setInt(1, tp.getIdTipoUsuario());
		ps.executeUpdate();
		ps.close();

	}

	// Editar Tipo de Usuario:
	public int daoEditarTipo(TipoUsuario tp) throws SQLException {
		int filas = 0;
		if (tp.getIdTipoUsuario() == 0) {
			return filas;
		} else {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
			ps.setString(1, tp.getNombreTipoUsuario());
			ps.setInt(2, tp.getIdTipoUsuario());

			filas = ps.executeUpdate();
			ps.close();
		}
		return filas;
	}
	
	//PARA VALIDAR FORMULARIO DE EDICION Y CREACION DE TIPOS DE ROL DE USUARIO:
	public boolean buscaridTipo(int idTipoUsuario) throws SQLException {
		boolean encontrado = false;
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setInt(1, idTipoUsuario);
		ResultSet rs = ps.executeQuery();

		TipoUsuario tp = null;
		if (rs.next()) {
			tp = new TipoUsuario(rs.getInt("idTipoUsuario"), rs.getString("nombreTipoUsuario"));
			System.out.println("Tipo usuario obtenido:" + tp.getIdTipoUsuario() + " / " + tp.getNombreTipoUsuario());
			encontrado = true;
		}
		rs.close();
		ps.close();
		return encontrado;
	}
	public boolean buscarNombreTipo(String idTipoUsuario) throws SQLException {
		boolean encontrado = false;
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setString(1, idTipoUsuario);
		ResultSet rs = ps.executeQuery();

		TipoUsuario tp = null;
		if (rs.next()) {
			tp = new TipoUsuario(rs.getInt("idTipoUsuario"), rs.getString("nombreTipoUsuario"));
			System.out.println("Tipo usuario obtenido:" + tp.getIdTipoUsuario() + " / " + tp.getNombreTipoUsuario());
			encontrado = true;
		}
		rs.close();
		ps.close();
		return encontrado;
	}
	
}
