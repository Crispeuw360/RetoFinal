package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.*;
import model.*;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

public class WindowCreateModel extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldMark;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private LoginController cont;
	private Worker worker;
	private Map<String, Model> modelList;
	private Map<String, CarDealership> carDealerships;
	private JComboBox<String> comboBoxId;
	private JButton btnCreate;
	private JButton btnBack;

	public WindowCreateModel(JFrame parent, LoginController cont, Worker worker) {
		super(parent, true);
		this.cont = cont;
		this.worker = worker;
		setUndecorated(true);
		setBackground(new Color(44, 44, 44));
		getRootPane().setBorder(new LineBorder(new Color(30, 30, 30), 10));
		setResizable(false);
		setTitle("Create Model");
		setSize(600, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(44, 44, 44));

		contentPanel.setBackground(new Color(55, 55, 55));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitulo = new JLabel("CREATE");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(192, 10, 201, 58);
		contentPanel.add(lblTitulo);

		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(150, 0, 0)));
		panelDatos.setBackground(new Color(70, 70, 70));
		panelDatos.setBounds(64, 78, 323, 289);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);

		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(10, 48, 77, 21);
		panelDatos.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldName.setBounds(126, 51, 170, 20);
		textFieldName.setBackground(new Color(80, 80, 80));
		textFieldName.setForeground(Color.WHITE);
		textFieldName.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldName);

		JLabel lblMark = new JLabel("Mark");
		lblMark.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMark.setForeground(Color.WHITE);
		lblMark.setBounds(10, 98, 77, 21);
		panelDatos.add(lblMark);

		textFieldMark = new JTextField();
		textFieldMark.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldMark.setBounds(126, 101, 170, 20);
		textFieldMark.setBackground(new Color(80, 80, 80));
		textFieldMark.setForeground(Color.WHITE);
		textFieldMark.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldMark);

		JLabel lblStock = new JLabel("Stock: ");
		lblStock.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblStock.setForeground(Color.WHITE);
		lblStock.setBounds(10, 147, 77, 21);
		panelDatos.add(lblStock);

		textFieldStock = new JTextField();
		textFieldStock.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldStock.setBounds(126, 150, 170, 20);
		textFieldStock.setBackground(new Color(80, 80, 80));
		textFieldStock.setForeground(Color.WHITE);
		textFieldStock.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldStock);

		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(10, 198, 77, 21);
		panelDatos.add(lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldPrice.setBounds(126, 201, 170, 20);
		textFieldPrice.setBackground(new Color(80, 80, 80));
		textFieldPrice.setForeground(Color.WHITE);
		textFieldPrice.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldPrice);

		JLabel lblid = new JLabel("ID: ");
		lblid.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblid.setForeground(Color.WHITE);
		lblid.setBounds(10, 242, 77, 21);
		panelDatos.add(lblid);

		comboBoxId = new JComboBox<>();
		comboBoxId.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		comboBoxId.setBackground(new Color(55, 55, 55));
		comboBoxId.setForeground(Color.WHITE);
		comboBoxId.setBounds(126, 244, 170, 21);
		panelDatos.add(comboBoxId);
		comboBoxId.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (isSelected) {
					setBackground(new Color(211, 47, 47));// red
					setForeground(Color.WHITE);
				}
				return this;
			}
		});

		btnCreate = new JButton("CREATE");
		btnCreate.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		btnCreate.setBackground(new Color(150, 0, 0));
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnCreate.setBounds(430, 324, 115, 43);
		btnCreate.setFocusPainted(false);
		btnCreate.setBorderPainted(false);
		contentPanel.add(btnCreate);
		btnCreate.addActionListener(this);

		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		btnBack.setBackground(new Color(150, 0, 0));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnBack.setBounds(430, 56, 115, 43);
		btnBack.setFocusPainted(false);
		btnBack.setBorderPainted(false);
		contentPanel.add(btnBack);
		btnBack.addActionListener(this);

		loadCarDealership();
	}

	public void loadCarDealership() {
		carDealerships = cont.getAllDeals();
		comboBoxId.removeAllItems();

		if (!carDealerships.isEmpty()) {
			for (CarDealership c : carDealerships.values()) {
				// Añadir el objeto completo al ComboBox
				comboBoxId.addItem(c.getName());
			}
		}
		comboBoxId.setSelectedIndex(0);
	}

	public void actionPerformed(ActionEvent e) {
		int stock;
		float price;
		if (btnCreate == e.getSource()) {
			// First validate empty fields
			if (textFieldName.getText().trim().isEmpty() || textFieldMark.getText().trim().isEmpty()
					|| textFieldStock.getText().trim().isEmpty() || textFieldPrice.getText().trim().isEmpty()) {

				JOptionPane.showMessageDialog(this, "Fill in all fields");
				return;
			}

			// Validate that stock is a positive integer

			try {
				stock = Integer.parseInt(textFieldStock.getText());
				if (stock < 0) {
					JOptionPane.showMessageDialog(this, "Stock must be a positive number");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Stock must be an integer");
				return;
			}

			// Validate that price is a positive decimal number

			try {
				price = Float.parseFloat(textFieldPrice.getText());
				if (price <= 0) {
					JOptionPane.showMessageDialog(this, "Price must be a positive number");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Price must be a valid number");
				return;
			}

			// If everything is correct, proceed with creation
			String nameDealership = (String) comboBoxId.getSelectedItem();
			String name = textFieldName.getText();
			CarDealership cardealer = cont.getDealership(nameDealership);

			if (!checkModelList(cardealer, name)) {
				Model model = new Model(name, textFieldMark.getText(), stock, price, cardealer.getId());
				if (cont.createModel(model)) {
					JOptionPane.showMessageDialog(this, "Created successfully");

					textFieldName.setText("");
					textFieldMark.setText("");
					textFieldStock.setText("");
					textFieldPrice.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Error creating model");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Model already exists");

			}
		} else if (btnBack == e.getSource()) {
			dispose();
			WindowMain principal = new WindowMain(cont, worker);
			principal.setVisible(true);
		}
	}

	public boolean checkModelList(CarDealership cardealer, String name) {
		boolean found = false;
		// calls the method of the LoginController to get all the clients to the
		// checkbox
		modelList = cont.getModels(cardealer);
		if (!modelList.isEmpty()) {
			for (Model d : modelList.values()) {
				if (d.getName_model().equalsIgnoreCase(name)) {
					found = true;
				}
			}
		}
		return found;
	}
}
