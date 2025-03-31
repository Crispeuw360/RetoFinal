package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.CarDealership;
import modelo.Model;
import modelo.Worker;

import javax.swing.JLabel;
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

public class CreateModel extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitulo;
	private JLabel lblName;
	private JTextField textFieldName;
	private JTextField textFieldMark;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private LoginControlador cont;
	private Worker worker;
	private CarDealership cardealer;
	private Map<String, Model> modelList;
	private Map<String, CarDealership> carDealerships;
	private boolean activar = false;
	private JComboBox comboBoxId;
	private JButton btnCreate;
	private JLabel error;
	private JPanel contentPane;
	private JButton btnVentanaEmergente;

	public CreateModel(/* JFrame parent, */LoginControlador cont, Worker worker) {
		/* super(parent, true); */
		this.cont = cont;
		this.worker = worker;
		setResizable(false);
		setTitle("VentanaModificar");
		setSize(600, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTitulo = new JLabel("CREATE");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(192, 10, 201, 58);
		contentPanel.add(lblTitulo);

		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDatos.setBounds(64, 78, 323, 289);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);

		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 48, 77, 21);
		panelDatos.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(126, 51, 170, 20);
		panelDatos.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblMark = new JLabel("Mark");
		lblMark.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMark.setBounds(10, 98, 77, 21);
		panelDatos.add(lblMark);

		textFieldMark = new JTextField();
		textFieldMark.setColumns(10);
		textFieldMark.setBounds(126, 101, 170, 20);
		panelDatos.add(textFieldMark);

		textFieldStock = new JTextField();
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(126, 150, 170, 20);
		panelDatos.add(textFieldStock);

		JLabel lblStock = new JLabel("Stock: ");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStock.setBounds(10, 147, 77, 21);
		panelDatos.add(lblStock);

		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(10, 198, 77, 21);
		panelDatos.add(lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(126, 201, 170, 20);
		panelDatos.add(textFieldPrice);

		JLabel lblid = new JLabel("ID: ");
		lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblid.setBounds(10, 242, 77, 21);
		panelDatos.add(lblid);

		comboBoxId = new JComboBox();
		comboBoxId.setBounds(126, 244, 77, 21);

		panelDatos.add(comboBoxId);

		btnCreate = new JButton("CREATE");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreate.setBounds(430, 324, 115, 43);
		contentPanel.add(btnCreate);
		btnCreate.addActionListener(this);

		error = new JLabel("Rellena todos los campos:");
		error.setForeground(new Color(255, 0, 0));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setFont(new Font("Tahoma", Font.BOLD, 15));
		error.setBounds(111, 377, 282, 26);
		contentPanel.add(error);
		error.setVisible(false);
		
		

		loadCarDealership();
	}

	public void loadCarDealership() {
		carDealerships = cont.getCarDealerships();
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
	    }
	}

	public boolean checkModelList(CarDealership cardealer, String name) {
		boolean encontrado = false;
		// calls the method of the LoginControlador to get all the clients to the
		// checkbox
		modelList = cont.getModels(cardealer);
		if (!modelList.isEmpty()) {
			for (Model d : modelList.values()) {
				if (d.getName_model().equalsIgnoreCase(name)) {
					encontrado = true;
				}
			}
		}
		return encontrado;
	}
}
