package ejercicioUno;
import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {

	private String host = "jdbc:mysql://localhost:3306";
	private String user = "root";
	private String password = "root";
	private String dbName = "veterinaria";
	
	public void ConectarBaseDatos() {
		
		try {
			
			Connection Conexion = null;
			Conexion = DriverManager.getConnection(host+dbName,user,password);
		} 
		
		catch (Exception e) {
			
		}
	}
}

