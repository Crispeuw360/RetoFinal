package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.CarDealership;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaCrearUsuario extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private LoginControlador cont;

	
	public VentanaCrearUsuario(JFrame parent,CarDealership cardealer,LoginControlador cont) 
	{
		super(parent, true);
		setTitle("Create New User");
		this.cont = cont;
		setSize(600, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("New User");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(208, 10, 156, 33);
		contentPanel.add(lblTitle);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
