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
import javax.swing.JOptionPane;

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
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

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
	private JButton btnUpdate;
	private JButton btnModify;
	private JLabel lblWarning;

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
		btnGoBack.addActionListener(this);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(84, 475, 150, 55);
		contentPanel.add(btnDelete);
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(this);
		btnDelete.setToolTipText("You cant delete a non existing worker");

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
		comboBoxWorkPlace.setEnabled(false);
		panelDatos.add(comboBoxWorkPlace);

		JLabel lblDealer = new JLabel("Dealership");
		lblDealer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDealer.setBounds(10, 196, 77, 21);
		panelDatos.add(lblDealer);

		JLabel lblAdmin = new JLabel("Admin:");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdmin.setBounds(10, 246, 77, 21);
		panelDatos.add(lblAdmin);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModify.setBounds(2, 10, 150, 33);
		panelDatos.add(btnModify);
		btnModify.addActionListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(163, 10, 150, 33);
		panelDatos.add(btnUpdate);
		btnUpdate.addActionListener(this);
		btnUpdate.setToolTipText("You must modify first");
		ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
		toolTipManager.setInitialDelay(0); // Show tooltip immediately

		rdbtnYes = new JRadioButton("YES");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(126, 247, 80, 20);
		panelDatos.add(rdbtnYes);
		rdbtnYes.setEnabled(false);

		rdbtnNo = new JRadioButton("NO");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(216, 247, 80, 20);
		panelDatos.add(rdbtnNo);
		rdbtnNo.setEnabled(false);

		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWarning.setBounds(14, 316, 292, 21);
		panelDatos.add(lblWarning);

		loadWorker();
		setupListeners();

	}

	public void loadWorker() {
		workers = cont.getWorkers();

		comboBoxWorkers.removeAllItems();

		if (!workers.isEmpty()) {

			comboBoxWorkers.addItem("New Worker");

			for (Worker w : workers.values()) {
				comboBoxWorkers.addItem(w.getUser());
			}
		}
		comboBoxWorkers.setSelectedIndex(-1);
	}

	public void loadDealer(Worker worker) {

		boolean existingWorker = false;
		int index = 0;

		if (worker != null) {

			dealer = cont.getWorkingPlace(worker);
			existingWorker = true;
		}

		dealers = cont.getAllDeals();

		comboBoxWorkPlace.removeAllItems();

		if (!dealers.isEmpty()) {

			for (CarDealership c : dealers.values()) {
				comboBoxWorkPlace.addItem(c.getName());

				if (existingWorker) {
					if (c.getId() == dealer.getId()) {
						comboBoxWorkPlace.setSelectedIndex(index);
					}
				}

				index++;
			}

			if (!existingWorker) {
				comboBoxWorkPlace.setSelectedIndex(-1);
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
				String workerName;
				Worker selectedWorker;
				workerName = (String) comboBoxWorkers.getSelectedItem();

				if (workerName != null && workers.containsKey(workerName)) {

					selectedWorker = workers.get(workerName);

					updateFields(selectedWorker);
					toggleFields(false);

					lblWarning.setText("");
					btnUpdate.setText("Update");

					if (selectedWorker.getUser().equals(worker.getUser())) {
						btnDelete.setEnabled(false);
						btnDelete.setToolTipText("You can't delete yourself");

					} else {
						btnDelete.setEnabled(true);
						btnDelete.setToolTipText(null);
					}

				} else if (workerName == "New Worker") {

					updateFields(null);
					toggleFields(false);

					lblWarning.setText("");
					textField_user.setEnabled(false);
					btnDelete.setEnabled(false);
					btnDelete.setToolTipText("You cant delete a non existing worker");
					btnUpdate.setText("Create");

				}
			}
		});
	}

	private void toggleFields(boolean enable) {
		ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
		toolTipManager.setInitialDelay(0); // Show tooltip immediately

		passwordField.setEnabled(enable);
		comboBoxWorkPlace.setEnabled(enable);
		rdbtnNo.setEnabled(enable);
		rdbtnYes.setEnabled(enable);
		btnUpdate.setEnabled(enable);

		if (enable) {
			btnUpdate.setToolTipText(null);
		} else {
			btnUpdate.setToolTipText("You must modify first");
		}

	}

	private void updateFields(Worker selectedWorker) {

		if (selectedWorker != null) {
			textField_user.setEnabled(false);
			toggleFields(false);
			textField_user.setText(selectedWorker.getUser());
			passwordField.setText(selectedWorker.getPassword());

			loadDealer(selectedWorker);

			if (selectedWorker.isAdmin()) {
				rdbtnYes.setSelected(true);
				rdbtnNo.setSelected(false);
			} else {
				rdbtnNo.setSelected(true);
				rdbtnYes.setSelected(false);
			}

		} else {

			textField_user.setText("");
			passwordField.setText("");

			loadDealer(selectedWorker);

			buttonGroup.remove(rdbtnYes);
			buttonGroup.remove(rdbtnNo);

			rdbtnYes.setSelected(false);
			rdbtnNo.setSelected(false);

			buttonGroup.add(rdbtnYes);
			buttonGroup.add(rdbtnNo);

		}
	}

	private Worker getFields() {

		String userName, password;
		Boolean admin = null;
		int dealerId;

		// Check userName
		userName = textField_user.getText().trim();
		if (userName.equals("")) {
			JOptionPane.showMessageDialog(this, "Write a valid username", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Check password
		password = new String(passwordField.getPassword());
		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Write a valid password", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Check dealer
		if (comboBoxWorkPlace.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Select a Dealership", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		dealerId = comboBoxWorkPlace.getSelectedIndex() + 1;

		// Check rdButons
		if (buttonGroup.getSelection() != null) {
			if (rdbtnYes.isSelected())
				admin = true;
			else if (rdbtnNo.isSelected())
				admin = false;
		} else {

			JOptionPane.showMessageDialog(this, "Select if the worker is an Admin", "Error", JOptionPane.ERROR_MESSAGE);

			return null;
		}

		Worker modifiedWorker = new Worker(admin, userName, password, dealerId);

		return modifiedWorker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnGoBack) {

			this.dispose();

		}

		if (e.getSource() == btnDelete) {

			String userName = (String) comboBoxWorkers.getSelectedItem();

			Worker selectedWorker = cont.getWorker(userName);

			if (selectedWorker != null && !worker.getUser().equals(selectedWorker.getUser())) {

				// Confirm deleteDialog
				int opcion = JOptionPane.showConfirmDialog(this,
						(String) "Warning: Are you sure you want to delete this worker?", "Confirm deletion",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);

				if (opcion == JOptionPane.YES_OPTION)
					if (cont.deleteWorker(selectedWorker)) {
						loadWorker();
						lblWarning.setText("Worker was successfully fired");

					} else
						lblWarning.setText("SQL PROBLEM");
				else if (opcion == JOptionPane.NO_OPTION)
					lblWarning.setText("Deletion canceled");
				else
					lblWarning.setText("Deletion canceled");

			} else if (worker.getUser().equals(selectedWorker.getUser()))
				JOptionPane.showMessageDialog(this, "You can't delete yourself", "Error", JOptionPane.ERROR_MESSAGE);

		}

		if (e.getSource() == btnShowPass) {

			if (visible) {
				passwordField.setEchoChar('\u2022'); // Hide password

			} else {
				passwordField.setEchoChar((char) 0); // Show password
			}

			visible = !visible; // change the boolean

		}

		if (e.getSource() == btnModify) {
			if (comboBoxWorkers.getSelectedIndex() == -1)
				JOptionPane.showMessageDialog(this, "Select a worker first", "Error", JOptionPane.ERROR_MESSAGE);
			else
				toggleFields(true);
			if (comboBoxWorkers.getSelectedIndex() == 0)
				textField_user.setEnabled(true);

		}

		if (e.getSource() == btnUpdate) {

			if (btnUpdate.getText() == "Update") {
				Worker editedWorker = getFields();

				if (editedWorker != null)
					if (cont.modifyWorker(editedWorker)) {
						loadWorker();

						JOptionPane.showMessageDialog(this, editedWorker.getUser() + " was successfully edited",
								"Success", JOptionPane.INFORMATION_MESSAGE);

					}
			} else if (btnUpdate.getText() == "Create") {

				Worker createWorker = getFields();

				if (createWorker != null)
					if (cont.getWorker(createWorker.getUser()) == null) {
						if (cont.createWorker(createWorker)) {
							loadWorker();
							JOptionPane.showMessageDialog(this, createWorker.getUser() + " was successfully created",
									"Success", JOptionPane.INFORMATION_MESSAGE);
						}
					} else
						JOptionPane.showMessageDialog(this, createWorker.getUser() + " already exists", "Error",
								JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == btnGoBack) {

			// Update worker to see changes in the mainWindow
			worker = cont.getWorker(worker.getUser());

			VentanaPrincipal win = new VentanaPrincipal(cont, worker);
			win.setVisible(true);
			this.dispose();

		}
	}

}
