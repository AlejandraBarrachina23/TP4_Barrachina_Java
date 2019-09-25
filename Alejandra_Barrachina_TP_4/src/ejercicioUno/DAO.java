package ejercicioUno;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

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
			  
			  CallableStatement StoredProcedureAgregarMascota = ConectarBaseDatos().prepareCall("CALL AgregarMascota(?,?,?)");
			  StoredProcedureAgregarMascota.setString(1,unaNuevaMascota.getNombre());
			  StoredProcedureAgregarMascota.setString(2,unaNuevaMascota.getEdad());
			  StoredProcedureAgregarMascota.setString(3,unaNuevaMascota.getSexo());
			  StoredProcedureAgregarMascota.execute();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}  	
	}
	
	
	public void EliminarMascota(Mascota MascotaEliminar) throws SQLException {
			  
		  CallableStatement StoredProcedureEliminarMascota = ConectarBaseDatos().prepareCall("CALL EliminarMascota(?)");
		  StoredProcedureEliminarMascota.setInt(1,MascotaEliminar.getID());
		  StoredProcedureEliminarMascota.execute(); 
	}
	 
	
	public ArrayList<Mascota> ListarMascotas() throws SQLException {
		
			ArrayList<Mascota> ListadoMascotas = new ArrayList<Mascota>();
		    String Consulta = "SELECT * FROM Mascotas";
		    java.sql.Statement EstablecerConsulta = ConectarBaseDatos().createStatement();
		    ResultSet TablaResultados = EstablecerConsulta.executeQuery(Consulta);		
			
			while(TablaResultados.next()) {
				
				Mascota unaMascota = new Mascota();
				unaMascota.setID(TablaResultados.getInt("id"));
				unaMascota.setNombre(TablaResultados.getString("Nombre"));
				unaMascota.setEdad(TablaResultados.getString("Edad"));
				unaMascota.setSexo(TablaResultados.getString("Sexo"));
				ListadoMascotas.add(unaMascota);
			}
			
		return ListadoMascotas;
	}
	
	public DefaultTableModel SetearTabla(ArrayList<Mascota>ListadoMascotas) {
		
		DefaultTableModel ModeloDeTabla = new DefaultTableModel();
		ModeloDeTabla.setColumnIdentifiers(new Object[] {"Id","Nombre","Edad","Sexo"});
		
		for (Mascota mascota : ListadoMascotas) {
			
			ModeloDeTabla.addRow(new Object[] {mascota.getID(),mascota.getNombre(), mascota.getEdad(), mascota.getSexo()});
		}
		
		return ModeloDeTabla;
	}
	
	
}

