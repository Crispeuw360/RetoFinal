package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.LoginControlador;
import modelo.CarDealership;
import modelo.Client_;
import modelo.Model;
import modelo.StockException;
import modelo.Worker;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;

public class VentanaVender extends JDialog implements ActionListener, ChangeListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxModels;
	private JComboBox<String> comboBoxClients;
	private JButton btnSell;
	private JLabel lblMessage;
	private JButton btnAddUser;
	private Map<String, Client_> clientsList;
	private Map<String, Model> modelsList;
	private LoginControlador cont;
	private JLabel lblModels;
	private JLabel lblWorkers;
	private Worker worker;
	private JButton btnGoBack;
	private JLabel lblQuantity;
	private JSlider sliderQuantity;
	private JLabel lblInformationModels;
	private JLabel lblInformationClients;

	public VentanaVender(/*JFrame parent,*/ Worker worker, LoginControlador cont) {
		//super(parent, true);
		this.cont = cont;
		this.worker = worker;
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBoxModels = new JComboBox<String>();
		comboBoxModels.setBounds(117, 94, 135, 35);
		contentPanel.add(comboBoxModels);

		comboBoxClients = new JComboBox<String>();
		comboBoxClients.setBounds(524, 94, 135, 35);
		contentPanel.add(comboBoxClients);

		btnSell = new JButton("SELL\r\n");
		btnSell.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnSell.setBounds(566, 430, 127, 43);
		contentPanel.add(btnSell);

		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblMessage.setBounds(117, 396, 529, 24);
		contentPanel.add(lblMessage);

		btnAddUser = new JButton("ADD USER");
		btnAddUser.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnAddUser.setBounds(139, 431, 127, 43);
		contentPanel.add(btnAddUser);

		lblModels = new JLabel("MODELS");
		lblModels.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblModels.setBounds(140, 49, 78, 35);
		contentPanel.add(lblModels);

		lblWorkers = new JLabel("WORKERS\r\n");
		lblWorkers.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblWorkers.setBounds(547, 49, 78, 35);
		contentPanel.add(lblWorkers);

		btnGoBack = new JButton("GO BACK");
		btnGoBack.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnGoBack.setBounds(24, 515, 84, 24);
		contentPanel.add(btnGoBack);

		lblQuantity = new JLabel("Units to sell: 0");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantity.setBounds(342, 349, 153, 24);
		contentPanel.add(lblQuantity);

		sliderQuantity = new JSlider();
		sliderQuantity.setBounds(117, 351, 200, 22);
		contentPanel.add(sliderQuantity);
		sliderQuantity.setValue(5);
		sliderQuantity.setValue(1);
		sliderQuantity.setMaximum(10);

		lblInformationModels = new JLabel("");
		lblInformationModels.setBounds(117, 265, 153, 75);
		contentPanel.add(lblInformationModels);

		lblInformationClients = new JLabel("");
		lblInformationClients.setBounds(524, 265, 153, 56);
		contentPanel.add(lblInformationClients);

		loadCliens();
		loadModels(worker);


		btnSell.addActionListener(this);
		btnAddUser.addActionListener(this);
		btnGoBack.addActionListener(this);
		sliderQuantity.addChangeListener(this);
		comboBoxModels.addItemListener(this);
		comboBoxClients.addItemListener(this);

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

	public void loadModels(Worker Worker) {
		modelsList = cont.getModels(worker);
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

		if (e.getSource() == btnAddUser) {
			//AQUI VA LA VENTANA NUEVO USUARIO
		}

		if(e.getSource()==btnSell) {
			try {
				checkingStock();
			} catch (StockException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}



		if (e.getSource() == btnGoBack) {
			this.dispose();
		}

	}

	public void checkingStock() throws StockException {
		Client_ client;
		Model model;

		client = clientsList.get(comboBoxClients.getSelectedItem());
		model = modelsList.get(comboBoxModels.getSelectedItem());
		
		if(cont.checkStock(model)>0) {
			if(cont.checkStock(model)<sliderQuantity.getValue()){
				JOptionPane.showMessageDialog(null, "Insufficient stock", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			}else {
				cont.callProcedure(client, model, worker, LocalDate.now(), sliderQuantity.getValue());
				lblInformationModels.setText("<html>Mark: " + model.getMark() + "<br>Price: " + model.getPrice() + "€" + "<br>Stock: " + (model.getStock()-sliderQuantity.getValue()) + "</html>");
				lblMessage.setText("PURCHASE CORRECTLY DONE!");
			}
		}else {
			throw new StockException();

		}
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int value;
		lblMessage.setText("");
		value = sliderQuantity.getValue();
		lblQuantity.setText("Units to sell: " + value);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Model model;
		Client_ client;

		model = modelsList.get(comboBoxModels.getSelectedItem());

		if(comboBoxModels.getSelectedItem() != null) {
			if(model != null) {
				lblMessage.setText("");
				lblInformationModels.setText("<html>Mark: " + model.getMark() + "<br>Price: " + model.getPrice() + "€" + "<br>Stock: " + model.getStock() + "</html>");
			}
		}

		if(comboBoxClients.getSelectedItem() != null) {
			client = clientsList.get(comboBoxClients.getSelectedItem());

			if(client != null) {
				lblMessage.setText("");
				lblInformationClients.setText("<html>DNI: " + client.getDni() + "<br>Email: " + client.getEmail());
			}
		}
	}
}
