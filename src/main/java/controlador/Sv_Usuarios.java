package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoTipoUsuario;
import dao.DaoUsuario;
import modelo.Categoria;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 * Servlet implementation class Sv_Usuarios
 */
@WebServlet("/Sv_Usuarios")
public class Sv_Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_Usuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void inicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	sesion = request.getSession(false);
		int opcion = Integer.parseInt(request.getParameter("opcion"));
		if (opcion != 0) {
			switch (opcion) {

			case 1:
				this.insertarEditarUsuario(request, response); // insertar y editar usuario.
				break;
			case 2:
				this.listaUsuarios(request, response);
				break;
			case 3:
				this.obtenerUsuario(request, response);
				break;
			case 4:
				this.borrarUsuario(request, response);
				break;
			case 5:
				this.listarTipoUsuario(request, response);
				break;
			case 6:
				this.borrarTipoUsuario(request, response);
				break;
			case 7: insertarEditarTipoUsuario(request, response);
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


	private void insertarEditarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
	
		 ArrayList <String> listaErr = new ArrayList <String> ();
		try {
		int codigoPostal;
		DaoUsuario usuDao = new DaoUsuario();
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		String pass = Sv_Login.getMD5(password); //password encriptada.
		String email = request.getParameter("email");
		String ciudad = request.getParameter("ciudad");
		String telefono = request.getParameter("telefono");
		int tipoUsuario = Integer.parseInt(request.getParameter("idTipoUsuario"));
		String direccion = request.getParameter("direccionEnvio");
		
		if(request.getParameter("codigoPostal")== "") {
			 codigoPostal = 0;	
		}else {
			 codigoPostal = Integer.parseInt(request.getParameter("codigoPostal"));
			
		}
		
		if(pass.isEmpty()) {
			listaErr.add("Debe rellenar el campo 'Password'");
		}else if(pass.length()<5) {
			listaErr.add("El password debe tener al menos 5 digitos");
		}
		
		if(email.isEmpty()) {
			listaErr.add("Debe rellenar el campo 'Email'");
		}
		
		if(nombre.isEmpty()) {
			listaErr.add("Debe rellenar el campo 'Nombre'");
		}
		
		if(apellidos.isEmpty()) {
			listaErr.add("Debe rellenar el campo 'Apellidos'");
		}
		try {
		if(nick.isEmpty()) {
			listaErr.add("Debe rellenar el campo 'Username'");
		}else if(nick.length()<5) {
			listaErr.add("El username debe tener al menos 5 digitos");
		/*}else if(usuDao.buscarNick(nick)== true) {
			listaErr.add("Username ya existe, intentelo con otro diferente.");*/
		}
		}catch(Exception e1) {
			listaErr.add("Error al introducir el username, username existente.");
		}
		
		if(listaErr.isEmpty() && request.getParameter("idUsuario")=="" && usuDao.buscarNick(nick)== false) {
			Usuario usu = new Usuario(nombre, apellidos, nick, pass, email, ciudad, telefono, tipoUsuario, direccion,
					codigoPostal);
			usu.insertar();
			 String tipoMensaje = "altaUsuario";
			 sesion.setAttribute("tipoMensaje",tipoMensaje);
			 request.getRequestDispatcher("mensaje.jsp").forward(request, response);
			/*response.sendRedirect("Sv_Usuarios?opcion=2");*/
		}else if(listaErr.isEmpty() && request.getParameter("idUsuario")!="" && usuDao.buscarNick(nick)== true) {
			Usuario usu = new Usuario(nombre, apellidos, nick, pass, email, ciudad, telefono, tipoUsuario, direccion,
					codigoPostal);
			usu.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
			usu.editar();
			String tipoMensaje = "edicionUsuario";
			 sesion.setAttribute("tipoMensaje",tipoMensaje);
			 request.getRequestDispatcher("mensaje.jsp").forward(request, response);
			/*response.sendRedirect("Sv_Usuarios?opcion=2");*/
		}else {
			request.setAttribute("listaErr",listaErr);
			request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
		}
		}catch(Exception e1) {
			listaErr.add(e1.getMessage());
			request.setAttribute("listaErr", listaErr);
			request.getRequestDispatcher("altaUsuario.jsp").forward(request, response);
		}
		
	
	}
	
	private void listaUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		List<Usuario> lista = DaoUsuario.getInstance().listaUsu();
			
		request.setAttribute("listaUsuarios", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("listaUsuarios.jsp");
		
		rd.forward(request, response);
		

	}

	private void obtenerUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

		Usuario usu = new Usuario();
		usu.setIdUsuario(idUsuario);
		usu.obtenerUsuario(idUsuario);
		System.out.println(usu);
		request.setAttribute("usuario", usu);

		RequestDispatcher rd = request.getRequestDispatcher("fichaUsuario.jsp");
		rd.forward(request, response);

	

	}

	private void borrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		Usuario usu = new Usuario();
		usu.setIdUsuario(idUsuario);
		usu.borrar(idUsuario);
		 String tipoMensaje = "bajaUsuario";
		 sesion.setAttribute("tipoMensaje",tipoMensaje);
		 request.getRequestDispatcher("mensaje.jsp").forward(request, response);
		/*response.sendRedirect("Sv_Usuarios?opcion=2");*/

	}
	
	private void listarTipoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		List<TipoUsuario> listadoTp = DaoTipoUsuario.getInstance().daoListarTipoUsuario();
		request.setAttribute("listadoTp", listadoTp);
		
		RequestDispatcher rd = request.getRequestDispatcher("tipoUsuario.jsp");
		rd.forward(request, response);
		
	}
	
	private void borrarTipoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		int idTipoUsuario = Integer.parseInt(request.getParameter("idTipoUsuario"));
		TipoUsuario tp = new TipoUsuario(idTipoUsuario);
		tp.setIdTipoUsuario(idTipoUsuario);
		tp.eliminarTp();
		
		response.sendRedirect("Sv_Usuarios?opcion=5");
		
	}

	private void insertarEditarTipoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
	
		ArrayList<String> listaErr = new ArrayList<>();
		try {
			
			String nombreTipoUsuario = request.getParameter("nombreTipoUsuario");
			String idTipoUsuario = request.getParameter("idTipoUsuario");
			TipoUsuario tUsu = new TipoUsuario();
		try {
			if(nombreTipoUsuario.isEmpty() && idTipoUsuario.isEmpty()) {
				listaErr.add("Debe introducir un nombre para crear un nuevo Rol de Usuario");
			}else if(!nombreTipoUsuario.isEmpty() &&  !idTipoUsuario.isEmpty()) {
				if(tUsu.buscarIdTipo(Integer.parseInt(idTipoUsuario))==false){
					listaErr.add("No existe el id para el Rol que quiere editar.");	
			}
				
			}else if(!nombreTipoUsuario.isEmpty() && idTipoUsuario.isEmpty()) {
				if(tUsu.buscarNombreTipo(nombreTipoUsuario)== true ) {
					listaErr.add("El tipo de usuario que quiere crear ya existe.");
			}
					
			}
		}catch(Exception e1) {
			listaErr.add("Error al intentar editar o crear un Rol de Usuario");
		}
			
			
			if(listaErr.isEmpty() && request.getParameter("idTipoUsuario")== "" ) {
			TipoUsuario tUsu1 = new TipoUsuario(nombreTipoUsuario);
			tUsu1.insertarTp();
			System.out.println("Rol Usuario insertado: " + tUsu1.getNombreTipoUsuario());
			response.sendRedirect("Sv_Usuarios?opcion=5");
			
			}else if(listaErr.isEmpty() && request.getParameter("idTipoUsuario")!= "" ) {
				int idTipoUsuario1 = Integer.parseInt(request.getParameter("idTipoUsuario"));
				TipoUsuario tUsu1 = new TipoUsuario(nombreTipoUsuario);
				tUsu1.editarTp();
				System.out.println("Rol Usuario editado: " + tUsu1.getNombreTipoUsuario());
				response.sendRedirect("Sv_Usuarios?opcion=5");
			}
			else {
				request.setAttribute("listaErr", listaErr);
				request.getRequestDispatcher("tipoUsuario.jsp").forward(request, response);
				response.sendRedirect("Sv_Usuarios?opcion=5");
			}
			
		}catch(Exception e1) {
			listaErr.add(e1.getMessage());
			request.setAttribute("listaErr", listaErr);
			request.getRequestDispatcher("tipoUsuario.jsp").forward(request, response);
			response.sendRedirect("Sv_Usuarios?opcion=5");
			
		}
		
	}
	
}



