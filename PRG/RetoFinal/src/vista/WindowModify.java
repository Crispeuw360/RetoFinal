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
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class WindowModify extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxList;
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
	private Worker worker;
	private Map<String, Model> modelsList;
	private boolean activar = false;


	public WindowModify(/*JFrame parent,*/LoginControlador cont,Worker worker) 
	{
		/*super(parent, true);*/
		this.cont = cont;
		this.worker=worker;
		setResizable(false);
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

		comboBoxList = new JComboBox<String>();
		comboBoxList.setBounds(35, 108, 201, 41);
		contentPanel.add(comboBoxList);


		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
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

		loadModels(worker);
		setupListeners();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int stock;
		double price;
		Model modi;

		if(e.getSource()==btnModify)
		{
			activar = true;
			toggleFields(activar);


		}else if(e.getSource()==btnUpdate) 
		{
			if(checkInt(textFieldStock.getText().trim())&&checkDouble(textFieldPrice.getText().trim()))
			{
				stock = Integer.parseInt(textFieldStock.getText().trim());
				price = Double.parseDouble(textFieldPrice.getText().trim());
				modi = new Model(textFieldName.getText().trim(),textFieldMark.getText().trim(),stock,price,worker.getId_car_dealer());
				if (cont.modifyModel(modi)) 
				{
					JOptionPane.showMessageDialog(null, "Modelo actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

					comboBoxList.removeAllItems();  // Limpiar ComboBox
					loadModels(worker); // Recargar modelos con el concesionario adecuado


					// Actualizar campos con los nuevos valores
					updateFields(modi);


				} else 
				{
					JOptionPane.showMessageDialog(null, "Error: It couldnt be updated.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else if(!checkInt(textFieldStock.getText().trim()))
			{
				JOptionPane.showMessageDialog(null, "Error: Put an Integer in the form.", "Error format", JOptionPane.ERROR_MESSAGE);
			}else
			{
				JOptionPane.showMessageDialog(null, "Error: Put a Double in the form.", "Error format", JOptionPane.ERROR_MESSAGE);
			}

		}
		else if(e.getSource()==btnBack) 
		{
			this.dispose();
		}

	}
	//it loads all models to the comboBox
	public void loadModels(Worker worker)
	{
		modelsList = cont.getModels(worker);
		if (!modelsList.isEmpty()) {
			for (Model m : modelsList.values()) {
				comboBoxList.addItem(m.getName_model());
			}
		}
		comboBoxList.setSelectedIndex(-1);
	}
	//It updates the data on the textFields
	private void updateFields(Model model) 
	{
		if (model != null) {
			textFieldName.setText(model.getName_model());
			textFieldMark.setText(model.getMark());
			textFieldStock.setText(String.valueOf(model.getStock()));
			textFieldPrice.setText(String.valueOf(model.getPrice()));
		}else
		{
			textFieldName.setText("");
			textFieldMark.setText("");
			textFieldStock.setText("");
			textFieldPrice.setText("");
			
			comboBoxList.setSelectedIndex(-1);
			
			toggleFields(false);
		}
	}
	//Setups the listener in the comboBox so it can detect changes
	public void setupListeners() 
	{
		comboBoxList.addActionListener(new ActionListener() 
		{
			//when a change is detected,gets the selected item and calls the funcion update Field
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedModel;
				Model model;
				selectedModel = (String) comboBoxList.getSelectedItem();

				if (selectedModel != null && modelsList.containsKey(selectedModel)) 
				{
					model = modelsList.get(selectedModel);
					updateFields(model);
				}else
				{
					updateFields(null);
				}
			}
		});
	}
	// updates the buttons and textFields visually to be enabled
	private void toggleFields(boolean enable) 
	{
		textFieldMark.setEnabled(enable);
		textFieldStock.setEnabled(enable);
		textFieldPrice.setEnabled(enable);
		btnUpdate.setEnabled(enable);
	}
	public static boolean checkInt(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;  // Es un número entero
		} catch (NumberFormatException e) {
			return false; // No es un número entero
		}
	}
	public static boolean checkDouble(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;  // Es un número decimal válido
		} catch (NumberFormatException e) {
			return false; // No es un número válido
		}
	}
}
