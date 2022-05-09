package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoArticulo;
import dao.DaoOrdenDeCompra;
import modelo.Articulo;
import modelo.DetalleCompra;
import modelo.OrdenDeCompra;

/**
 * Servlet implementation class Sv_CarritoCompra
 */
@WebServlet("/Sv_CarritoCompra")
public class Sv_CarritoCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession sesion;
	ArrayList<DetalleCompra> listaCarrito;
	int idDetalle;
	double totalPagar = 0;
	int cantidadCompra = 1;

	/*
	 * ; double precioCompra = 0;
	 * 
	 * 
	 * int contador = 0;
	 */

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sv_CarritoCompra() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void inicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		int opcion = Integer.parseInt(request.getParameter("opcion"));
		switch (opcion) {
		/*
		 * case 1: agregarCarrito(request,response); break; case 2:
		 * carrito(request,response); break;
		 */

		case 1: // Crear compra, insertarla en base de datos.
			this.generarCompra(request, response);
			break;
		case 2: // Crear carrito de compra: Añadir articulos al carrito:
			this.llenarCarrito(request, response);

			break;

		case 3: // editar el estado de una orden de compra
			this.editarEstadoOrden(request, response);
			break;

		case 4: // Eliminar unidad de un articulo del carrito
			this.eliminarUnidad(request, response);
			break;
		case 5: // eliminar item del carrito:
			this.eliminarDetalle(request, response);
			break;
		case 6: 
			this.eliminarDetalleCompra(request, response);
			break;

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

	private void generarCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		// Sesion:
		sesion = request.getSession(false); // sesion ha de estar iniciada
		int nivelAcceso = (int)sesion.getAttribute("nivelAcceso"); // 

		// Recojo la lista generada en la pagina carrito:
		ArrayList<DetalleCompra> listaArticulos = (ArrayList<DetalleCompra>) sesion.getAttribute("carrito");

		// Aqui quito del stock la cantidad de articulo comprado:
		int idArticulo;
		for (int i = 0; i < listaArticulos.size(); i++) {
			idArticulo = listaArticulos.get(i).getIdArticulo();
			Articulo art = new Articulo(idArticulo);
			art.obtenerArticulo(idArticulo);
			int cantStock = art.getCantidad();
			System.out.println("cantidad antes de comprar: " + cantStock);
			if (art.getCantidad() >= listaArticulos.get(i).getCantidad()) {
				art.setCantidad(art.getCantidad() - listaArticulos.get(i).getCantidad());
				art.editar();
				System.out.println("cantidad de art despues de compra:" + art.getCantidad());
			} else {
				request.getRequestDispatcher("errores.jsp");
			}

		}

		// Recupero el idUsuario de la sesion:
		int idUsuario = (int) sesion.getAttribute("idUsuario");

		// Genero un nombre de Orden de compra: fecha de hoy/idUsuario:
		LocalDate fechaNombre = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd/HHmm");
		String nombreOrden = dtf.format(LocalDateTime.now()) + "-" + String.valueOf(idUsuario);

		// Genero la fechaCompra:
		LocalDate fecha = LocalDate.now();

		// Monto total:
		double montoTotal = Double.parseDouble(request.getParameter("totalCompra"));
		// Estado del pago:Por defecto Pendiente
		String estadoPago = "pendiente";

		// Creo el objeto OrdenDeCompra:
		OrdenDeCompra oc = new OrdenDeCompra(nombreOrden, fecha, idUsuario, montoTotal, estadoPago, listaArticulos);

		// Inserto la compra en la base de datos:
		int respuesta = DaoOrdenDeCompra.getInstance().daoCrearCompra(oc);

		if (respuesta != 0 && montoTotal > 0 && nivelAcceso !=0) {
			sesion.setAttribute("lista", listaArticulos);
			String tipoMensaje = "compra";
			sesion.setAttribute("tipoMensaje",tipoMensaje);
			request.getRequestDispatcher("mensaje.jsp").forward(request, response);
			/*request.getRequestDispatcher("Sv_Filtros?opcion=8&idUsuario=" + idUsuario).forward(request, response);*/
			listaArticulos.clear();
		} else {
			request.getRequestDispatcher("errores.jsp").forward(request, response);
		}

	}

	private void llenarCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		// Sesion:
		sesion = request.getSession(false);

		// Recojo la lista generada en la pagina carrito:
		ArrayList<DetalleCompra> listaArticulos = (ArrayList<DetalleCompra>) sesion.getAttribute("carrito");
		if (listaArticulos == null) { // Si la lista de articulos de la sesion esta vacia
			listaArticulos = new ArrayList<DetalleCompra>();
			// crea una lista
			sesion.setAttribute("carrito", listaArticulos); // creo un atributo llamado carrito con la lista de
															// articulos
		}
		DetalleCompra dc = new DetalleCompra();
		// Creo un objeto articulo para obtener mediante el metodo obtenerArticulo los
		// atributos de este
		Articulo art = new Articulo();

		// Recojo el valor idArticulo mediante URL:
		int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
		art.obtenerArticulo(idArticulo);

		// Recojo la cantidad a comprar:
		cantidadCompra = Integer.parseInt(request.getParameter("cantidadCompra"));

		// Creo el precio de la compra del detalle con la cantidad y el precio del
		// articulo:
		double precioCompra = art.getPrecioArticulo() * cantidadCompra;

		// Creo el idDetalle:

		idDetalle = idDetalle + 1;
		
		//Creo el detalle compra dc y le asigno los valores de los atributos.
		dc = new DetalleCompra(idDetalle);
		dc.setIdArticulo(art.getIdArticulo());
		dc.setPrecioCompra(dc.getPrecioCompra() + cantidadCompra * art.getPrecioArticulo());
		dc.setCantidad(cantidadCompra);
		dc.setPrecioArticulo(art.getPrecioArticulo());
		dc.setNombreArticulo(art.getNombreArticulo());
		System.out.println("detallecompra: " + dc.getIdDetalle() + " || " + dc.getNombreArticulo() + "/"
				+ dc.getCantidad() + "/" + dc.getPrecioCompra());
		int indice = -1;
		//Con bucle for busco si existe en la cesta un articulo igual:
		for (int j = 0; j < listaArticulos.size(); j++) {
			DetalleCompra dc1 = listaArticulos.get(j); // para esto creo un nuevo detalle de compra y lo igualo al 
														// item de la lista por el que esta pasando el bucle.
			if (dc.getIdArticulo() == dc1.getIdArticulo()) { //condicion de igualdad, del articulo a introducir y el introducido anteiormente.
				dc.setIdDetalle(dc1.getIdDetalle());
				System.out.println("detalle cambiado:" + dc.getIdDetalle());
				dc.setCantidad(dc.getCantidad() + dc1.getCantidad()); //sumo las cantidades de ambos en caso de coincidir
				dc.setPrecioCompra(dc.getPrecioCompra() + dc1.getPrecioCompra()); //sumo los subtotales
				dc.setIdDetalle(dc1.getIdDetalle()); //y le pongo el idDetalle del que ya estaba dentrode la cesta.
				System.out.println("idDetalle: " + dc.getIdDetalle());

				indice = j;
				break;
			}

		}
		if (indice == -1) { //si el valor del indice sigue en -1 es que no ha entrado en el bucle y por lo tanto solo añade 
			// una linea mas en la cesta
			
			
			listaArticulos.add(dc);

			System.out.println("listaArticulos sin coincidir: " + listaArticulos.size());

		} else { //si el indice es j quiere decir que ha coincidido con uno de los articulos que habia dentro de la cesta y le 
				// asigna el valor j  que es el de la posicion del producto encontrado.

			listaArticulos.set(indice, dc);
			System.out.println("listaArticulos coincidiendo: " + listaArticulos.size());
		}
	
		sesion.setAttribute("carrito", listaArticulos);

		request.getRequestDispatcher("carrito.jsp").forward(request, response);

	}

	private void eliminarUnidad(HttpServletRequest request, HttpServletResponse response)//metodo para eliminar una unidad de un detalle compra.
			throws ServletException, IOException, NumberFormatException, SQLException {
		// Sesion:
		sesion = request.getSession(false); //requiere que la sesion este iniciada
		ArrayList<DetalleCompra> listaArticulos = (ArrayList<DetalleCompra>) sesion.getAttribute("carrito"); // recoge el listado con los articulos
																											//que hay dentro del arrayList.
		int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));//recoge por la URL el parametro idDetalle
		DetalleCompra dc = new DetalleCompra(idDetalle);//crea una detalleCompra con el id recogido.

		for (int i = 0; i < listaArticulos.size(); i++) { 
			DetalleCompra dc1 = listaArticulos.get(i);//creo otro detalleCompra que lo igualo al item por el que voy iterando
			if (dc1.getIdDetalle() == dc.getIdDetalle() && dc1.getCantidad() > 1) { //creo una condicion para que no llegue a cero la cantidad
				listaArticulos.get(i).setCantidad(listaArticulos.get(i).getCantidad() - 1); // resto 1 ud cada vez que pulso el -
				listaArticulos.get(i).setPrecioCompra(listaArticulos.get(i).getPrecioCompra() - listaArticulos.get(i).getPrecioArticulo());
				//resto el precio del articulo y creo el nuevo precio
				
					
				totalPagar = totalPagar - listaArticulos.get(i).getPrecioCompra();// voy sacando la suma total de todos los articulos por cada pasada
																				// resto al total el precio del item que he quitado la unidad.

				sesion.setAttribute("totalPagar", totalPagar); //envio el atributo totalPagar y la lista de detalles de compra por la sesion.
				sesion.setAttribute("carrito", listaArticulos);

			}
		}
		request.getRequestDispatcher("carrito.jsp").forward(request, response);
	}

	private void eliminarDetalle(HttpServletRequest request, HttpServletResponse response)//metodo para eliminar un articulo
			throws ServletException, IOException, NumberFormatException, SQLException {
		// Sesion:
		sesion = request.getSession(false); // sesion ha de estar iniciada.
		ArrayList<DetalleCompra> listaArticulos = (ArrayList<DetalleCompra>) sesion.getAttribute("carrito");//recojo la lista de detalleCompra
																								//con los articulos que ya tiene de ir llenandolo

		int idDetalle = Integer.parseInt(request.getParameter("idDetalle")); //recojo el parametro idDetalle
		
		listaArticulos.remove(idDetalle-1); // borro el item que corresponde al idDetalle-1, pues el idDetalle empieza en 1; procedente del metodo llenarCarrito()
		
		for (int i = 0; i < listaArticulos.size(); i++) { // hago un bucle for para ir restanto los subtotales de los articulos eliminados
			
			totalPagar = totalPagar - listaArticulos.get(i).getPrecioCompra();
		}
			
		
		sesion.setAttribute("totalPagar", totalPagar); // envio por la sesion el totalPagar y la lista de articulos.
		sesion.setAttribute("carrito", listaArticulos);
		request.getRequestDispatcher("carrito.jsp").forward(request, response);
		

	}

	private void editarEstadoOrden(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {

		int idOrden = Integer.parseInt(request.getParameter("idOrden"));
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String estadoPago = request.getParameter("estadoPago");

		if (estadoPago != "") {
			OrdenDeCompra oc = new OrdenDeCompra(idOrden);
			oc.setEstadoPago(estadoPago);
			oc.editarOrden();

			response.sendRedirect("Sv_Filtros?opcion=8&idUsuario=" + idUsuario);

		}

	}
	
	private void eliminarDetalleCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		
		int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));//recojo el id del detalle por la url
		System.out.println("idreocgido:"+ idDetalle);
		
		DetalleCompra dc = new DetalleCompra(idDetalle);
		dc.obtenerDetalleCompra(idDetalle); //obtengo el detalle con el idDetalle
		System.out.println("dc obtenida:" +dc.getIdDetalle()+"/"+dc.getNombreArticulo());
		
		OrdenDeCompra oc = new OrdenDeCompra(dc.getIdOrden());		
		oc.obtenerOrdenCompra(dc.getIdOrden());//lo obtengo a partir de la orden a la que esta asociada el detalle de compra
		System.out.println("oc obtenida:"+oc.getIdOrden()+"/articulo:"+oc.getNombreOrden()+"/monto antes de eliminar:" +oc.getMontoTotal() );
		
		
		oc.setMontoTotal(oc.getMontoTotal()-dc.getPrecioCompra());//resto de la orden la cantidad correspondiente al subtotal del detalle de compra
		oc.setEstadoPago("Devolucion");
		oc.editarOrden();
		System.out.println("montotal resultante: "+oc.getMontoTotal());
		
		
		
		Articulo art = new Articulo(); // creo un articulo del mismo tipo que el detalle
		try {
			art.obtenerArticulo(dc.getIdArticulo()); //obtengo el articulo del stock
			
			art.setCantidad(art.getCantidad()+ dc.getCantidad()); // le sumo la cantidad que corresponde al detalle que quiero quitar.
			
			
			System.out.println("articulo repuesta a stock: "+art.getIdArticulo()+"/"+art.getNombreArticulo()+"cantidad final: "+art.getCantidad());
		}catch(Exception e1) {
			 String tipoMensaje = "errorBorrado";
			 sesion.setAttribute("tipoMensaje",tipoMensaje);
			 request.getRequestDispatcher("mensaje.jsp").forward(request, response);
			
			}
		dc.setPrecioCompra(0);
		dc.setNombreArticulo(dc.getNombreArticulo()+"-Eliminado "+dc.getCantidad()+"ud.");
		
		art.editar();
		dc.editar();
		oc.editarOrden();
		
		System.out.println("detalle compra eliminado: "+dc.getIdDetalle()+"/"+dc.getNombreArticulo());
		try {
			response.sendRedirect("Sv_Filtros?opcion=9&idOrden="+dc.getIdOrden());
			System.out.println("");
		}catch(Exception e1) {
			response.sendRedirect("Sv_Filtros?opcion=7");
		}
		

	}
	}
