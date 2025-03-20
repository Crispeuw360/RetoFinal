package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.Model;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class VentanaPrincipal extends JFrame {
	private LoginControlador cont;
	private JButton btnSell;
	private JButton btnModifyCars;
	private JButton btnDelete;
	private JLabel lblDealership;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmDealership;
	private JMenuItem mntmAdmin;
	
	private Map<String, Model> models;

	public VentanaPrincipal(LoginControlador cont) {
		this.cont = cont;
		setBackground(new Color(181, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList list = new JList();
		list.setBounds(470, 75, 275, 350);
		contentPane.add(list);

		btnModifyCars = new JButton("MODIFY");
		btnModifyCars.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnModifyCars.setBounds(318, 475, 150, 55);
		contentPane.add(btnModifyCars);

		btnSell = new JButton("SELL");
		btnSell.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnSell.setBounds(84, 475, 150, 55);
		contentPane.add(btnSell);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(552, 475, 150, 55);
		contentPane.add(btnDelete);

		JLabel lblCarInfo = new JLabel("New label");
		lblCarInfo.setBounds(40, 128, 319, 192);
		contentPane.add(lblCarInfo);

		lblDealership = new JLabel("WELCOME TO nombrecO");
		lblDealership.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealership.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblDealership.setBounds(40, 10, 705, 55);
		contentPane.add(lblDealership);

		menuBar = new JMenuBar();
		menuBar.setBounds(676, 10, 100, 40);
		contentPane.add(menuBar);

		mnNewMenu = new JMenu("Username");
		mnNewMenu.setPreferredSize(new Dimension(100, 30)); // Makes the JMenu bigger
		menuBar.add(mnNewMenu);

		mntmDealership = new JMenuItem("Concesionario:");
		mnNewMenu.add(mntmDealership);

		mntmAdmin = new JMenuItem("Admin");
		mnNewMenu.add(mntmAdmin);
		
		

		
/*
		public void cargarsuarios() {
			usuarios = cont.getUsuarios();

			if (!usuarios.isEmpty()) {

				for (Usuario u : usuarios.values()) {
					comboBox.addItem(u.getNombre());
				}
			}
			comboBox.setSelectedIndex(-1);
		}
*/

	}
	
	public void loadModel() {
		models = cont.getModels();
		
		if(!models.isEmpty()) {
			
		}
		
	}
	
}
