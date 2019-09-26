package ejercicioUno;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Validar {

	public static boolean CampoVacio(JPanel panelContenedor) throws Exception {
		
		for (Component Componente : panelContenedor.getComponents()) {
			
			if(Componente instanceof JTextField) {
				if(((JTextField) Componente).getText().trim().isEmpty()) throw new Exception("Complete todos los campos");
	       }
		}	
		
		return true;
	}
}
