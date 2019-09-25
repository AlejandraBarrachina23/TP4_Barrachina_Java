package ejercicioUno;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Utilidades {

	public static String RadioButtonSeleccionado(JPanel panelContenedor) {
		
		String itemsSeleccionado="";
		for (Component Componente : panelContenedor.getComponents()) {
			
			if(Componente instanceof JRadioButton) {
				
				if(((JRadioButton) Componente).isSelected()) itemsSeleccionado+= " " + ((JRadioButton) Componente).getText();
			}
		}
		return itemsSeleccionado;
	}
	
	public static int Confirmacion(String pregunta) {
		 
		 return JOptionPane.showConfirmDialog (null, pregunta ,"Advertencia", JOptionPane.YES_NO_OPTION);
		 
	}
}
