package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.Worker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usu;
	private JPasswordField passwd;
	private JButton btnAceptar;
	private JLabel error;
	private LoginControlador cont;

	public VentanaLogin(LoginControlador cont) {
		this.cont = cont;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblUser.setBounds(170, 45, 130, 25);
		contentPane.add(lblUser);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblContrasena.setBounds(170, 114, 130, 25);
		contentPane.add(lblContrasena);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAceptar.setBounds(244, 183, 125, 25);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);

		usu = new JTextField();
		usu.setColumns(10);
		usu.setBounds(310, 45, 150, 25);
		contentPane.add(usu);

		passwd = new JPasswordField();
		passwd.setBounds(310, 117, 150, 25);
		contentPane.add(passwd);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String usuario, password;
		if (e.getSource() == btnAceptar) {
			usuario = usu.getText();
			char[] passwordChars = passwd.getPassword();
			password = new String(passwordChars);
			java.util.Arrays.fill(passwordChars, ' '); // Limpia la contraseña en memoria

			if (!usuario.isEmpty() && !password.isEmpty()) {
				Worker worker = new Worker(false, usuario, password, 1); // Inicialmente, no sabemos si es admin
				Worker foundWorker = cont.checkWorker(worker); // Entra worker con admin y cardealer mal sale bien

				if (foundWorker != null) {

					// Verificamos si el usuario es administrador

					VentanaPrincipal ven = new VentanaPrincipal(cont, foundWorker);
					ven.setVisible(true);

					this.dispose();

				} else {
					JOptionPane.showMessageDialog(this, "Incorrect username or password");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Fill in all fields");
			}
		}
	}
}