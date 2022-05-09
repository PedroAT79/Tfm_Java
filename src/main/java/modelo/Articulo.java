package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoArticulo;

public class Articulo {
	
	private int idArticulo;
	private  String nombreArticulo;
	private double precioArticulo;
	private String marca;
	private String modelo;
	private int cantidad;
	private String estadoConservacion;
	private String descripcion;
	private int idSubcategoria;
	
	
	
	//Constructores:
		public Articulo() {
	}
		
	
	
	public Articulo(int idArticulo, String nombreArticulo, double precioArticulo, String marca, String modelo,
			int cantidad, String estadoConservacion, String descripcion, int idSubcategoria) {
		this.idArticulo = idArticulo;
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
		this.marca = marca;
		this.modelo = modelo;
		this.cantidad = cantidad;
		this.estadoConservacion = estadoConservacion;
		this.descripcion = descripcion;
		this.idSubcategoria = idSubcategoria;
		
	}



	public Articulo(String nombreArticulo, double precioArticulo, String marca, String modelo, int cantidad,
			String estadoConservacion, int idSubcategoria, String descripcion) {
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
		this.marca = marca;
		this.modelo = modelo;
		this.cantidad = cantidad;
		this.estadoConservacion = estadoConservacion;
		this.idSubcategoria = idSubcategoria;
		this.descripcion = descripcion;
		
		
	}

	public Articulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	
	
	public Articulo(int idArticulo, String nombreArticulo, double precioArticulo, String marca, int cantidad,
			String estadoConservacion, int idSubcategoria) {
		this.idArticulo = idArticulo;
		this.nombreArticulo = nombreArticulo;
		this.precioArticulo = precioArticulo;
		this.marca = marca;
		this.cantidad = cantidad;
		this.estadoConservacion = estadoConservacion;
		this.idSubcategoria = idSubcategoria;
	
	}

	//GETTER Y SETTER:
	
	public int getIdArticulo() {
		return idArticulo;
	}


	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
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


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getEstadoConservacion() {
		return estadoConservacion;
	}


	public void setEstadoConservacion(String estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}


	public int getIdSubcategoria() {
		return idSubcategoria;
	}


	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	@Override
	public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", nombreArticulo=" + nombreArticulo + ", precioArticulo="
				+ precioArticulo + ", marca=" + marca + ", modelo=" + modelo + ", cantidad=" + cantidad
				+ ", estadoConservacion=" + estadoConservacion + ", idSubcategoria=" + idSubcategoria + ", descripcion="
				+ descripcion+"]";
	}
	
	
	//METODOS CRUD:
	
	public void insertar() {
		
		try {
			DaoArticulo.getInstance().daoInsertar(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void editar() {
		try {
			DaoArticulo.getInstance().daoUpdate(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void borrarArticulo() throws SQLException {
		
		DaoArticulo.getInstance().delete(this);
	}
	
	public void obtenerArticulo(int idArticulo) throws SQLException {
		Articulo art = DaoArticulo.getInstance().buscarId(idArticulo);
		this.idArticulo = art.getIdArticulo();
		this.nombreArticulo = art.getNombreArticulo();
		this.cantidad = art.getCantidad();
		this.descripcion = art.getDescripcion();
		this.estadoConservacion = art.getEstadoConservacion();
		this.idSubcategoria = art.getIdSubcategoria();
		this.marca = art.getMarca();
		this.modelo = art.getModelo();
		this.precioArticulo = art.getPrecioArticulo();
		
	}
	

	
	
	
	
	
	

}
