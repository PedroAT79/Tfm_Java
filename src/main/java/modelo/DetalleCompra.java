package modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.DaoDetalleCompra;
import dao.DaoOrdenDeCompra;

public class DetalleCompra {

	private int idDetalle;
	private int idArticulo;
	private int idOrden;
	private int cantidad;
	private double precioCompra;
	private String nombreArticulo;
	private double precioArticulo;

	// Constructores:

	public DetalleCompra() {
	}

	public DetalleCompra(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public DetalleCompra(int idDetalle, int idArticulo, int idOrden, int cantidad, double precioCompra,
			String nombreArticulo, double precioArticulo) {
		this.idDetalle = idDetalle;
		this.idArticulo = idArticulo;
		this.idOrden = idOrden;
		this.cantidad = cantidad;
		this.precioCompra = precioCompra;
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
	}

	public DetalleCompra(int idArticulo, int idOrden, int cantidad, double precioCompra, String nombreArticulo,
			double precioArticulo) {
		this.idArticulo = idArticulo;
		this.idOrden = idOrden;
		this.cantidad = cantidad;
		this.precioCompra = precioCompra;
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
	}

	// Getter y setter:
	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public double getPrecioArticulo() {
		return precioArticulo;
	}

	public void setPrecioArticulo(double precioArticulo) {
		this.precioArticulo = precioArticulo;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public void obtenerDetalleCompra(int idDetalle) throws SQLException {
		DetalleCompra dc = DaoDetalleCompra.getInstance().daoObtenerDetalle(idDetalle);
		this.idDetalle = dc.getIdDetalle();
		this.idArticulo = dc.getIdArticulo();
		this.idOrden = dc.getIdOrden();
		this.cantidad = dc.getCantidad();
		this.precioArticulo = dc.getPrecioArticulo();
		this.nombreArticulo = dc.getNombreArticulo();
		this.precioCompra = dc.getPrecioCompra();

	}
	// Borrar detalle de compra:

	public void eliminarDetalle(int idDetalle) throws SQLException {

		DaoDetalleCompra.getInstance().delete(this);

	}

	public void editar() throws SQLException {
		DaoDetalleCompra.getInstance().daoEditarDetalleCompra(this);

	}
}
