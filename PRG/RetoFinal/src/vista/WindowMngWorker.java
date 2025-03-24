package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.Worker;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class WindowMngWorker extends JDialog implements ActionListener {

	private LoginControlador cont;
	private Worker worker;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_user;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnGoBack;
	private JButton btnDelete;
	private JComboBox <String> comboBoxWorkers;
	private Component comboBoxWorkPlace;
	private Map<String, Worker> workers;

	public WindowMngWorker(VentanaPrincipal ventanaPrincipal, LoginControlador cont, Worker worker) {
		super(ventanaPrincipal, true);
		this.cont = cont;
		this.worker = worker;
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBoxWorkers = new JComboBox<String>();
		comboBoxWorkers.setBounds(50, 50, 200, 40);
		contentPanel.add(comboBoxWorkers);

		btnGoBack = new JButton("RETURN");
		btnGoBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnGoBack.setBounds(552, 475, 150, 55);
		contentPanel.add(btnGoBack);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(84, 475, 150, 55);
		contentPanel.add(btnDelete);

		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setBounds(410, 50, 320, 401);
		contentPanel.add(panelDatos);

		JLabel lblUserName = new JLabel("Name: ");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(10, 87, 77, 21);
		panelDatos.add(lblUserName);

		textField_user = new JTextField();
		textField_user.setEnabled(false);
		textField_user.setColumns(10);
		textField_user.setBounds(126, 88, 170, 20);
		panelDatos.add(textField_user);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 142, 77, 21);
		panelDatos.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		passwordField.setBounds(126, 143, 170, 20);
		panelDatos.add(passwordField);

		comboBoxWorkPlace = new JComboBox<String>();
		comboBoxWorkPlace.setBounds(126, 193, 170, 20);
		panelDatos.add(comboBoxWorkPlace);

		JLabel lblDealer = new JLabel("Dealership");
		lblDealer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDealer.setBounds(10, 196, 77, 21);
		panelDatos.add(lblDealer);

		JLabel lblAdmin = new JLabel("Admin:");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdmin.setBounds(10, 246, 77, 21);
		panelDatos.add(lblAdmin);

		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModify.setBounds(2, 10, 150, 33);
		panelDatos.add(btnModify);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(163, 10, 150, 33);
		panelDatos.add(btnUpdate);

		JRadioButton rdbtnYes = new JRadioButton("YES");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(126, 247, 80, 20);
		panelDatos.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("NO");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(216, 247, 80, 20);
		panelDatos.add(rdbtnNo);
		
		loadWorker();

	}

	public void loadWorker() {
		workers = cont.getCoWorkers(worker);

		if (!workers.isEmpty()) {

			comboBoxWorkers.addItem("New Worker");
			
			for (Worker w : workers.values()) {
				comboBoxWorkers.addItem(w.getUser());
			}
		}
		comboBoxWorkers.setSelectedIndex(-1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == btnGoBack) {
			
			this.dispose();
			
		}
		
	}

}
