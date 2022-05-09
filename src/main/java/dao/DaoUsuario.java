package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.DBConection;
import modelo.Usuario;

public class DaoUsuario {

	private Connection conn = null;

	public static DaoUsuario instance = null;

	public DaoUsuario() throws SQLException {
		conn = DBConection.getConnection();
	}

	public static DaoUsuario getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoUsuario();
		}
		return instance;

	}

//SENTENCIAS SQL:

	private static final String SQL_INSERT = "INSERT INTO usuarios(nombre, apellidos,nick,password,email,ciudad,telefono,idTipoUsuario,direccionEnvio,codigoPostal) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE usuarios SET nombre=?, apellidos=?,nick=?, password=?, email=?, ciudad=?, telefono=?, idTipoUsuario=?, direccionEnvio=?, codigoPostal=? WHERE idUsuario=?";
	private static final String SQL_DELETE = "DELETE from usuarios WHERE idUsuario=?";
	private static final String SQL_SELECT = "SELECT * FROM usuarios";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM usuarios WHERE idUsuario = ?";
	private static final String SQL_SELECT_BY_tipoUsuario = "SELECT * FROM usuarios WHERE idTipoUsuario=?";
	private static final String SQL_SELECT_BY_nick = "SELECT * FROM usuarios WHERE nick=?";

//METODOS PARA EL CRUD:

	// Insertar usuario:

	public int daoInsertarUsuario(Usuario usu) throws SQLException {
		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_INSERT);
		ps.setString(1, usu.getNombre());
		ps.setString(2, usu.getApellidos());
		ps.setString(3, usu.getNick());
		ps.setString(4, usu.getPassword());
		ps.setString(5, usu.getEmail());
		ps.setString(6, usu.getCiudad());
		ps.setString(7, usu.getTelefono());
		ps.setInt(8, usu.getTipoUsuario());
		ps.setString(9, usu.getDireccionEnvio());
		ps.setInt(10, usu.getCodigoPostal());

		filas = ps.executeUpdate();
		ps.close();
		return filas;

	}

	public int daoEditarUsuario(Usuario usu) throws SQLException {
		int filas = 0;

		if (usu.getIdUsuario() == 0) {
			return filas;
		} else {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE);
			ps.setString(1, usu.getNombre());
			ps.setString(2, usu.getApellidos());
			ps.setString(3, usu.getNick());
			ps.setString(4, usu.getPassword());
			ps.setString(5, usu.getEmail());
			ps.setString(6, usu.getCiudad());
			ps.setString(7, usu.getTelefono());
			ps.setInt(8, usu.getTipoUsuario());
			ps.setString(9, usu.getDireccionEnvio());
			ps.setInt(10, usu.getCodigoPostal());

			ps.setInt(11, usu.getIdUsuario());
			filas = ps.executeUpdate();
			ps.close();

		}

		return filas;
	}

	public Usuario daoObtenerUsuario(int idUsuario) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID);
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();

		Usuario usu = null;
		if (rs.next()) {
			usu = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("nick"), rs.getString("password"), rs.getString("email"), rs.getString("ciudad"),
					rs.getString("telefono"), rs.getInt("idTipoUsuario"), rs.getString("direccionEnvio"),
					rs.getInt("codigoPostal"));
			System.out.println("usuario obtenido del dao:" + usu);

		}
		ps.close();
		rs.close();
		return usu;

	}

	public int daoBorrarUsuario(int idUsuario) throws SQLException {
		int filas = 0;
		PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		ps.setInt(1, idUsuario);

		ps.executeUpdate();

		ps.close();
		return filas;

	}

	public ArrayList<Usuario> listaUsu() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();

		ArrayList<Usuario> listaUsuarios = null;
		while (rs.next()) {
			if (listaUsuarios == null)
				listaUsuarios = new ArrayList<>();
			listaUsuarios.add(new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("nick"), rs.getString("password"), rs.getString("email"), rs.getString("ciudad"),
					rs.getString("telefono"), rs.getInt("idTipoUsuario"), rs.getString("direccionEnvio"),
					rs.getInt("codigoPostal")));

		}
		rs.close();
		ps.close();
		for(int i=0; i<listaUsuarios.size(); i++) {
			System.out.println("lista usuariosDao: " +listaUsuarios.get(i).getNombre());
		}
		
		return listaUsuarios;

	}
	
	public boolean buscarNick(String nick) throws SQLException  {
		boolean encontrado = false;
		PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_nick);
		ps.setString(1, nick);
		ResultSet rs = ps.executeQuery();

		Usuario usu = null;
		if (rs.next()) {
			usu = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("nick"), rs.getString("password"), rs.getString("email"), rs.getString("ciudad"),
					rs.getString("telefono"), rs.getInt("idTipoUsuario"), rs.getString("direccionEnvio"),
					rs.getInt("codigoPostal"));
			System.out.println("usuario obtenido del dao:" + usu);
			encontrado = true;

		}
		ps.close();
		rs.close();
		return encontrado;

	}
	
	//Comprobacion que el usuario existe mediante nick y Password:
	
	public int daoUsuarioValido(String nick, String password) throws SQLException {
		int idUsuario = 0; // inicializo el idUsuario en 0
		PreparedStatement ps = conn.prepareStatement("SELECT idUsuario FROM usuarios WHERE nick=? AND password=?"); // selecciona el id de Usuario que corresponde al nick y al password que he introducido
		ps.setString(1, nick); 
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			idUsuario = rs.getInt("idUsuario"); //si no corresponde a ninguno idUsuario permanece 0,
		}
		rs.close();
		ps.close();
		return idUsuario;
		
	}
	}


