package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.DBConection;
import modelo.Articulo;
import modelo.Imagen;

public class DaoImagen {

	private Connection conn = null;

	public static DaoImagen instance = null;

	public DaoImagen() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoImagen getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoImagen();
		}
		return instance;

	}

//SENTENCIAS SQL:

	private static final String SQL_INSERT = "INSERT INTO imagen (idArticulo, ruta, nombreImagen) VALUES (?,?,?)";
	private static final String SQL_SELECT = "SELECT * from imagen";
	private static final String SQL_UPDATE = "UPDATE imagen SET idArticulo=?, ruta=?, nombreImagen=?  WHERE idImagen=?";
	private static final String SQL_DELETE = "DELETE FROM imagen WHERE idImagen=?";
	private static final String SQL_SELECT_POR_ID = "SELECT * from imagen WHERE idImagen=?";
	private static final String SQL_SELECT_BY_ARTICULO = "SELECT * from imagen WHERE idArticulo=?";

	// INSERTAR UNA IMAGEN EN BASE DE DATOS:

	public int daoInsertar(Imagen img) throws SQLException {

		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setInt(1, img.getIdArticulo());
		ps.setString(2, img.getRuta());
		ps.setString(3, img.getNombreImagen());
		filas = ps.executeUpdate();

		ps.close();
		return filas;

	}

	// ACTUALIZAR IMAGEN:

	public int daoUpdate(Imagen img) throws SQLException {

		int filas = 0;
		if (img.getIdImagen() == 0) {
			return filas;
		} else {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
			ps.setInt(1, img.getIdArticulo());
			ps.setString(2, img.getRuta());
			ps.setString(3, img.getNombreImagen());
			
			ps.setInt(4, img.getIdImagen());

			filas = ps.executeUpdate();
			ps.close();
		}
		return filas;

	}

	// BORRAR IMAGEN:

	public int daoDelete(Imagen img) throws SQLException {
		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		ps.setInt(1, img.getIdImagen());

		filas = ps.executeUpdate();
		ps.close();
		return filas;

	}

	// LISTAR IMAGENES:

	public ArrayList<Imagen> daoListadoImg() throws SQLException {

		PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();

		ArrayList<Imagen> listadoImg = null;
		while (rs.next()) {
			if (listadoImg == null)
				listadoImg = new ArrayList<>();
			listadoImg.add(new Imagen(rs.getInt("idImagen"), rs.getInt("idArticulo"),rs.getString("ruta"), rs.getString("nombreImagen")));
		}
		rs.close();
		ps.close();
		return listadoImg;

	}

	// LISTAR IMAGENES POR IDARTICULO:

	public ArrayList<Imagen> daoListarPorArt(int idArticulo) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ARTICULO);
		ps.setInt(1, idArticulo);

		ResultSet rs = ps.executeQuery();

		ArrayList<Imagen> listado = null;
		while (rs.next()) {
			if (listado == null)
				listado = new ArrayList<>();
			listado.add(new Imagen(rs.getInt("idImagen"), rs.getInt("idArticulo"),rs.getString("ruta"), rs.getString("nombreImagen")));
		}
		rs.close();
		ps.close();
		return listado;

	}
	
	//LISTAR IMAGENES POR IDIMAGEN:
	
	public Imagen daoListarPorIdImagen(int idImagen) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_POR_ID);
		ps.setInt(1,idImagen);

		ResultSet rs = ps.executeQuery();
		Imagen result=null;
		if(rs.next()) {
			result = new Imagen(rs.getInt("idImagen"), rs.getInt("idArticulo"),
					rs.getString("ruta"), rs.getString("nombreImagen"));
		}
		rs.close();
		ps.close();
		return result;

}
	
	
}
