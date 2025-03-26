package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.CarDealership;
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
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_user;
	private JPasswordField passwordField;

	private JButton btnGoBack;
	private JButton btnDelete;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;

	private JComboBox<String> comboBoxWorkers;
	private JComboBox<String> comboBoxWorkPlace;

	private Worker worker;
	private Map<String, Worker> workers;
	private CarDealership dealer;
	private Map<String, CarDealership> dealers;

	private JButton btnShowPass;
	boolean visible = false;

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
		passwordField.setBounds(126, 143, 115, 20);
		panelDatos.add(passwordField);

		btnShowPass = new JButton("show");
		btnShowPass.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnShowPass.setBounds(246, 143, 50, 20);
		panelDatos.add(btnShowPass);
		btnShowPass.addActionListener(this);

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

		rdbtnYes = new JRadioButton("YES");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(126, 247, 80, 20);
		panelDatos.add(rdbtnYes);

		rdbtnNo = new JRadioButton("NO");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(216, 247, 80, 20);
		panelDatos.add(rdbtnNo);

		loadWorker();
		setupListeners();

	}

	public void loadWorker() {
		workers = cont.getWorkers();

		if (!workers.isEmpty()) {

			comboBoxWorkers.addItem("New Worker");

			for (Worker w : workers.values()) {
				comboBoxWorkers.addItem(w.getUser());
			}
		}
		comboBoxWorkers.setSelectedIndex(-1);
	}

	public void loadDealer(Worker worker) {

		int index = 0;

		dealer = cont.getWorkingPlace(worker);

		dealers = cont.getAllDeals();

		comboBoxWorkPlace.removeAllItems();

		if (!dealers.isEmpty()) {

			for (CarDealership c : dealers.values()) {
				comboBoxWorkPlace.addItem(c.getName());

				if (c.getId() == dealer.getId()) {

					comboBoxWorkPlace.setSelectedIndex(index);

				}
				index++;
			}

		}

	}

	// Setups the listener in the comboBox so it can detect changes
	public void setupListeners() {
		comboBoxWorkers.addActionListener(new ActionListener() {
			// when a change is detected,gets the selected item and calls the funcion update
			// Field
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedWorker;
				Worker worker;
				selectedWorker = (String) comboBoxWorkers.getSelectedItem();

				if (selectedWorker != null && workers.containsKey(selectedWorker)) {

					worker = workers.get(selectedWorker);

					updateFields(worker);
				} else {
					updateFields(null);
				}
			}
		});
	}

	private void updateFields(Worker selectedWorker) {

		if (selectedWorker != null) {
			textField_user.setText(selectedWorker.getUser());
			passwordField.setText(selectedWorker.getPassword());
			// comboBoxWorkPlace

			loadDealer(selectedWorker);

			if (selectedWorker.isAdmin()) {
				rdbtnYes.setSelected(true);
			} else {
				rdbtnNo.setSelected(true);
			}

		} else {

			textField_user.setText("");
			passwordField.setText("");

			comboBoxWorkPlace.setSelectedIndex(-1);

			rdbtnYes.setSelected(false);

			rdbtnNo.setSelected(false);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGoBack) {

			this.dispose();

		}

		if (e.getSource() == btnShowPass) {

			if (visible) {
			    passwordField.setEchoChar('\u2022'); // Ocultar texto
				
			} else {
				passwordField.setEchoChar((char) 0); // Mostrar texto
			}

			visible = !visible; // Alternar el estado

		}

	}
}
