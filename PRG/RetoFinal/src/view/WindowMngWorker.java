package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controller.LoginController;
import model.CarDealership;
import model.Worker;

public class WindowMngWorker extends JDialog implements ActionListener {

	private LoginController cont;
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
	private JLabel lblTitulo;

	public WindowMngWorker(WindowMain ventanaPrincipal, LoginController cont, Worker worker) {
		super(ventanaPrincipal, true);
		this.cont = cont;
		this.worker = worker;

		// WNDW CONFIG
		setUndecorated(true);
		setBackground(new Color(44, 44, 44));
		getRootPane().setBorder(new LineBorder(new Color(30, 30, 30), 10));
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(44, 44, 44));

		contentPanel.setBackground(new Color(55, 55, 55));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTitulo = new JLabel("MANAGE WORKERS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(277, 10, 201, 58);
		contentPanel.add(lblTitulo);

		// COMBOBOX
		comboBoxWorkers = new JComboBox<String>();
		comboBoxWorkers.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		comboBoxWorkers.setBackground(new Color(55, 55, 55));
		comboBoxWorkers.setForeground(Color.WHITE);
		comboBoxWorkers.setBounds(50, 108, 201, 41);
		comboBoxWorkers.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (isSelected) {
					setBackground(new Color(211, 47, 47));//red
					setForeground(Color.WHITE);
				} 
				return this;
			}
		});
		contentPanel.add(comboBoxWorkers);

		// FORMS
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(150, 0, 0)));
		panelDatos.setBackground(new Color(70, 70, 70));
		panelDatos.setBounds(432, 88, 323, 401);
		panelDatos.setLayout(null);
		contentPanel.add(panelDatos);

		JLabel lblUserName = new JLabel("Name: ");
		lblUserName.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(10, 87, 77, 21);
		panelDatos.add(lblUserName);

		textField_user = new JTextField();
		textField_user.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textField_user.setEnabled(false);
		textField_user.setBounds(126, 88, 170, 20);
		textField_user.setBackground(new Color(80, 80, 80));
		textField_user.setForeground(Color.WHITE);
		textField_user.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textField_user);
		textField_user.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(10, 142, 77, 21);
		panelDatos.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		passwordField.setEnabled(false);
		passwordField.setBounds(126, 143, 115, 20);
		passwordField.setBackground(new Color(80, 80, 80));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(passwordField);

		btnShowPass = new JButton("show");
		btnShowPass.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		btnShowPass.setBounds(246, 143, 50, 20);
		btnShowPass.setBackground(new Color(211, 47, 47));
		btnShowPass.setForeground(Color.WHITE);
		btnShowPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnShowPass.setFocusPainted(false);
		btnShowPass.setBorderPainted(false);
		panelDatos.add(btnShowPass);
		btnShowPass.addActionListener(this);

		comboBoxWorkPlace = new JComboBox<String>();
		comboBoxWorkPlace.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		comboBoxWorkPlace.setBounds(126, 193, 170, 20);
		comboBoxWorkPlace.setEnabled(false);
		comboBoxWorkPlace.setBackground(new Color(80, 80, 80));
		comboBoxWorkPlace.setForeground(Color.WHITE);
		comboBoxWorkPlace.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		comboBoxWorkPlace.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (isSelected) {
					setBackground(new Color(211, 47, 47));//red
					setForeground(Color.WHITE);
				} 
				return this;
			}
		});
		panelDatos.add(comboBoxWorkPlace);

		JLabel lblDealer = new JLabel("Dealership");
		lblDealer.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblDealer.setForeground(Color.WHITE);
		lblDealer.setBounds(10, 196, 77, 21);
		panelDatos.add(lblDealer);

		JLabel lblAdmin = new JLabel("Admin:");
		lblAdmin.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setBounds(10, 246, 77, 21);
		panelDatos.add(lblAdmin);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		btnModify.setBounds(10, 10, 150, 33);
		btnModify.setBackground(new Color(211, 47, 47));
		btnModify.setForeground(Color.WHITE);
		btnModify.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnModify.setFocusPainted(false);
		btnModify.setBorderPainted(false);
		panelDatos.add(btnModify);
		btnModify.addActionListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(163, 10, 150, 33);
		btnUpdate.setBackground(new Color(100, 100, 100));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorderPainted(false);
		panelDatos.add(btnUpdate);
		btnUpdate.addActionListener(this);
		btnUpdate.setToolTipText("You must modify first");

		// Radio buttons
		rdbtnYes = new JRadioButton("YES");
		rdbtnYes.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rdbtnYes.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(126, 247, 80, 20);
		rdbtnYes.setEnabled(false);
		rdbtnYes.setBackground(new Color(70, 70, 70));
		panelDatos.add(rdbtnYes);

		rdbtnNo = new JRadioButton("NO");
		rdbtnNo.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		rdbtnNo.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(216, 247, 80, 20);
		rdbtnNo.setEnabled(false);
		rdbtnNo.setBackground(new Color(70, 70, 70));
		panelDatos.add(rdbtnNo);

		// LBLWARNING
		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblWarning.setForeground(Color.WHITE);
		lblWarning.setBounds(14, 316, 292, 21);
		panelDatos.add(lblWarning);

		// BOTTOM BUTTONS
		btnGoBack = new JButton("GO BACK");
		btnGoBack.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnGoBack.setBounds(10, 512, 215, 41);
		btnGoBack.setBackground(new Color(211, 47, 47));
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnGoBack.setFocusPainted(false);
		btnGoBack.setBorderPainted(false);
		contentPanel.add(btnGoBack);
		btnGoBack.addActionListener(this);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(552, 512, 215, 41);
		btnDelete.setBackground(new Color(211, 47, 47));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnDelete.setFocusPainted(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setEnabled(false);
		contentPanel.add(btnDelete);
		btnDelete.addActionListener(this);
		btnDelete.setToolTipText("You cant delete a non existing worker");

		ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
		toolTipManager.setInitialDelay(0);

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

	public void setupListeners() {
		comboBoxWorkers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String workerName = (String) comboBoxWorkers.getSelectedItem();
				btnUpdate.setText("Update");
				if (workerName != null && workers.containsKey(workerName)) {
					Worker selectedWorker = workers.get(workerName);
					updateFields(selectedWorker);
					toggleFields(false);
					lblWarning.setText("");
					

					if (selectedWorker.getUser().equals(worker.getUser())) {
						btnDelete.setEnabled(false);
						btnDelete.setToolTipText("You can't delete yourself");
					} else {
						btnDelete.setEnabled(true);
						btnDelete.setToolTipText(null);
					}
				} else if (workerName == "New Worker") {
					btnUpdate.setText("Create");
					updateFields(null);
					toggleFields(false);
					lblWarning.setText("");
					textField_user.setEnabled(false);
					btnDelete.setEnabled(false);
					btnDelete.setToolTipText("You cant delete a non existing worker");
				}
			}
		});
	}

	private void toggleFields(boolean enable) {
		ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
		toolTipManager.setInitialDelay(0);

		passwordField.setEnabled(enable);
		comboBoxWorkPlace.setEnabled(enable);
		rdbtnNo.setEnabled(enable);
		rdbtnYes.setEnabled(enable);
		btnUpdate.setEnabled(enable);

		if (enable) {
			btnUpdate.setBackground(new Color(211, 47, 47));
			btnUpdate.setToolTipText(null);
		} else {
			btnUpdate.setBackground(new Color(100, 100, 100));
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
			buttonGroup.clearSelection();
		}
	}

	private Worker getFields() {
		String userName = textField_user.getText().trim();
		if (userName.equals("")) {
			JOptionPane.showMessageDialog(this, "Write a valid username", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		String password = new String(passwordField.getPassword());
		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Write a valid password", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		if (comboBoxWorkPlace.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Select a Dealership", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		int dealerId = comboBoxWorkPlace.getSelectedIndex() + 1;

		if (buttonGroup.getSelection() == null) {
			JOptionPane.showMessageDialog(this, "Select if the worker is an Admin", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		boolean admin = rdbtnYes.isSelected();

		return new Worker( userName, password,admin, dealerId);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGoBack) {
			worker = cont.getWorker(worker.getUser());

			WindowMain win = new WindowMain(cont, worker);
			win.setVisible(true);
			this.dispose();

		}

		if (e.getSource() == btnDelete) {
			String userName = (String) comboBoxWorkers.getSelectedItem();
			Worker selectedWorker = cont.getWorker(userName);

			if (selectedWorker != null && !worker.getUser().equals(selectedWorker.getUser())) {
				int opcion = JOptionPane.showConfirmDialog(this,
						"Warning: Are you sure you want to delete this worker?", "Confirm deletion",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {
					if (cont.deleteWorker(selectedWorker)) {
						loadWorker();
						lblWarning.setText("Worker was successfully fired");
					} else {
						lblWarning.setText("SQL PROBLEM");
					}
				} else {
					lblWarning.setText("Deletion canceled");
				}
			} else if (worker.getUser().equals(selectedWorker.getUser())) {
				JOptionPane.showMessageDialog(this, "You can't delete yourself", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == btnShowPass) {
			if (visible) {
				passwordField.setEchoChar('\u2022');
			} else {
				passwordField.setEchoChar((char) 0);
			}
			visible = !visible;
		}

		if (e.getSource() == btnModify) {
			if (comboBoxWorkers.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "Select a worker first", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				toggleFields(true);
				if (comboBoxWorkers.getSelectedIndex() == 0) {
					textField_user.setEnabled(true);
				}
			}
		}

		if (e.getSource() == btnUpdate) {
			if (btnUpdate.getText().equals("Update")) {
				Worker editedWorker = getFields();
				if (editedWorker != null && cont.modifyWorker(editedWorker)) {
					loadWorker();
					JOptionPane.showMessageDialog(this, editedWorker.getUser() + " was successfully edited", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (btnUpdate.getText().equals("Create")) {
				Worker createWorker = getFields();
				if (createWorker != null) {
					if (cont.getWorker(createWorker.getUser()) == null) {
						if (cont.createWorker(createWorker)) {
							loadWorker();
							JOptionPane.showMessageDialog(this, createWorker.getUser() + " was successfully created",
									"Success", JOptionPane.INFORMATION_MESSAGE);
							
						}
					} else {
						JOptionPane.showMessageDialog(this, createWorker.getUser() + " already exists", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}