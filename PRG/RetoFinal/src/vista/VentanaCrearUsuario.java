package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.CarDealership;
import modelo.Client;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class VentanaCrearUsuario extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private LoginControlador cont;
	private JTextField textFieldUserName;
	private JTextField textFieldDni;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JButton btnCreate;
	private Map<String, Client> clientsList;
	private JLabel lblInvisible;

	
	public VentanaCrearUsuario(JFrame parent,CarDealership cardealer,LoginControlador cont) 
	{
		super(parent, true);
		setTitle("Create New User");
		this.cont = cont;
		setSize(600, 450);
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
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserName.setBounds(26, 78, 126, 33);
		contentPanel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(26, 132, 126, 33);
		contentPanel.add(lblPassword);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDni.setBounds(26, 187, 126, 33);
		contentPanel.add(lblDni);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(26, 246, 126, 33);
		contentPanel.add(lblEmail);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(177, 78, 326, 31);
		contentPanel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(177, 191, 326, 31);
		contentPanel.add(textFieldDni);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(177, 246, 326, 31);
		contentPanel.add(textFieldEmail);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 136, 326, 31);
		contentPanel.add(passwordField);
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreate.setBounds(208, 287, 156, 46);
		contentPanel.add(btnCreate);
		btnCreate .addActionListener(this);
		
		lblInvisible = new JLabel("");
		lblInvisible.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInvisible.setBounds(26, 343, 543, 46);
		contentPanel.add(lblInvisible);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnCreate) 
		{
			if(!textFieldUserName.getText().trim().isEmpty()&&!passwordField.getPassword().toString().trim().isEmpty()&&!textFieldDni.getText().trim().isEmpty()&&!textFieldEmail.getText().trim().isEmpty()) 
			{
				if(checkClientList(textFieldUserName.getText()))
				{
					
				}else
				{
					lblInvisible.setText("Nombre de usuario Ya existe, vuelve a intentarlo");
				}
			}else
			{
				lblInvisible.setText("Rellena todo los apartados");
			}
		}
	}
	public boolean checkClientList(String userName) {
		boolean encontrado = false;
		clientsList = cont.getClients();
		if (!clientsList.isEmpty()) 
		{
			for (Client c : clientsList.values()) 
			{
				if(c.getUser_().equalsIgnoreCase(userName))
				{
					encontrado = true;
				}
			}
		}
		return encontrado;
	}
}
