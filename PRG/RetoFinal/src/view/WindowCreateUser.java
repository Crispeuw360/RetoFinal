package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.LoginController;
import model.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * This window allows creating new users in the system. The user needs to
 * provide a username, password, DNI, and email.
 * 
 * @author Igor Nikolaidis
 * @version 1.0
 */
public class WindowCreateUser extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	/** we create the cont for using it in the window */
	private LoginController cont;
	private JTextField textFieldUserName;
	private JTextField textFieldDni;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JButton btnCreate;
	private Map<String, Client> clientsList;
	private JLabel lblInvisible;
	private JButton btnBack;
	private JButton btnShowPass;
	private boolean visible = false;
	private ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/Logo.png"));
	private Image img = icon.getImage().getScaledInstance(80, 81, Image.SCALE_SMOOTH);
	private Worker worker;

	/**
	 * Constructor for the window Create User
	 * 
	 * @param parent    is the main/father window
	 * @param cardealer The car dealership object used in the system.
	 * @param cont      The controller that handles user authentication and
	 *                  management.
	 */
	public WindowCreateUser(JDialog parent, Worker worker, LoginController cont) {
		super(parent, true);
		this.worker = worker;
		this.cont = cont;

		setUndecorated(true); // Elimina los bordes y la barra de título
		setBackground(new Color(44, 44, 44)); // Color de fondo exterior
		getRootPane().setBorder(new LineBorder(new Color(30, 30, 30), 10));
		setResizable(false);
		setTitle("Create New User");
		setSize(600, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(44, 44, 44));
		contentPanel.setBackground(new Color(55, 55, 55));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitle = new JLabel("New User");
		lblTitle.setBounds(208, 10, 156, 33);
		lblTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		contentPanel.add(lblTitle);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(26, 112, 126, 33);
		lblUserName.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblUserName.setForeground(Color.WHITE);
		contentPanel.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 166, 126, 33);
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblPassword.setForeground(Color.WHITE);
		contentPanel.add(lblPassword);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(26, 219, 126, 33);
		lblDni.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblDni.setForeground(Color.WHITE);
		contentPanel.add(lblDni);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(26, 278, 126, 33);
		lblEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblEmail.setForeground(Color.WHITE);
		contentPanel.add(lblEmail);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(177, 112, 326, 31);
		textFieldUserName.setBackground(new Color(80, 80, 80));
		textFieldUserName.setForeground(Color.WHITE);
		textFieldUserName.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		contentPanel.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		textFieldDni = new JTextField();
		textFieldDni.setBounds(177, 225, 326, 31);
		textFieldDni.setColumns(10);
		textFieldDni.setForeground(Color.WHITE);
		textFieldDni.setBackground(new Color(80, 80, 80));
		textFieldDni.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		contentPanel.add(textFieldDni);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(177, 280, 326, 31);
		textFieldEmail.setColumns(10);
		textFieldEmail.setForeground(Color.WHITE);
		textFieldEmail.setBackground(new Color(80, 80, 80));
		textFieldEmail.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		contentPanel.add(textFieldEmail);

		passwordField = new JPasswordField();
		passwordField.setBounds(177, 170, 273, 31);
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(new Color(80, 80, 80));
		passwordField.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		contentPanel.add(passwordField);

		btnCreate = new JButton("Create");
		btnCreate.setBounds(221, 331, 156, 46);
		btnCreate.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		btnCreate.setBackground(new Color(211, 47, 47));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnCreate.setFocusPainted(false);
		btnCreate.setBorderPainted(false);
		contentPanel.add(btnCreate);
		btnCreate.addActionListener(this);

		lblInvisible = new JLabel("");
		lblInvisible.setBounds(26, 343, 543, 46);
		lblInvisible.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblInvisible.setForeground(Color.WHITE);
		contentPanel.add(lblInvisible);

		btnBack = new JButton("Go Back", new ImageIcon(img));
		btnBack.setBounds(42, 22, 74, 63);
		btnBack.setBackground(new Color(55, 55, 55));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnBack.setFocusPainted(false);
		btnBack.setBorderPainted(false);
		contentPanel.add(btnBack);
		btnBack.addActionListener(this);

		btnShowPass = new JButton("Show");
		btnShowPass.setBounds(453, 169, 56, 33);
		btnShowPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 9));
		btnShowPass.setBackground(new Color(211, 47, 47));
		btnShowPass.setForeground(Color.WHITE);
		btnShowPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnShowPass.setFocusPainted(false);
		btnShowPass.setBorderPainted(false);
		contentPanel.add(btnShowPass);
		btnShowPass.addActionListener(this);
	}

	/**
	 * actiomPerformed is to listen all the activity of the buttons in the window
	 * 
	 * @param e get the source of witch button has been pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Client cli;

		if (e.getSource() == btnCreate) {
			// Checks if the forms are not empty
			if (!textFieldUserName.getText().trim().isEmpty()
					&& !passwordField.getPassword().toString().trim().isEmpty()
					&& !textFieldDni.getText().trim().isEmpty() && !textFieldEmail.getText().trim().isEmpty()) {
				// calls the procedure
				if (!checkClientList(textFieldUserName.getText().trim())) {
					cli = new Client(textFieldDni.getText().trim(), textFieldEmail.getText().trim(),
							textFieldUserName.getText().trim(), new String(passwordField.getPassword()));
					// calls fron the LoginControlador
					if (cont.insertClient(cli)) {
						lblInvisible.setText("User created Correctly");
					} else {
						lblInvisible.setText("Error creating the user");
					}
				} else {
					lblInvisible.setText("This Username already exist, Try Again");
				}
			} else {
				lblInvisible.setText("fill all the forms");
			}
		}
		if (e.getSource() == btnShowPass) {
			if (e.getSource() == btnShowPass) {

				if (visible) {
					passwordField.setEchoChar('\u2022'); // Ocultar texto

				} else {
					passwordField.setEchoChar((char) 0); // Mostrar texto
				}

				visible = !visible; // Alternar el estado

			}
		}

		if (e.getSource() == btnBack) {

			this.dispose();
	
			
			WindowMain ven = new WindowMain(cont, worker);
			WindowPurchase win = new WindowPurchase(ven, worker, cont);
			win.setVisible(true);

		}
	}

	/**
	 * Checks all the rows of the table client, to confirm if the username already
	 * exist
	 * 
	 * @param userName checks the username provided
	 * @return True if the username exists, false otherwise.
	 */
	public boolean checkClientList(String userName) {
		boolean encontrado = false;
		// calls the method of the LoginControlador to get all the clients to the
		// checkbox
		clientsList = cont.getClients();
		if (!clientsList.isEmpty()) {
			for (Client c : clientsList.values()) {
				if (c.getUser_().equalsIgnoreCase(userName)) {
					encontrado = true;
				}
			}
		}
		return encontrado;
	}
}
