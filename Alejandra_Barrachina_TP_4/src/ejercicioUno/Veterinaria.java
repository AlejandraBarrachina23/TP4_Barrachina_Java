package ejercicioUno;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import javax.swing.JTable;

public class Veterinaria extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlPrincipal;
	private JTextField tboxNombre;
	private JTextField tboxEdad;
	private JTable tblListadoMascotas;

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

	public Veterinaria() {
		setTitle("Veterinaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 501);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDatos.setBounds(10, 11, 414, 168);
		pnlPrincipal.add(pnlDatos);
		pnlDatos.setLayout(null);
		
		tboxNombre = new JTextField();
		tboxNombre.setBounds(140, 25, 159, 20);
		pnlDatos.add(tboxNombre);
		tboxNombre.setColumns(10);
		
		tboxEdad = new JTextField();
		tboxEdad.setBounds(140, 61, 159, 20);
		pnlDatos.add(tboxEdad);
		tboxEdad.setColumns(10);
		
		JLabel lblEdadl = new JLabel("Edad");
		lblEdadl.setBounds(46, 64, 46, 14);
		pnlDatos.add(lblEdadl);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 28, 46, 14);
		pnlDatos.add(lblNombre);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(46, 99, 46, 14);
		pnlDatos.add(lblSexo);
		
		JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		rdbtnHembra.setBounds(140, 102, 97, 23);
		pnlDatos.add(rdbtnHembra);
		
		JRadioButton rdbtnMacho = new JRadioButton("Macho");
		rdbtnMacho.setBounds(239, 102, 69, 23);
		pnlDatos.add(rdbtnMacho);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(315, 134, 89, 23);
		pnlDatos.add(btnAgregar);
		
		tblListadoMascotas = new JTable();
		tblListadoMascotas.setBounds(10, 190, 414, 261);
		pnlPrincipal.add(tblListadoMascotas);
	}
}
