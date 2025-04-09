package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.*;
import model.Worker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class WindowLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usu;
	private JPasswordField passwd;
	private JButton btnAceptar;
	private LoginController cont;
	private JButton btnShowPassword;
    private boolean passwordVisible = false;
   

	public WindowLogin(LoginController cont) {
		this.cont = cont;
        setUndecorated(true);
        setBackground(new Color(44, 44, 44));
        getRootPane().setBorder(new LineBorder(new Color(30, 30, 30), 10));
        setResizable(false);
        setTitle("Login");
        setSize(600, 300);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(55, 55, 55));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("LOGIN");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(200, 27, 200, 40);
        contentPane.add(lblTitulo);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(150, 102, 130, 25);
        contentPane.add(lblUser);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setBounds(150, 155, 130, 25);
        contentPane.add(lblContrasena);

        usu = new JTextField();
        usu.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        usu.setBounds(290, 104, 192, 21);
        usu.setBackground(new Color(80, 80, 80));
        usu.setForeground(Color.WHITE);
        usu.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
        contentPane.add(usu);

        passwd = new JPasswordField();
        passwd.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        passwd.setBounds(290, 157, 136, 21);
        passwd.setBackground(new Color(80, 80, 80));
        passwd.setForeground(Color.WHITE);
        passwd.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
        contentPane.add(passwd);

        btnShowPassword = new JButton("show");
        btnShowPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 8));
        btnShowPassword.setBounds(430, 157, 52, 21);
        btnShowPassword.setFocusPainted(false);
        btnShowPassword.setBorderPainted(false);
        btnShowPassword.setBackground(new Color(150, 0, 0));
        btnShowPassword.setForeground(Color.WHITE);
        btnShowPassword.addActionListener(this);
        contentPane.add(btnShowPassword);

        btnAceptar = new JButton("ACEPTAR");
        btnAceptar.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        btnAceptar.setBounds(244, 225, 112, 25);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setBackground(new Color(150, 0, 0));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.addActionListener(this);
        contentPane.add(btnAceptar);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
        btnSalir.setBounds(488, 38, 63, 25);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setBackground(new Color(150, 0, 0));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.addActionListener(e -> System.exit(0));
        contentPane.add(btnSalir);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
	    String usuario, password;
	    if (e.getSource() == btnShowPassword) {
	        togglePasswordVisibility();
	    }
	    
	    else if (e.getSource() == btnAceptar) {
	        // Get username from text field
	        usuario = usu.getText();  

	        // Get password as char array (more secure than String)
	        char[] passwordChars = passwd.getPassword();  

	        // Convert to String (not recommended for security)
	        password = new String(passwordChars);  

	        // Clean the original array to prevent memory exposure
	        java.util.Arrays.fill(passwordChars, ' ');  

	        if (!usuario.isEmpty() && !password.isEmpty()) {
	            Worker worker = new Worker( usuario, password,false, 1); // Initially, we don't know if admin
	            Worker foundWorker = cont.checkWorker(worker); // Enters worker with admin and cardealer wrong, comes out right

	            if (foundWorker != null) {

	                // We verify if the user is administrator

	                WindowMain ven = new WindowMain(cont, foundWorker);
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
	/**
	 * Toggles the password visibility between visible and hidden states
	 */
	private void togglePasswordVisibility() {
	    // Toggle the visibility flag
	    passwordVisible = !passwordVisible;
	    
	    if (passwordVisible) {
	        // Show password in plain text
	        char[] password = passwd.getPassword();
	        passwd.setEchoChar((char)0);  // Set echo char to 0 to show characters
	        passwd.setText(new String(password));
	    } else {
	        // Hide password by showing bullet points
	        // Security note: We don't store plain text, we just change the echo char
	        passwd.setEchoChar('•');  // Set echo char to bullet point character
	    }
	}
}