package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.DBConection;
import modelo.Categoria;

public class DaoCategoria {

	private Connection conn = null;

	public static DaoCategoria instance = null;

	public DaoCategoria() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoCategoria getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoCategoria();
		}
		return instance;

	}

// SENTENCIAS SQL:
	private static final String SQL_SELECT = "SELECT * FROM subcategoria";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM subcategoria WHERE idSubcategoria = ?";
	private static final String SQL_INSERT = "INSERT INTO subcategoria (nombreCategoria) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE subcategoria SET nombreCategoria=? WHERE idSubcategoria=?";
	private static final String SQL_DELETE = "DELETE FROM subcategoria WHERE idSubcategoria=?";
	private static final String SQL_SELECT_BY_NOMBRE = "SELECT * FROM subcategoria WHERE nombreCategoria = ?";

// METODOS CRUD:

	// Insertar:

	public int daoInsertarSubcategoria(Categoria cat) throws SQLException {
		int filas = 0;

		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setString(1, cat.getNombreCategoria());

		filas = ps.executeUpdate();
		ps.close();

		return filas;

	}
	//Listar categorias:
	public ArrayList<Categoria> listarCategorias() throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();

		ArrayList<Categoria> result = null;
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();

			result.add(new Categoria(rs.getInt("idSubcategoria"), rs.getString("nombreCategoria")));

		}
		rs.close();
		ps.close();
		System.out.println("listacat dao:" + result);
		return result;
	}
	

	//OBtener categoria por el id
	public Categoria obtenerCat(int idCategoria) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setInt(1, idCategoria);
		ResultSet rs = ps.executeQuery();
		

		Categoria cat = null;
		if (rs.next()) {
			cat = new Categoria(rs.getInt("idSubcategoria"), rs.getString("nombreCategoria"));
			System.out.println(cat);
		}
		ps.close();
		rs.close();
		return cat;

	}
	//Borrar categoria:
	public void daoBorrarCategoria(Categoria cat) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		ps.setInt(1, cat.getIdSubcategoria());
		ps.executeUpdate();
		ps.close();
	}
	
	//Editar categoria:
	public int daoEditarCategoria(Categoria cat) throws SQLException {
		int filas = 0;
		if (cat.getIdSubcategoria() == 0) {
			return filas;
		} else {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
			ps.setString(1, cat.getNombreCategoria());
			ps.setInt(2, cat.getIdSubcategoria());

			filas = ps.executeUpdate();
			ps.close();
		}
		return filas;
	}
	//Buscar categoria por el id:
	public boolean  buscarCatPorIdCategoria(int idSubcategoria) throws SQLException {
		boolean encontrado = false;
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setInt(1,idSubcategoria);
		ResultSet rs = ps.executeQuery();
		
		Categoria cat = null;
		if(rs.next()) {
			cat = new Categoria(rs.getInt("idSubcategoria"), rs.getString("nombreCategoria"));
			encontrado = true;
		}
		ps.close();
		rs.close();
		return encontrado;
	}
	//Metodo para validar formulario:
	public boolean buscarCatPorNombre(String categoria) throws SQLException {
		boolean encontrado = false;
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_NOMBRE);
		ps.setString(1,categoria);
		ResultSet rs = ps.executeQuery();
		
		Categoria cat = null;
		if(rs.next()) {
			cat = new Categoria(rs.getInt("idSubcategoria"), rs.getString("nombreCategoria"));
			encontrado = true;
		}
		ps.close();
		rs.close();
		return encontrado;
	}

	
}
