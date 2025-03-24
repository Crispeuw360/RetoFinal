package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import modelo.CarDealership;
import modelo.Client_;
import modelo.Model;
import modelo.StockException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.JTextField;

public class VentanaVender extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUnits;
	private JComboBox<String> comboBoxModels;
	private JComboBox<String> comboBoxClients;
	private JLabel lblUnits;
	private JButton btnSell;
	private JLabel lblMessage;
	private JButton btnAddUser;
	private Map<String, Client_> clientsList;
	private Map<String, Model> modelsList;
	private LoginControlador cont;
	private JLabel lblModels;
	private JLabel lblWorkers;
	private CarDealership cardealer;

	public VentanaVender(/*JFrame parent,*/ CarDealership cardealer, LoginControlador cont) {
		//super(parent, true);
		this.cont = cont;
		this.cardealer = cardealer;
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBoxModels = new JComboBox<String>();
		comboBoxModels.setBounds(112, 89, 135, 35);
		contentPanel.add(comboBoxModels);

		comboBoxClients = new JComboBox<String>();
		comboBoxClients.setBounds(519, 89, 135, 35);
		contentPanel.add(comboBoxClients);

		lblUnits = new JLabel("Units to sell:");
		lblUnits.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblUnits.setBounds(124, 319, 118, 35);
		contentPanel.add(lblUnits);

		textFieldUnits = new JTextField();
		textFieldUnits.setBounds(258, 317, 127, 35);
		contentPanel.add(textFieldUnits);
		textFieldUnits.setColumns(10);

		btnSell = new JButton("SELL\r\n");
		btnSell.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		btnSell.setBounds(573, 420, 127, 43);
		contentPanel.add(btnSell);

		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMessage.setBounds(95, 372, 529, 24);
		contentPanel.add(lblMessage);

		btnAddUser = new JButton("ADD USER");
		btnAddUser.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		btnAddUser.setBounds(146, 421, 127, 43);
		contentPanel.add(btnAddUser);

		lblModels = new JLabel("MODELS");
		lblModels.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblModels.setBounds(135, 44, 78, 35);
		contentPanel.add(lblModels);

		lblWorkers = new JLabel("WORKERS\r\n");
		lblWorkers.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblWorkers.setBounds(542, 44, 78, 35);
		contentPanel.add(lblWorkers);
		
		loadCliens();
		loadModels(cardealer);
		
		
		btnSell.addActionListener(this);
		btnAddUser.addActionListener(this);

	}

	public void loadCliens() {
		clientsList = cont.getClients();
		if (!clientsList.isEmpty()) {
			for (Client_ c : clientsList.values()) {
				comboBoxClients.addItem(c.getUser_());
			}
		}
		comboBoxClients.setSelectedIndex(-1);
	}

	public void loadModels(CarDealership cardealer) {
		modelsList = cont.getModels(cardealer);
		if (!modelsList.isEmpty()) {
			for (Model m : modelsList.values()) {
				comboBoxModels.addItem(m.getName_model());
			}
		}
		comboBoxModels.setSelectedIndex(-1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Client_ client;
		Model model;
		if (e.getSource() == btnAddUser) {
			//AQUI VA LA VENTANA NUEVO USUARIO
		}
		
		//the try catch surrounds the button not to get into an infinite loop
		try {
		if(e.getSource()==btnSell) {
			
				checkingStock();
		}
			
		} catch (StockException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void checkingStock() throws StockException {
		Client_ client;
		Model model;
		
		client = clientsList.get(comboBoxClients.getSelectedItem());
		model = modelsList.get(comboBoxModels.getSelectedItem());
		
		if(cont.checkStock(model)) {
			cont.callProcedure(client, model, cardealer, LocalDate.now(), Integer.parseInt(textFieldUnits.getText()));
			lblMessage.setText("PURCHASE CORRECTLY DONE!");
			}else {
				throw new StockException();
				//AÑADIR VENTANA POP UPP
				//SE TIENE QUE QUEDAR EN BUCLE????? NO SE PUEDE EN BUCLE
				
			}
	}
	
}
