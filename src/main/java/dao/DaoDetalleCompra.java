package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.DBConection;
import modelo.Articulo;
import modelo.DetalleCompra;
import modelo.OrdenDeCompra;

public class DaoDetalleCompra {
	private Connection conn = null;

	public static DaoDetalleCompra instance = null;

	public DaoDetalleCompra() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoDetalleCompra getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoDetalleCompra();
		}
		return instance;

	}
	
	//SENTENCIA SQL
	private static final String SQL_SELECT_DETALLE_COMPRA_BY_IDDETALLE = "SELECT * FROM detallecompra WHERE idDetalle=?";
	private static final String SQL_SELECT_BY_ORDEN= "SELECT * FROM detallecompra WHERE idOrden=?";
	private static final String SQL_DELETE_BY_ID = "DELETE from detallecompra WHERE idDetalle=?";
	private static final String SQL_UPDATE = "UPDATE detallecompra SET nombreArticulo=?, precioCompra=? WHERE idDetalle=?";
	
	
	//metodo para sacar los detalles de cada orden de compra:
	public DetalleCompra daoObtenerDetalle(int idDetalle) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_DETALLE_COMPRA_BY_IDDETALLE);
		ps.setInt(1,idDetalle );
		
		ResultSet rs = ps.executeQuery();
		
		DetalleCompra result = null;
		if (rs.next()) {
			result = new DetalleCompra(rs.getInt("idDetalle"), rs.getInt("idArticulo"),rs.getInt("idOrden"),
					rs.getInt("cantidad"),rs.getDouble("precioCompra"),rs.getString("nombreArticulo"), rs.getDouble("precioArticulo"));
					System.out.println("detalle obtenido del dao:" + result);			
		}
		rs.close();
		ps.close();
		return result;
		
			
	}

	
	public ArrayList<DetalleCompra> daoListarDetalles(int idOrden) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ORDEN);
		ps.setInt(1,idOrden );
		ResultSet rs = ps.executeQuery();
		
		ArrayList<DetalleCompra> result = null;
		while(rs.next()) {
			if(result == null)
			result = new ArrayList<>();	
			result.add(new DetalleCompra(rs.getInt("idDetalle"),rs.getInt("idArticulo"),rs.getInt("idOrden"),
					rs.getInt("cantidad"),rs.getDouble("precioCompra"),rs.getString("nombreArticulo"), rs.getDouble("precioArticulo")));
					System.out.println("detalle obtenido del dao:" + result);			
		}
		rs.close();
		ps.close();
		return result;
		
			
	}
	
	// BORRAR detalle:

		public void delete(DetalleCompra dc) throws SQLException {
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_BY_ID);
			ps.setInt(1, dc.getIdDetalle());
			ps.executeUpdate();
			ps.close();
		}
	//EDITAR DETALLE_ cantidad:
		
		public int daoEditarDetalleCompra(DetalleCompra dc) throws SQLException {
			int filas = 0;
			if (dc.getIdDetalle() == 0) {
				return filas;
			} else {
				PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
				ps.setString(1, dc.getNombreArticulo());
				ps.setDouble(2,dc.getPrecioCompra());
				
				ps.setInt(3,dc.getIdDetalle());
				filas = ps.executeUpdate();
				ps.close();
			}
			return filas;
		}
		
	
	
	
}
