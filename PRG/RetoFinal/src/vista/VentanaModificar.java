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

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VentanaModificar extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxLista;
	private JLabel lblTitulo;
	private JLabel lblName;
	private JTextField textFieldName;
	private JTextField textFieldMark;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private JButton btnBack;
	private JButton btnModify;
	private JButton btnUpdate;
	private LoginControlador cont;
	private Map<String, Model> modelsList;
	private boolean activar = false;


	public VentanaModificar(JFrame parent,CarDealership cardealer,LoginControlador cont) 
	{
		super(parent, true);
		this.cont = cont;
		setTitle("VentanaModificar");
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTitulo = new JLabel("MODIFY");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(277, 10, 201, 58);
		contentPanel.add(lblTitulo);

		comboBoxLista = new JComboBox();
		comboBoxLista.setBounds(35, 108, 201, 41);
		contentPanel.add(comboBoxLista);


		JPanel panelDatos = new JPanel();
		panelDatos.setBounds(453, 88, 323, 401);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);

		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 87, 77, 21);
		panelDatos.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setEnabled(activar);
		textFieldName.setBounds(126, 88, 170, 20);
		panelDatos.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblMark = new JLabel("Mark");
		lblMark.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMark.setBounds(10, 142, 77, 21);
		panelDatos.add(lblMark);

		textFieldMark = new JTextField();
		textFieldMark.setEnabled(activar);
		textFieldMark.setColumns(10);
		textFieldMark.setBounds(126, 143, 170, 20);
		panelDatos.add(textFieldMark);

		textFieldStock = new JTextField();
		textFieldStock.setEnabled(activar);
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(126, 193, 170, 20);
		panelDatos.add(textFieldStock);

		JLabel lblStock = new JLabel("Stock: ");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStock.setBounds(10, 196, 77, 21);
		panelDatos.add(lblStock);

		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(10, 246, 77, 21);
		panelDatos.add(lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setEnabled(activar);
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(126, 247, 170, 20);
		panelDatos.add(textFieldPrice);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModify.setBounds(2, 10, 150, 33);
		panelDatos.add(btnModify);
		btnModify.addActionListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(activar);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(163, 10, 150, 33);
		panelDatos.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnBack = new JButton("GO BACK");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(10, 512, 215, 41);
		contentPanel.add(btnBack);
		btnBack.addActionListener(this);

		loadModels(cardealer);
		setupListeners();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource()==btnModify)
		{
			activar = true;
			toggleFields(activar);

		}
		else if(e.getSource()==btnBack) 
		{
			activar = false;
			toggleFields(activar);
		}

	}
	//it loads all models to the comboBox
	public void loadModels(CarDealership cardealer)
	{
		modelsList = cont.getModels(cardealer);
		if (!modelsList.isEmpty()) {
			for (Model m : modelsList.values()) {
				comboBoxLista.addItem(m.getName_model());
			}
		}
		comboBoxLista.setSelectedIndex(-1);
	}
	//It updates the data on the textFields
	private void updateFields(Model model) 
	{
		textFieldName.setText(model.getName_model());
		textFieldMark.setText(model.getMark());
		textFieldStock.setText(String.valueOf(model.getStock()));
		textFieldPrice.setText(String.valueOf(model.getPrice()));
	}
	//Setups the listener in the comboBox so it can detect changes
	public void setupListeners() 
	{
		comboBoxLista.addActionListener(new ActionListener() 
		{
			//when a change is detected,gets the selected item and calls the funcion update Field
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedModel;
				Model model;
				selectedModel = (String) comboBoxLista.getSelectedItem();
				
				if (selectedModel != null && modelsList.containsKey(selectedModel)) 
				{
					model = modelsList.get(selectedModel);
					updateFields(model);
				}
			}
		});
	}
	// updates the buttons and textFields visually to be enabled
	private void toggleFields(boolean enable) 
	{
		textFieldName.setEnabled(enable);
		textFieldMark.setEnabled(enable);
		textFieldStock.setEnabled(enable);
		textFieldPrice.setEnabled(enable);
		btnUpdate.setEnabled(enable);
	}
}
