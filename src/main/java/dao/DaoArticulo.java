package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.DBConection;
import modelo.Articulo;

public class DaoArticulo {

	private Connection conn = null;

	public static DaoArticulo instance = null;

	public DaoArticulo() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoArticulo getInstance() throws SQLException {
		if (instance == null) 
			instance = new DaoArticulo();
		
		return instance;

	}

	// SENTENCIAS SQL:

	private static final String SQL_SELECT = "SELECT * from articulo";
	private static final String SQL_INSERT = "INSERT INTO articulo (nombreArticulo, precioArticulo, marca, modelo,cantidad, estadoConservacion,descripcion, idSubcategoria) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE articulo SET nombreArticulo=?, precioArticulo=?, marca=?, modelo=?,cantidad=?, estadoConservacion=?, descripcion=?, idSubcategoria=? WHERE idArticulo=?";
	private static final String SQL_DELETE = "DELETE from articulo WHERE idArticulo=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM articulo WHERE idArticulo = ?";

	// Sentencias SQL con filtros:
	private static final String SQL_SELECT_BY_MODELO = "SELECT * from articulo WHERE modelo=?";
	private static final String SQL_SELECT_BY_MARCA = "SELECT * from articulo WHERE marca=?";
	private static final String SQL_SELECT_BY_ESTADO = "SELECT * from articulo WHERE estadoConservacion=?";
	private static final String SQL_SELECT_BY_SUBCATEGORIA = "SELECT * from articulo WHERE idSubcategoria=?";
	private static final String SQL_SELECT_BICICLETAS ="SELECT * from articulo WHERE idSubcategoria BETWEEN 1 AND 5 ORDER BY precioArticulo DESC";
	private static final String SQL_SELECT_COMPONENTES ="SELECT * from articulo WHERE idSubcategoria BETWEEN 6 AND 15 ORDER BY precioArticulo DESC";
	private static final String SQL_SELECT_ELECTRONICA ="SELECT * from articulo WHERE idSubcategoria BETWEEN 16 AND 19 ORDER BY precioArticulo DESC";

	// INSERTAR ARTICULO EN BASE DE DATOS:

	public int daoInsertar(Articulo articulo) throws SQLException {
		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setString(1, articulo.getNombreArticulo());
		ps.setDouble(2, articulo.getPrecioArticulo());
		ps.setString(3, articulo.getMarca());
		ps.setString(4, articulo.getModelo());
		ps.setInt(5, articulo.getCantidad());
		ps.setString(6, articulo.getEstadoConservacion());
		ps.setString(7, articulo.getDescripcion());
		ps.setInt(8, articulo.getIdSubcategoria());

		filas = ps.executeUpdate();
		ps.close();

		return filas;

	}

	// EDITAR ARTICULO EN BASE DE DATOS:

	public int daoUpdate(Articulo articulo) throws SQLException {
		int filas = 0;
		if (articulo.getIdArticulo() == 0) {
			return filas;
		} else {

			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
			ps.setString(1, articulo.getNombreArticulo());
			ps.setDouble(2, articulo.getPrecioArticulo());
			ps.setString(3, articulo.getMarca());
			ps.setString(4, articulo.getModelo());
			ps.setInt(5, articulo.getCantidad());
			ps.setString(6, articulo.getEstadoConservacion());
			ps.setString(7, articulo.getDescripcion());
			ps.setInt(8, articulo.getIdSubcategoria());

			ps.setInt(9, articulo.getIdArticulo());

			filas = ps.executeUpdate();
			ps.close();

		}
		return filas;
	}

	// BORRAR ARTICULO:

	public void delete(Articulo art) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		ps.setInt(1, art.getIdArticulo());
		ps.executeUpdate();
		ps.close();
	}

	// LISTAR ARTICULOS:

	public ArrayList<Articulo> listaArticulos() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();

		ArrayList<Articulo> result = null;
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();

			result.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
					rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
					rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
					rs.getInt("idSubcategoria")));

		}

		ps.close();
		rs.close();
		return result;

	}

	// BUSCAR ARTICULO:

	public Articulo buscarId(int idArticulo) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setInt(1, idArticulo);
		ResultSet rs = ps.executeQuery();
		Articulo result = null;
		if (rs.next()) {
			result = new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
					rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
					rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
					rs.getInt("idSubcategoria"));
		}
		rs.close();
		ps.close();
		return result;
	}

	// BUSCAR CON FILTRO:

	public ArrayList<Articulo> filtroPorModelo(String filtro) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_MODELO);
		ps.setString(1, filtro);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorModelo = null;
		while (rs.next()) {
			if (listadoPorModelo == null) 
				listadoPorModelo = new ArrayList<>();
				listadoPorModelo.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			ps.close();
			rs.close();

		
		return listadoPorModelo;

	}

	public ArrayList<Articulo> filtroPorMarca(String filtro) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_MARCA);
		ps.setString(1, filtro);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorMarca = null;
		while (rs.next()) {
			if (listadoPorMarca == null) 
				listadoPorMarca = new ArrayList<>();
				listadoPorMarca.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			for (int i = 0; i < listadoPorMarca.size(); i++) {
				;
				System.out.println(listadoPorMarca.get(i).getMarca());
			}
			rs.close();
			ps.close();

		
		return listadoPorMarca;

	}

	public ArrayList<Articulo> filtroPorEstado(String filtro) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ESTADO);
		ps.setString(1, filtro);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorModelo = null;
		while (rs.next()) {
			if (listadoPorModelo == null) 
				listadoPorModelo = new ArrayList<>();
				listadoPorModelo.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			rs.close();
			ps.close();

		
		return listadoPorModelo;

	}

	public ArrayList<Articulo> filtroPorSubcategoria(int filtro) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_SUBCATEGORIA);
		ps.setInt(1, filtro);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorModelo = null;
		while (rs.next()) {
			if (listadoPorModelo == null) 
				listadoPorModelo = new ArrayList<>();
				listadoPorModelo.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			ps.close();
			rs.close();
		
		return listadoPorModelo;

	}
	
	public ArrayList<Articulo> filtroBicicletas() throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BICICLETAS);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorModelo = null;
		while (rs.next()) {
			if (listadoPorModelo == null) 
				listadoPorModelo = new ArrayList<>();
				listadoPorModelo.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			ps.close();
			rs.close();
		
		return listadoPorModelo;

	}
	public ArrayList<Articulo> filtroComponentes() throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_COMPONENTES);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorModelo = null;
		while (rs.next()) {
			if (listadoPorModelo == null) 
				listadoPorModelo = new ArrayList<>();
				listadoPorModelo.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			ps.close();
			rs.close();
		
		return listadoPorModelo;

	}
	public ArrayList<Articulo> filtroElectronica() throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ELECTRONICA);
		ResultSet rs = ps.executeQuery();
		ArrayList<Articulo> listadoPorModelo = null;
		while (rs.next()) {
			if (listadoPorModelo == null) 
				listadoPorModelo = new ArrayList<>();
				listadoPorModelo.add(new Articulo(rs.getInt("idArticulo"), rs.getString("nombreArticulo"),
						rs.getDouble("precioArticulo"), rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("cantidad"), rs.getString("estadoConservacion"), rs.getString("descripcion"),
						rs.getInt("idSubcategoria")));
			}
			ps.close();
			rs.close();
		
		return listadoPorModelo;

	}
}
