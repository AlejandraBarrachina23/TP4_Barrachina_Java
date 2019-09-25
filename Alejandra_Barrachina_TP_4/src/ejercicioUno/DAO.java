package ejercicioUno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DAO {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String password = "";
	private String dbName = "veterinaria";
	Connection Conexion = null;
	
	public Connection ConectarBaseDatos() {
		
		Connection Conexion = null;
		 
		try {
			
			Conexion = DriverManager.getConnection(host+dbName,user,password);			
		} 
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Conexion;
	}
	
	public void AgregarMascota(Mascota unaNuevaMascota) {
		
		try {
			  
			  DAO ConexionDB = new DAO();
			  CallableStatement StoredProcedureAgregarMascota = ConexionDB.ConectarBaseDatos().prepareCall("CALL AgregarMascota(?,?,?,?)");
			  StoredProcedureAgregarMascota.setInt(1,unaNuevaMascota.getID());
			  StoredProcedureAgregarMascota.setString(2,unaNuevaMascota.getNombre());
			  StoredProcedureAgregarMascota.setString(3,unaNuevaMascota.getEdad());
			  StoredProcedureAgregarMascota.setString(4,unaNuevaMascota.getSexo());
			  StoredProcedureAgregarMascota.execute();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}  	
	}	
	 
	public ArrayList<Mascota> ListarMascotas() {
		
			ArrayList<Mascota> ListadoMascotas = new ArrayList<Mascota>();
			
			
			
			return ListadoMascotas;
	}
}

