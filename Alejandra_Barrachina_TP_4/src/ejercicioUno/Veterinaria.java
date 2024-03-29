package ejercicioUno;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Veterinaria extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlPrincipal;
	private JTextField tboxNombre;
	private JTextField tboxEdad;
	private JTable tblListadoMascotas;
	private DefaultTableModel ModeloDeTabla;
	private ButtonGroup grupoRadioButtons = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Veterinaria frame = new Veterinaria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Veterinaria() throws SQLException {
		
		setTitle("Veterinaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ValidarSoloNumeros ValidarNumeros = new ValidarSoloNumeros();
		ValidarSoloLetras ValidarLetras = new ValidarSoloLetras();
		setBounds(100, 100, 450, 501);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBounds(10, 11, 414, 168);
		pnlDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrincipal.add(pnlDatos);
		pnlDatos.setLayout(null);
		tboxNombre = new JTextField();
		tboxNombre.setBounds(140, 25, 159, 20);
		pnlDatos.add(tboxNombre);
		tboxNombre.setColumns(10);
		tboxNombre.addKeyListener(ValidarLetras);
		tboxEdad = new JTextField();
		tboxEdad.setBounds(140, 61, 159, 20);
		tboxEdad.addKeyListener(ValidarNumeros);
		pnlDatos.add(tboxEdad);
		tboxEdad.setColumns(10);
		JLabel lblEdadl = new JLabel("Edad");
		lblEdadl.setBounds(46, 64, 46, 14);
		pnlDatos.add(lblEdadl);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 28, 46, 14);
		pnlDatos.add(lblNombre);
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(46, 106, 46, 14);
		pnlDatos.add(lblSexo);		
		JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		rdbtnHembra.setSelected(true);
		rdbtnHembra.setBounds(140, 102, 97, 23);
		pnlDatos.add(rdbtnHembra);
		JRadioButton rdbtnMacho = new JRadioButton("Macho");
		rdbtnMacho.setBounds(239, 102, 69, 23);
		pnlDatos.add(rdbtnMacho);
		grupoRadioButtons.add(rdbtnHembra);
		grupoRadioButtons.add(rdbtnMacho);
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(315, 134, 89, 23);
		pnlDatos.add(btnAgregar);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 414, 261);
		pnlPrincipal.add(scrollPane);
		tblListadoMascotas = new JTable();
		tblListadoMascotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblListadoMascotas);
		tblListadoMascotas.setDefaultEditor(Object.class, null);
		DAO AccesoDatos = new DAO();
		tblListadoMascotas.setModel((DefaultTableModel)AccesoDatos.SetearTabla(AccesoDatos.ListarMascotas()));

		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					
					Validar.CampoVacio(pnlDatos);
					Mascota unaMascota = new Mascota();
					unaMascota.setearMascota(tboxNombre.getText(), tboxEdad.getText(), Utilidades.RadioButtonSeleccionado(pnlDatos));
					AccesoDatos.AgregarMascota(unaMascota); 
					tblListadoMascotas.setModel((DefaultTableModel)AccesoDatos.SetearTabla(AccesoDatos.ListarMascotas()));
					tboxEdad.setText("");
					tboxNombre.setText("");
				} 
				
				catch (Exception e) {
				
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
					
			}
		});
		
		tblListadoMascotas.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				
				if(Utilidades.Confirmacion("�Seguro que desea eliminar este registro?")==0) {
					
					try {
						
						Mascota MascotaEliminar = new Mascota();
						int filaSeleccionada = tblListadoMascotas.rowAtPoint(e.getPoint());
						MascotaEliminar.setID(Integer.parseInt(tblListadoMascotas.getValueAt(filaSeleccionada, 0).toString()));
						AccesoDatos.EliminarMascota(MascotaEliminar);
						tblListadoMascotas.setModel((DefaultTableModel)AccesoDatos.SetearTabla(AccesoDatos.ListarMascotas()));
					} 
					
					catch (SQLException excepcion) {
						
						excepcion.printStackTrace();
					}
					
				}
			}
		});
		
	}
}

class ValidarSoloNumeros extends KeyAdapter{
	  
	public void keyTyped(KeyEvent e) {
		
		char validar = e.getKeyChar();
		if(Character.isLetter(validar)) {
			e.consume();
			JOptionPane.showMessageDialog(null, "Solo se permiten numeros");
		} 
	}
}

class ValidarSoloLetras extends KeyAdapter{
  
public void keyTyped(KeyEvent e) {
	
	char validar = e.getKeyChar();
	if(!Character.isLetter(validar)) {
		e.consume();
		JOptionPane.showMessageDialog(null, "Solo se permiten letras");
	} 
}

}
