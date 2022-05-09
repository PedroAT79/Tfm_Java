/**
 * Conexion a base de datos, mediante el patron SINGLETON. Mediante esta conexion se 
 * van a conectar las Clases DAO al sistema de tablas de la aplicacion.
 */
package controlador;
import java.sql.Connection;
import java.sql.DriverManager;
	import java.sql.SQLException;
import java.util.Properties;
	
public class DBConection {
		/**
		 * 	Usamos una sola instancia de conexion para todas las veces que necesitemos 
		 * 	acceder a la base de datos durante la ejecucion del programa.
		 */
		private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tienda?useSSL=false";
		private static Connection instance = null; //variable estatica del tipo conection
		
		private DBConection() {
			
		}
		/*
		 *  metodo estatico que verifica si ya existe una instancia de la conexion
		 *  si no existe la crea y si si existe la retorna y termina el metodo.
		 */
		public static Connection getConnection() throws SQLException { 
			if(instance == null) {
				Properties props = new Properties();
				props.put("user", "root");
				props.put("password", "1234");
				instance = DriverManager.getConnection(JDBC_URL, props);
			}
			
			return instance;
			
		}
	}
