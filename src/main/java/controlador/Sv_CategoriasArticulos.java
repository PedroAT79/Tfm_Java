package controlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DaoArticulo;
import dao.DaoCategoria;
import dao.DaoImagen;
import modelo.Articulo;
import modelo.Categoria;
import modelo.Imagen;

/**
 * Servlet implementation class Sv_CategoriasArticulos
 */
@WebServlet("/Sv_CategoriasArticulos")
@MultipartConfig
public class Sv_CategoriasArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
	private String pathFiles = "C:\\Users\\plat_\\eclipse-workspace\\TiendaCiclismo\\src\\main\\webapp\\fotos";

	private String prePathFiles = "";

	private File uploads = new File(pathFiles);
	private File preUploads = new File(prePathFiles);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sv_CategoriasArticulos() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String tratamientoRuta(String prePathFiles2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @throws SQLException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void inicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int opcion = Integer.parseInt(request.getParameter("opcion"));
		if (opcion != 0) {
			switch (opcion) {
			case 1: // insertar / editar articulo:
				this.insertarArt(request, response);
				break;
			case 2: // borrar categoria:
				this.eliminarSubcategoria(request, response);
				break;
			case 3: // insertar / editar categoria:
				this.insertarCat(request, response);
				break;
			case 4:
				try { // listar articulos:
					this.listarArticulos(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5: // eliminar articulo
				this.eliminarArticulos(request, response);
				break;
			case 6: // obtener articulo e imagenes asociadas
				this.obtenerImagenArticulo(request, response);

				break;
			case 7: // listarCategorias
				this.listarSubcategorias(request, response);
				break;
			case 8:
				this.obtenerCategoria(request, response);
				break;
			case 9: // insertar editar imagenes
				this.insertarEditarImagenes(request, response);
				break;
			case 10: // eliminar imagenes de articulo
				this.eliminarImagen(request, response);
				break;
			case 11: // insertar / editar categoria:
				this.editarCat(request, response);
				break;

			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			inicio(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {
			this.inicio(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void insertarArt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		sesion = request.getSession(false);
		ArrayList<String> listaErr = new ArrayList<>();
		try {
			String nombreArticulo = request.getParameter("nombreArticulo");
			String precioArticulo = request.getParameter("precioArticulo");
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			String estadoConservacion = request.getParameter("estadoConservacion");
			int idSubcategoria = Integer.parseInt(request.getParameter("idSubcategoria"));
			String descripcion = request.getParameter("descripcion");
			double precio = 0;
			int cantidad1 = 0;

			try {
				if (precioArticulo.isEmpty()) {
					listaErr.add("Debe de introducir el campo Precio");
				} else {
					precio = Double.parseDouble(precioArticulo);
					if (precio <= 0) {
						listaErr.add("Debe introducir un precio mayor que 0");
					}
				}
			} catch (Exception e1) {
				listaErr.add("Error de formato introducido en el campo Precio");
			}

			if (nombreArticulo.isEmpty()) {
				listaErr.add("Debe de introducir una breve descripcion del articulo");
			} else if (nombreArticulo.length() < 10) {
				listaErr.add("La descripcion debe tener al menos 10 digitos");
			}

			if (marca.isEmpty()) {
				listaErr.add("Debe de introducir la marcar del articulo");
			}
			if (modelo.isEmpty()) {
				modelo = "No especificado";
			}

			try {
				if (cantidad == 0) {
					listaErr.add("Debe de introducir un valor para el campo Cantidad.");
				} else if (cantidad <= 0) {
					listaErr.add("La Cantidad introducida debe ser mayor a 0");
				}
			} catch (Exception e1) {
				listaErr.add("Error de formato introducido en el campo Cantidad");
			}

			if (estadoConservacion == "Elegir una opcion") {
				listaErr.add("Debe elegir una opcion para el Estado de Conservacion");
			}
			if (idSubcategoria == 0) {
				listaErr.add("Debe de elegir una Categoria de Articulo");
			}
			if (descripcion.isEmpty()) {
				descripcion = "Sin descripcion de Articulo.";
			}

			if (listaErr.isEmpty() && request.getParameter("idArticulo") == "") {

				Articulo art = new Articulo(nombreArticulo, precio, marca, modelo, cantidad, estadoConservacion,
						idSubcategoria, descripcion);
				art.insertar();
				String tipoMensaje = "altaArticulo";
				sesion.setAttribute("tipoMensaje", tipoMensaje);
				request.getRequestDispatcher("mensaje.jsp").forward(request, response);
				/* response.sendRedirect("Sv_CategoriasArticulos?opcion=4"); */
				System.out.println("Ha insertado un nuevo articulo:" + art);

			} else if (listaErr.isEmpty() && request.getParameter("idArticulo") != "") {
				Articulo art = new Articulo(nombreArticulo, precio, marca, modelo, cantidad, estadoConservacion,
						idSubcategoria, descripcion);
				art.setIdArticulo(Integer.parseInt(request.getParameter("idArticulo")));
				art.editar();
				System.out.println("Ha editado un articulo, con ID.: " + art.getIdArticulo());
				String tipoMensaje = "edicionArticulo";
				sesion.setAttribute("tipoMensaje", tipoMensaje);
				request.getRequestDispatcher("mensaje.jsp").forward(request, response);
				/* response.sendRedirect("Sv_CategoriasArticulos?opcion=4"); */

			} else {
				request.setAttribute("listaErr", listaErr);
				request.getRequestDispatcher("altaArticulo.jsp").forward(request, response);
			}
		} catch (Exception e1) {
			listaErr.add(e1.getMessage());
			request.setAttribute("listaErr", listaErr);
			request.getRequestDispatcher("altaArticulo.jsp").forward(request, response);
		}
	}

	private void eliminarArticulos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		sesion = request.getSession(false);
		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
		Articulo art = new Articulo(idArticulo);
		art.setIdArticulo(idArticulo);
		art.borrarArticulo();
		String tipoMensaje = "borradoArticulo";
		sesion.setAttribute("tipoMensaje", tipoMensaje);
		request.getRequestDispatcher("mensaje.jsp").forward(request, response);
		/* response.sendRedirect("Sv_CategoriasArticulos?opcion=4"); */

	}

	private void obtenerArticulo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));

		Articulo art = new Articulo();
		art.obtenerArticulo(idArticulo);
		request.setAttribute("articulo", art);

		RequestDispatcher rd = request.getRequestDispatcher("articulo2.jsp");
		rd.forward(request, response);

	}

	private void listarArticulos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Articulo> listaArticulos = DaoArticulo.getInstance().listaArticulos();
		
				request.setAttribute("listaArticulos", listaArticulos);
		

			request.getRequestDispatcher("listadoArticulos.jsp").forward(request, response);

	}

	private void insertarCat(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		ArrayList<String> listaErr = new ArrayList<String>();

		try {
			Categoria cat = new Categoria();

			String nombreCategoria = request.getParameter("nombreCategoria");

			try {
				if (nombreCategoria.isEmpty()) {
					listaErr.add("Debe de rellenar el campor 'Nombre categoria");
				} else if (cat.buscarCatPorNombre(nombreCategoria) == true) {
					listaErr.add("El nombre de categoria introducido ya existe");
				} else if (cat.validarNumeros(nombreCategoria) == true) {
					listaErr.add("El nombre de categoria No admite numeros");
				}
			} catch (Exception e1) {
				listaErr.add("Error al intentar crear una nueva Categoria");
			}
			if (listaErr.isEmpty() && request.getParameter("idSubcategoriaInsert") == "") {
				Categoria cat1 = new Categoria(nombreCategoria);
				cat1.insertarCat();

				response.sendRedirect("Sv_CategoriasArticulos?opcion=7");
			} else {
				request.setAttribute("listaErr", listaErr);
				request.getRequestDispatcher("altaCategoria.jsp").forward(request, response);
			}

		} catch (Exception e1) {
			listaErr.add(e1.getMessage());
			request.setAttribute("listaErr", listaErr);
			request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
		}
	}

	private void editarCat(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		ArrayList<String> listaErr = new ArrayList<String>();
		try {

			Categoria cat = new Categoria();
			String nombreCategoria = request.getParameter("nombreCategoria");
			String idSubcategoria = request.getParameter("idSubcategoriaEdit");

			try {
				if (nombreCategoria.isEmpty() || idSubcategoria.isEmpty()) {
					listaErr.add("Debe introducir Id y Nombre para editar la categoria");
				} else if (Integer.parseInt(idSubcategoria) <= 0
						|| cat.buscarCategoriaPorId(Integer.parseInt(idSubcategoria)) == false) {
					listaErr.add("La categoria a editar no existe (id introducido es erroneo)");
				} else if (cat.validarNumeros(nombreCategoria) == true) {
					listaErr.add("El nombre de categoria No admite numeros");
				}

			} catch (Exception e1) {
				listaErr.add("Error al intentar EDITAR una nueva Categoria");
			}
			if (listaErr.isEmpty()) {
				Categoria cat1 = new Categoria(nombreCategoria);
				cat1.setIdSubcategoria(Integer.parseInt(idSubcategoria));
				cat1.editarCategoria();
				response.sendRedirect("Sv_CategoriasArticulos?opcion=7");
			} else {
				request.setAttribute("listaErr", listaErr);
				request.getRequestDispatcher("altaCategoria.jsp").forward(request, response);
			}

		} catch (Exception e1) {
			request.setAttribute("listaErr", listaErr);
			request.getRequestDispatcher("altaCategoria.jsp").forward(request, response);
		}

	}

	private void eliminarSubcategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idSubcategoria = Integer.parseInt(request.getParameter("idSubcategoria"));
		Categoria cat = new Categoria(idSubcategoria);
		cat.setIdSubcategoria(idSubcategoria);
		cat.eliminarCategoria();

		response.sendRedirect("Sv_CategoriasArticulos?opcion=7");

	}

	private void listarSubcategorias(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		List<Categoria> listadoCat = DaoCategoria.getInstance().listarCategorias();
		request.setAttribute("listadoCat", listadoCat);

		RequestDispatcher rd = request.getRequestDispatcher("altaCategoria.jsp");
		rd.forward(request, response);
	}

	private void obtenerCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idSubcategoria = Integer.parseInt(request.getParameter("idSubcategoria"));
		Categoria cat = new Categoria();
		cat.obtenerCategoria(idSubcategoria);

		request.setAttribute("catObtenida", cat);

		RequestDispatcher rd = request.getRequestDispatcher("altaCategoria.jsp");
		rd.forward(request, response);

		response.sendRedirect("Sv_CategoriasArticulos?opcion=7");

	}

	private void obtenerImagenArticulo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));

		Articulo art = new Articulo();
		art.obtenerArticulo(idArticulo);
		request.setAttribute("articulo", art);

		Imagen img = new Imagen();
		img.obtenerUltimaImagen(idArticulo);

		ArrayList<Imagen> listado = null;

		if (img.obtenerUltimaImagen(idArticulo).getIdImagen() != 0) {
			listado = img.listarImagenesPorIdArt(idArticulo);
			request.setAttribute("listadoImagenes", listado);
		} else {
			listado = img.listarImagenesPorIdArt(1); // imagen por defecto.
			request.setAttribute("listadoImagenes", listado);

		}

		request.setAttribute("imagenObtenida", img);

		RequestDispatcher rd = request.getRequestDispatcher("articulo2.jsp");
		rd.forward(request, response);

	}

	/*
	 * private void obtenerImagenesPorArticulo(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException,
	 * SQLException { int idArticulo =
	 * Integer.parseInt(request.getParameter("idArticulo")); Imagen img = null;
	 * List<Imagen> listaImagenes =
	 * DaoImagen.getInstance().daoListarPorArt(idArticulo);
	 * request.setAttribute("listadoImgsArt", listaImagenes);
	 * 
	 * RequestDispatcher rd = request.getRequestDispatcher("articulo2.jsp");
	 * 
	 * response.sendRedirect("articulo2.jsp");
	 * 
	 * }
	 */
	private void obtenerImagenesPorArticulo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));

		List<Imagen> listaImagenes = DaoImagen.getInstance().daoListarPorArt(idArticulo);
		for (Imagen img : listaImagenes) {
			img.setRuta(img.getRuta().replaceAll("\\", "fotos/"));
			System.out.println("imagenes: " + img.getRuta());
		}

		request.setAttribute("listadoImgsArt", listaImagenes);

		RequestDispatcher rd = request.getRequestDispatcher("articulo2.jsp");

		response.sendRedirect("articulo2.jsp");

	}

	private void insertarEditarImagenes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// Recojo los inputs del formulario:
		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
		String nombreArchivo = request.getParameter("nombreImagen");

		// Recupero el archivo del formulario:
		Part part = request.getPart("foto");
		Path path = Paths.get(part.getSubmittedFileName());
		String fileName = path.getFileName().toString();

		// Preparo el buffer para el guardado:
		InputStream input = part.getInputStream();
		String ruta = "";
		String preRuta = "";
		if (input != null) {
			// Creo el archivo:
			File file = new File(uploads, fileName);
			File prefile = new File(preUploads, fileName);
			// Obtengo la ruta para la base de datos:

			ruta = file.getAbsolutePath();

			preRuta = prefile.getPath();

			preRuta = preRuta.replace("\\", "fotos/");

			System.out.println("La ruta es: " + ruta);
			System.out.println("La preRuta es: " + preRuta);

			// Copio la foto:
			try {
				Files.copy(input, file.toPath());
			} catch (Exception e) {
				PrintWriter respuesta = response.getWriter();
				respuesta.print("Error al copiar la foto");
				System.out.println("Error al copiar la foto");
			}
		}
		// Creo el objeto imagen con sus atributos:
		Imagen img = null;
		img = new Imagen(idArticulo, preRuta, nombreArchivo);

		List<Imagen> listaImg = DaoImagen.getInstance().daoListarPorArt(idArticulo);
		System.out.println("Lista Imagenes desde SERVLET: " + listaImg);

		if (listaImg == null && img.getIdImagen() == 0) {

			img.insertar();
			System.out.println("Datos 1º imagen del articulo: " + img);

		} else {
			if (listaImg.size() < 4 && img.getIdImagen() == 0) { // tope de 4 archivos por articulo
				img.insertar();
				System.out.println("Datos imagen desde insertar: IDARTICULO" + img.getIdArticulo() + " IDIMAGEN "
						+ img.getIdImagen() + " RUTA " + img.getRuta() + "nombre: " + nombreArchivo);

			} else {
				int idImagen = Integer.parseInt(request.getParameter("idImagen"));
				img.setIdImagen(idImagen);
				img.editar();
				System.out.println("Datos imagen desde editar: IDARTICULO" + img.getIdArticulo() + " IDIMAGEN "
						+ img.getIdImagen() + " RUTA " + img.getRuta() + "nombre: " + nombreArchivo);

			}
		}

		response.sendRedirect("Sv_CategoriasArticulos?opcion=6&idArticulo=" + idArticulo);
	}

	private void eliminarImagen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idImagen = Integer.parseInt(request.getParameter("idImagen"));
		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));

		Imagen img = new Imagen();
		img.setIdImagen(idImagen);

		img.obtenerImagen(idImagen);

		img.borrar();
		System.out.println("Imagen borrada:" + img);
		response.sendRedirect("Sv_CategoriasArticulos?opcion=6&idArticulo=" + idArticulo);

	}

}
