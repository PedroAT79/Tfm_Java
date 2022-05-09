package controlador;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class Sv_Login
 */
@WebServlet("/Sv_Login")
public class Sv_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sv_Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void inicio(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int opcion = Integer.parseInt(request.getParameter("opcion"));
		if (opcion != 0) {
			switch (opcion) {
			case 1:
				this.checkLogin(request, response);
				break;
			case 2:
				this.cerrarSesion(request, response);
				
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
		} catch (Exception e) {
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
		// doGet(request, response);ç
		try {
			this.inicio(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<String> listaErr = new ArrayList<String>();
		String nickIntro = request.getParameter("nick");
		String passIntro = request.getParameter("password");
		String passEncrypt = getMD5(passIntro);
		int nivelAcceso =0;

		Usuario usu = new Usuario();
		int idUsuario = usu.comprobarLoguin(nickIntro, passEncrypt);
	
		if (idUsuario != 0) {
			usu.obtenerUsuario(idUsuario);
			nivelAcceso = usu.getTipoUsuario();
			String nombreUsu = usu.getNick();

			sesion = request.getSession();
			sesion.setAttribute("usuario", nombreUsu);
			sesion.setAttribute("nivelAcceso", nivelAcceso);
			sesion.setAttribute("idUsuario", idUsuario);
			System.out.println("usuario LOGUEADO: " + usu.getNombre()+"/"+ nivelAcceso);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			
			

		} else {
			listaErr.add("EL USUARIO NO EXISTE O EL PASSWORD ES INCORRECTO");
			System.out.println("Usuario no esta logueado");
			usu.setTipoUsuario(1); // usuario invitado
			nivelAcceso = usu.getTipoUsuario();
			System.out.println("tipo de usuario:" + nivelAcceso);
			request.setAttribute("listaErr", listaErr);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

		

	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
		System.out.println("Sesion terminada");
	}

	// Metodo para encriptar passwords:
	public static String getMD5(String cadena) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(cadena.getBytes());

		int size = b.length;
		StringBuilder h = new StringBuilder(size);
		for (int i = 0; i < size; i++) {

			int u = b[i] & 255;

			if (u < 16) {
				h.append("0").append(Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();

	}

}
