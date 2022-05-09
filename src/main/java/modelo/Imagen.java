package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoImagen;

public class Imagen {

	private int idImagen;
	private int idArticulo;
	private String ruta;
	private String nombreImagen;

	// Contructores:

	public Imagen() {
	}

	public Imagen(int idArticulo, String ruta, String nombreImagen) {
		this.idArticulo = idArticulo;
		this.ruta = ruta;
		this.nombreImagen = nombreImagen;
	}

	public Imagen(int idImagen, int idArticulo, String ruta, String nombreImagen) {
		this.idImagen = idImagen;
		this.idArticulo = idArticulo;
		this.ruta = ruta;
		this.nombreImagen = nombreImagen;
	}

	public Imagen(int idImagen) {
		this.idImagen = idImagen;
	}

	// Setter y getters:

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	// to string:
	@Override
	public String toString() {
		return "Imagen [idImagen=" + idImagen + ", idArticulo=" + idArticulo + ", ruta=" + ruta + ", nombreImagen="
				+ nombreImagen + "]";
	}

	// METODOS CRUD:

	public void insertar() {
		try {
			DaoImagen.getInstance().daoInsertar(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			DaoImagen.getInstance().daoUpdate(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void borrar() {
		try {
			DaoImagen.getInstance().daoDelete(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void listarImagenesPorIdImagen(int idImagen) {
		
		
		try {
			DaoImagen.getInstance().daoListarPorIdImagen(idImagen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Imagen> listarImagenesPorIdArt(int idArticulo) {
		ArrayList<Imagen> lista=null;
		try {
		lista = DaoImagen.getInstance().daoListarPorArt(idArticulo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public Imagen obtenerUltimaImagen(int idArticulo) throws SQLException {

		List<Imagen> listaImgPorArt = DaoImagen.getInstance().daoListarPorArt(idArticulo);
		System.out.println("Lista ImpPorArt: "+listaImgPorArt);
		Imagen img = new Imagen();
		if(listaImgPorArt != null) {
			
			
			for( int i=0; i<listaImgPorArt.size(); i++) {
				img= listaImgPorArt.get(i);
			}
			
			img= listaImgPorArt.get(listaImgPorArt.size() - 1);
			System.out.println("ListaImportArt despues del for: "+ img);
			
			this.idImagen = img.getIdImagen();
			this.idArticulo = img.getIdArticulo();
			this.nombreImagen = img.getNombreImagen();
			this.ruta = img.getRuta();
			
		}else {
			img.setIdImagen(0);
			
			
		}
		return img;

	}
		
	public void obtenerImagen(int idImagen) throws SQLException {
		Imagen img = DaoImagen.getInstance().daoListarPorIdImagen(idImagen);
		this.idImagen = img.getIdImagen();
		this.idArticulo = img.getIdArticulo();
		this.ruta = img.getRuta();
		this.nombreImagen = img.getNombreImagen();

	}
	
	
}
	
	

