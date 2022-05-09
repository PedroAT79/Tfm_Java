package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCategoria;

public class Categoria {
	
	private int idSubcategoria;
	private String nombreCategoria;
	
	//Constructores
	public Categoria() {
	}

	public Categoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;;
	}

	public Categoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Categoria(int idSubcategoria, String nombreCategoria) {
		this.idSubcategoria = idSubcategoria;
		this.nombreCategoria = nombreCategoria;
	}
	
	
	//Getters y Setters:
	public int getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idSubcategoria + ", nombreCategoria=" + nombreCategoria + "]";
	}
	
	//METODOS CRUD:
	
	public void insertarCat() throws SQLException {
		
		DaoCategoria.getInstance().daoInsertarSubcategoria(this);
	}
	
		
	
	public void obtenerCategoria(int idSubcategoria) throws SQLException {
			
		Categoria cat = DaoCategoria.getInstance().obtenerCat(idSubcategoria);
		this.idSubcategoria = cat.getIdSubcategoria();
		this.nombreCategoria = cat.getNombreCategoria();
		
	}
	
	public void eliminarCategoria() throws SQLException {
		
		DaoCategoria.getInstance().daoBorrarCategoria(this);
				
	}
	
	public void editarCategoria() {
		try {
			DaoCategoria.getInstance().daoEditarCategoria(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean buscarCategoriaPorId(int idSubcategoria) throws SQLException {
		boolean encontrado = false;
		encontrado = DaoCategoria.getInstance().buscarCatPorIdCategoria(idSubcategoria);
		return encontrado;
		
	}
	
	public boolean buscarCatPorNombre(String categoria) throws SQLException {
		boolean encontrado = false;
		
		encontrado = DaoCategoria.getInstance().buscarCatPorNombre(categoria);
		return encontrado;
	}
	
	public boolean validarNumeros(String categoria) {
		return categoria.matches("[0-9]*");
	}
	
	
	
	
	
	
	
	
	

}
