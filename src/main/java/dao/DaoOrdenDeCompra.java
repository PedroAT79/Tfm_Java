package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import controlador.DBConection;
import modelo.Categoria;
import modelo.DetalleCompra;
import modelo.OrdenDeCompra;
import modelo.Usuario;

public class DaoOrdenDeCompra {

	private Connection conn = null;

	public static DaoOrdenDeCompra instance = null;

	public DaoOrdenDeCompra() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoOrdenDeCompra getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoOrdenDeCompra();
		}
		return instance;

	}

	// Sentencias SQL:

	private static String SQL_INSERT = "INSERT INTO ordencompra (nombreOrden, fechaCompra, idUsuario, estadoPago, montoTotal) VALUES (?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FORM ordencompra WHERE idOrden=?";
	private static final String SQL_UPDATE = "UPDATE ordencompra SET estadoPago=?, montoTotal=? WHERE idOrden=?";
	private static final String SQL_SELECT = "SELECT * FROM ordencompra";
	private static final String SQL_SELECT_BY_USUARIO = "SELECT * FROM ordencompra WHERE idUsuario=?";
	private static final String SQL_SELECT_DETALLE_COMPRA_BY_IDORDEN = "SELECT * FROM ordencompra WHERE idOrden=?";
	private static final String SQL_SELECT_BY_ESTADO_PAGO = "SELECT * FROM ordencompra WHERE estadoPago=?";
	
	
	// METODOS CRUD:
	// Insertar Orden de Compra:

	public int daoCrearCompra(OrdenDeCompra oc) throws SQLException {
		int idOrden;
		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setString(1, oc.getNombreOrden());
		ps.setDate(2, Date.valueOf(oc.getFechaCompra()));
		ps.setInt(3, oc.getIdUsuario());
		ps.setString(4, oc.getEstadoPago());
		ps.setDouble(5, oc.getMontoTotal());
		
		filas = ps.executeUpdate();

		SQL_INSERT = "SELECT @@IDENTITY AS idOrden";
		ResultSet rs = ps.executeQuery(SQL_INSERT);
		rs.next();
		idOrden = rs.getInt("idOrden");
		
		
		for (DetalleCompra detalle : oc.getListaDetalle()) {
			SQL_INSERT = "INSERT INTO detallecompra (idArticulo,idOrden,cantidad,precioCompra, nombreArticulo, precioArticulo) VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setInt(1, detalle.getIdArticulo());
			ps.setInt(2, idOrden);
			ps.setInt(3, detalle.getCantidad());
			ps.setDouble(4, detalle.getPrecioCompra());
			ps.setString(5, detalle.getNombreArticulo());
			ps.setDouble(6, detalle.getPrecioArticulo());
			System.out.println("comprobacion parametros: "+ detalle.getCantidad()+ "/"
			+detalle.getIdArticulo()+ "/"
					+detalle.getIdDetalle()+ "/"
			+detalle.getIdOrden() + "/"
					+detalle.getNombreArticulo()+ "/"
			+detalle.getPrecioArticulo()+ "/"
					+detalle.getPrecioCompra()+ "/");
			filas = ps.executeUpdate();

		}
		rs.close();
		ps.close();
		return filas;

	}

	public ArrayList<OrdenDeCompra> daoListarTodasLasOrdenes() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();

		ArrayList<OrdenDeCompra> lista = null;
		while (rs.next()) {
			if (lista == null)
				lista = new ArrayList<>();
			lista.add(new OrdenDeCompra(rs.getInt("idOrden"), rs.getString("nombreOrden"),
					rs.getDate("fechaCompra").toLocalDate(), rs.getInt("idUsuario"), rs.getDouble("montoTotal"),
					rs.getString("estadoPago")));

		}
		rs.close();
		ps.close();

		return lista;

	}

	public ArrayList<OrdenDeCompra> daoFiltrarPedidosPorUsuario(int idUsuario) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_USUARIO);
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();

		ArrayList<OrdenDeCompra> lista = null;
		while (rs.next()) {
			if (lista == null)
				lista = new ArrayList<>();
			lista.add(new OrdenDeCompra(rs.getInt("idOrden"), rs.getString("nombreOrden"),
					rs.getDate("fechaCompra").toLocalDate(), rs.getInt("idUsuario"), rs.getDouble("montoTotal"),
					rs.getString("estadoPago")));

		}
		rs.close();
		ps.close();

		return lista;

	}

	public int daoEditarOrden(OrdenDeCompra oc) throws SQLException {
		int filas = 0;
		if (oc.getIdOrden() == 0) {
			return filas;
		} else {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
			ps.setString(1, oc.getEstadoPago());
			ps.setDouble(2, oc.getMontoTotal());
			ps.setInt(3, oc.getIdOrden());

			filas = ps.executeUpdate();
			ps.close();
		}
		return filas;
	}


	public OrdenDeCompra obtenerOrdenDeCompra(int idOrden) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_DETALLE_COMPRA_BY_IDORDEN);
		ps.setInt(1, idOrden);

		ResultSet rs = ps.executeQuery();

		OrdenDeCompra result = null;
		if (rs.next()) {
			result = new OrdenDeCompra(rs.getInt("idOrden"), rs.getString("nombreOrden"),
					rs.getDate("fechaCompra").toLocalDate(), rs.getInt("idUsuario"), rs.getDouble("montoTotal"),
					rs.getString("estadoPago"));
			System.out.println("detalle obtenido del dao:" + result);
		}
		rs.close();
		ps.close();
		return result;

	}
	
	public ArrayList<OrdenDeCompra> daoFiltrarPorEstadoPago(String estadoPago) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ESTADO_PAGO);
		ps.setString(1, estadoPago);
		ResultSet rs = ps.executeQuery();

		ArrayList<OrdenDeCompra> lista = null;
		while (rs.next()) {
			if (lista == null)
				lista = new ArrayList<>();
			lista.add(new OrdenDeCompra(rs.getInt("idOrden"), rs.getString("nombreOrden"),
					rs.getDate("fechaCompra").toLocalDate(), rs.getInt("idUsuario"), rs.getDouble("montoTotal"),
					rs.getString("estadoPago")));

		}
		rs.close();
		ps.close();

		return lista;

	}
	
}

