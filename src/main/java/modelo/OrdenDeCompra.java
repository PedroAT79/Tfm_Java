package modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.DaoOrdenDeCompra;

public class OrdenDeCompra {

	private int idOrden;
	private String nombreOrden;
	private LocalDate fechaCompra;
	private int idUsuario;
	private double montoTotal;
	private String estadoPago;
	private ArrayList<DetalleCompra> listaDetalle;
	DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// Constructor vacio:
	public OrdenDeCompra() {
	}

	public OrdenDeCompra(int idOrden) {
		this.idOrden = idOrden;
	}

	public OrdenDeCompra(String nombreOrden, LocalDate fechaCompra, int idUsuario, double montoTotal, String estadoPago,
			ArrayList<DetalleCompra> listaArticulos) {
		this.nombreOrden = nombreOrden;
		this.fechaCompra = fechaCompra;
		this.idUsuario = idUsuario;
		this.montoTotal = montoTotal;
		this.estadoPago = estadoPago;
		this.listaDetalle = listaArticulos;

	}

	public OrdenDeCompra(int idOrden, String nombreOrden, LocalDate fechaCompra, int idUsuario, double montoTotal,
			String estadoPago) {

		this.idOrden = idOrden;
		this.nombreOrden = nombreOrden;
		this.fechaCompra = fechaCompra;
		this.idUsuario = idUsuario;
		this.montoTotal = montoTotal;
		this.estadoPago = estadoPago;

	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public String getNombreOrden() {
		return nombreOrden;
	}

	public void setNombreOrden(String nombreOrden) {
		this.nombreOrden = nombreOrden;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public List<DetalleCompra> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(ArrayList<DetalleCompra> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	// Crear Orden de compra:

	public void insertarCompra() throws SQLException {
		DaoOrdenDeCompra.getInstance().daoCrearCompra(this);

	}

	// Para editar el estado de la orden de compra, cuando compra se pone por
	// defecto en "pendiente", y posteriormente el
	// administrado la cambia segun el estado en que se encuentre (en tramite o
	// cerrada).
	public void editarOrden() throws SQLException {
		DaoOrdenDeCompra.getInstance().daoEditarOrden(this);
	}
	//Metodo para obtener los valores de los atributos de una orden de compra en concreto.
	public void obtenerOrdenCompra(int idOrden) throws SQLException {
		OrdenDeCompra oc = DaoOrdenDeCompra.getInstance().obtenerOrdenDeCompra(idOrden);
		this.idOrden = oc.getIdOrden();
		this.nombreOrden = oc.getNombreOrden();
		this.fechaCompra = oc.getFechaCompra();
		this.idUsuario = oc.getIdUsuario();
		this.montoTotal = oc.getMontoTotal();
		this.estadoPago = oc.getEstadoPago();

	}


	//Metodo para obtener las ordenes de compra de mas o menos de 24 horas.
	public static boolean ordenesMas24(int idOrden) throws SQLException {
		boolean respuesta = false;
		OrdenDeCompra oc = new OrdenDeCompra(idOrden);
		oc.obtenerOrdenCompra(idOrden);
		if(oc.getFechaCompra().isBefore(LocalDate.now().minusDays(1))) {
			respuesta = true;
			
		}else {
			respuesta = false;
			
		}
		return respuesta;
		
	}

}
