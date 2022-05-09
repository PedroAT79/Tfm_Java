package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoArticulo;
import dao.DaoImagen;
import dao.DaoOrdenDeCompra;
import dao.DaoDetalleCompra;
import controlador.Sv_CategoriasArticulos;
import modelo.Articulo;
import modelo.DetalleCompra;
import modelo.Imagen;
import modelo.OrdenDeCompra;
import modelo.Usuario;

/**
 * Servlet implementation class Sv_Filtros
 */
@WebServlet("/Sv_Filtros")
public class Sv_Filtros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sv_Filtros() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void inicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		int opcion = Integer.parseInt(request.getParameter("opcion"));
		if (opcion != 0) {
			switch (opcion) {
			case 1:// filtrar articulos por marca:
				this.filtrarMarca(request, response);
				break;
			case 2:
				this.filtrarModelo(request, response);
				break;
			case 3:
				this.filtrarEstado(request, response);
				break;
			case 4:
				this.filtrarCategoria(request, response);
				break;
			case 5:
				this.obtenerImagenArticulo(request, response);
				break;
			case 6:
				this.listarTodo(request, response);
				break;
			case 7:
				this.obtenerTodasLasOrdenes(request, response);
				break;
			case 8:
				this.filtrarPedidoPorUsuario(request, response);
				break;
			case 9:
				this.obtenerDetalleDeCompra(request, response);
				break;
			case 10:
				this.filtrarPorSecciones(request, response);
				break;
			case 11:
				this.obtenerOrdenesPorFechas(request, response);
				break;

			default:

			}

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			inicio(request, response);
		} catch (NumberFormatException | ServletException | IOException | SQLException e) {
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
		} catch (NumberFormatException | ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// FILTROS DE ARTICULOS:
	// Filtrar por marca de articulo:
	private void filtrarMarca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		String marca = request.getParameter("filtro");
		ArrayList<Articulo> listadoPorMarca = DaoArticulo.getInstance().filtroPorMarca(marca);

		request.setAttribute("listaFiltro", listadoPorMarca);
		request.getRequestDispatcher("filtro.jsp");

		response.sendRedirect("filtro.jsp");

	}

	private void filtrarModelo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		String modelo = request.getParameter("filtro");
		ArrayList<Articulo> listadoPorModelo = DaoArticulo.getInstance().filtroPorModelo(modelo);

		request.setAttribute("listaFiltro", listadoPorModelo);
		request.getRequestDispatcher("filtro.jsp");

		response.sendRedirect("filtro.jsp");

	}

	private void filtrarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		String estado = request.getParameter("filtro");
		ArrayList<Articulo> listadoPorEstado = DaoArticulo.getInstance().filtroPorEstado(estado);

		request.setAttribute("listaFiltro", listadoPorEstado);
		request.getRequestDispatcher("filtro.jsp");

		response.sendRedirect("filtro.jsp");

	}

	private void filtrarCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		String categoria = request.getParameter("filtro");

		ArrayList<Articulo> listadoPorCategoria = DaoArticulo.getInstance()
				.filtroPorSubcategoria(Integer.parseInt(categoria));

		request.setAttribute("listaFiltro", listadoPorCategoria);
		request.getRequestDispatcher("mostrarArticulos.jsp").forward(request, response);

		/* response.sendRedirect("mostrarArticulos.jsp"); */

	}

	private void filtrarPorSecciones(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		int seccion = Integer.parseInt(request.getParameter("seccion"));

		if (seccion != 0) {
			switch (seccion) {
			case 1: // filtrar por articulos tipo bicicleta:
				ArrayList<Articulo> listadoPorBicicletas = DaoArticulo.getInstance().filtroBicicletas();
				request.setAttribute("listaFiltro", listadoPorBicicletas);

				break;
			case 2: // filtrar por articulos tipo componentes:
				ArrayList<Articulo> listadoPorComponentes = DaoArticulo.getInstance().filtroComponentes();
				request.setAttribute("listaFiltro", listadoPorComponentes);

				break;
			case 3: // filtro por articulos tipo electronica/gadgets:
				ArrayList<Articulo> listadoPorElectronica = DaoArticulo.getInstance().filtroElectronica();
				request.setAttribute("listaFiltro", listadoPorElectronica);

				break;
			default:
				response.sendRedirect("index.jsp");
			}
			request.getRequestDispatcher("mostrarArticulos.jsp").forward(request, response);
		}

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

		RequestDispatcher rd = request.getRequestDispatcher("mostrarArticulo.jsp");
		rd.forward(request, response);

	}

	private void listarTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		ArrayList<Articulo> listadoTotal = DaoArticulo.getInstance().listaArticulos();

		request.setAttribute("listaFiltro", listadoTotal);
		request.getRequestDispatcher("index.jsp").forward(request, response);

		/* response.sendRedirect("mostrarArticulos.jsp"); */

	}

	/*-------filtrado de ordenes de compra-------------------*/

	private void obtenerTodasLasOrdenes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		ArrayList<OrdenDeCompra> listadoTotal = DaoOrdenDeCompra.getInstance().daoListarTodasLasOrdenes();
		request.setAttribute("lista", listadoTotal);
		request.getRequestDispatcher("listadoOrdenes.jsp").forward(request, response);

	}

	private void filtrarPedidoPorUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		int idUsuario = 0;
		if (Integer.parseInt(request.getParameter("idUsuario")) != 0) {
			idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		} else {
			idUsuario = (int) request.getAttribute("idUsuario");
		}

		ArrayList<OrdenDeCompra> listadoPorUsuario = DaoOrdenDeCompra.getInstance()
				.daoFiltrarPedidosPorUsuario(idUsuario);
		Usuario usu = new Usuario();
		usu.setIdUsuario(idUsuario);
		usu.obtenerUsuario(idUsuario);

		request.setAttribute("usuario", usu);
		request.setAttribute("lista", listadoPorUsuario);
		request.getRequestDispatcher("fichaUsuario.jsp").forward(request, response);

	}

	private void obtenerDetalleDeCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		int idOrden = Integer.parseInt(request.getParameter("idOrden"));
		ArrayList<DetalleCompra> listadoPorIdOrden = DaoDetalleCompra.getInstance().daoListarDetalles(idOrden);

		OrdenDeCompra oc = new OrdenDeCompra(idOrden);
		oc.obtenerOrdenCompra(idOrden);

		request.setAttribute("orden", oc);
		request.setAttribute("listaPorOrden", listadoPorIdOrden);
		request.getRequestDispatcher("detalleCompra.jsp").forward(request, response);

	}

	private void obtenerOrdenesPorFechas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		String estado = request.getParameter("estadoPago");
		int seleccion = Integer.parseInt(request.getParameter("seleccion"));
		ArrayList<OrdenDeCompra> lista = DaoOrdenDeCompra.getInstance().daoFiltrarPorEstadoPago(estado);
		OrdenDeCompra oc = null;
		ArrayList<OrdenDeCompra> lista1 = null;

		if (seleccion != 0) {
			switch (seleccion) {
			case 1: // ordenes pendientes de mas de 48 horas
				for (int i = 0; i < lista.size(); i++) {
					int idOrden = lista.get(i).getIdOrden();
					oc = new OrdenDeCompra(idOrden);
					oc.obtenerOrdenCompra(idOrden);
					if (lista1 == null) {
						lista1 = new ArrayList<>();
					}
					if (oc.ordenesMas24(idOrden)) {
						lista1.add(oc);
					}
				}
				break;
			case 2://ORDENES PENDIENTES DE 24 HORAS
				for (int i = 0; i < lista.size(); i++) {
					int idOrden = lista.get(i).getIdOrden();
					oc = new OrdenDeCompra(idOrden);
					oc.obtenerOrdenCompra(idOrden);
					if (lista1 == null) {
						lista1 = new ArrayList<>();
					}
					if (!oc.ordenesMas24(idOrden)) {
						lista1.add(oc);
					}
				}
				break;
			case 3://ORDENES EN ESTADO "EN TRAMITE"
				lista1 = DaoOrdenDeCompra.getInstance().daoFiltrarPorEstadoPago(estado);
			break;
			default:

			}
			request.setAttribute("lista", lista1);
			request.getRequestDispatcher("listadoOrdenes.jsp").forward(request, response);

		}

	}
}