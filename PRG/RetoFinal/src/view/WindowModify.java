package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.LoginController;

import java.awt.Color;
import java.awt.Component;

/**
 * This window allows modifying models in the system.
 * A worker can update details of a selected model.
 *
 * @author Igor
 * @version 1.0
 */
public class WindowModify extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxList;
	private JLabel lblTitulo;
	private JLabel lblName;
	private JTextField textFieldName;
	private JTextField textFieldMark;
	private JTextField textFieldStock;
	private JTextField textFieldPrice;
	private JButton btnLogo;
	private JButton btnModify;
	private JButton btnUpdate;
	private LoginController cont;
	private Worker worker;
	private Map<String, Model> modelsList;
	private boolean activar = false;
	private ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/Logo.png")); 
	private Image img = icon.getImage().getScaledInstance(100, 101, Image.SCALE_SMOOTH); 


	/**
     * Constructor for the WindowModify class.
     *
     *@param parent  the father/main window
     * @param cont   The controller handling model modifications.
     * @param worker The worker managing models.
     */
	public WindowModify(/*JFrame parent,*/LoginController cont,Worker worker) 
	{
		setUndecorated(true); // Elimina los bordes y la barra de título
		setBackground(new Color(44, 44, 44)); // Color de fondo exterior
		getRootPane().setBorder(new LineBorder(new Color(30, 30, 30), 10));
		this.cont = cont;
		this.worker=worker;
		setResizable(false);
		setTitle("VentanaModificar");
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(44, 44, 44));

		contentPanel.setBackground(new Color(55, 55, 55));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTitulo = new JLabel("MODIFY MODELS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(277, 10, 201, 58);
		contentPanel.add(lblTitulo);

		comboBoxList = new JComboBox<String>();
		comboBoxList.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		comboBoxList.setBackground(new Color(55, 55, 55));
		comboBoxList.setForeground(new Color(255, 255, 255));
		comboBoxList.setBounds(28, 262, 235, 41);
		comboBoxList.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    setBackground(new Color(211, 47, 47)); // Rojo oscuro cuando se selecciona
                    setForeground(Color.WHITE);
                } else {
                    setBackground(new Color(55, 55, 55)); // Gris oscuro en normal
                    setForeground(Color.WHITE);
                }
                return this;
            }
        });
		contentPanel.add(comboBoxList);


		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(150, 0, 0)));
		panelDatos.setBackground(new Color(70, 70, 70));
		panelDatos.setBounds(432, 88, 323, 401);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);

		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(10, 87, 77, 21);
		panelDatos.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldName.setEnabled(activar);
		textFieldName.setBounds(126, 88, 170, 20);
		textFieldName.setBackground(new Color(80, 80, 80));
		textFieldName.setForeground(Color.WHITE);
		textFieldName.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblMark = new JLabel("Mark");
		lblMark.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMark.setForeground(Color.WHITE);
		lblMark.setBounds(10, 142, 77, 21);
		panelDatos.add(lblMark);

		textFieldMark = new JTextField();
		textFieldMark.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldMark.setEnabled(activar);
		textFieldMark.setColumns(10);
		textFieldMark.setBounds(126, 143, 170, 20);
		textFieldMark.setBackground(new Color(80, 80, 80));
        textFieldMark.setForeground(Color.WHITE);
        textFieldMark.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldMark);

		textFieldStock = new JTextField();
		textFieldStock.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldStock.setEnabled(activar);
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(126, 193, 170, 20);
		textFieldStock.setBackground(new Color(80, 80, 80));
        textFieldStock.setForeground(Color.WHITE);
        textFieldStock.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldStock);

		JLabel lblStock = new JLabel("Stock: ");
		lblStock.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblStock.setForeground(Color.WHITE);
		lblStock.setBounds(10, 196, 77, 21);
		panelDatos.add(lblStock);

		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(10, 246, 77, 21);
		panelDatos.add(lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textFieldPrice.setEnabled(activar);
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(126, 247, 170, 20);
		textFieldPrice.setBackground(new Color(80, 80, 80));
        textFieldPrice.setForeground(Color.WHITE);
        textFieldPrice.setBorder(BorderFactory.createLineBorder(new Color(211, 47, 47)));
		panelDatos.add(textFieldPrice);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		btnModify.setBackground(new Color(211, 47, 47));
        btnModify.setForeground(Color.WHITE);
        btnModify.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnModify.setBounds(10, 10, 150, 33);
		btnModify.setFocusPainted(false);
        btnModify.setBorderPainted(false);
		panelDatos.add(btnModify);
		btnModify.addActionListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(activar);
		btnUpdate.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		btnUpdate.setBackground(new Color(100, 100, 100));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnUpdate.setBounds(163, 10, 150, 33);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorderPainted(false);
		panelDatos.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnLogo = new JButton("",new ImageIcon(img));
		btnLogo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnLogo.setBackground(new Color(55, 55, 55));
        btnLogo.setForeground(Color.WHITE);
        btnLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnLogo.setBounds(28, 26, 103, 105);
		btnLogo.setFocusPainted(false);
		btnLogo.setBorderPainted(false);
		contentPanel.add(btnLogo);
		btnLogo.addActionListener(this);

		loadModels(worker);
		setupListeners();
	}


	/**
     * Handles button actions for modifying and updating models.
     *
     * @param e The action event triggered by a button click.
     */
	@Override
	//it provides all the functions of the buttons
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int stock;
		double price;
		Model modi;
		
		//Enables the forms
		if(e.getSource()==btnModify)
		{
			activar = true;
			toggleFields(activar);
		}
		//Modify the Models
		if(e.getSource()==btnUpdate) 
		{
			if (!textFieldMark.getText().isEmpty()&&!textFieldPrice.getText().isEmpty()&&!textFieldStock.getText().isEmpty()) 
			{
				if (checkInt(textFieldStock.getText().trim()) && checkDouble(textFieldPrice.getText().trim())) {
					stock = Integer.parseInt(textFieldStock.getText().trim());
					price = Double.parseDouble(textFieldPrice.getText().trim());
					modi = new Model(textFieldName.getText().trim(), textFieldMark.getText().trim(), stock, price,
							worker.getId_car_dealer());
					if (cont.modifyModel(modi)) {
						JOptionPane.showMessageDialog(null, "Modelo actualizado correctamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);

						comboBoxList.removeAllItems(); // Limpiar ComboBox
						loadModels(worker); // Recargar modelos con el concesionario adecuado

						// Actualizar campos con los nuevos valores
						updateFields(modi);

					} else {
						JOptionPane.showMessageDialog(null, "Error: It couldnt be updated.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (!checkInt(textFieldStock.getText().trim())) {
					JOptionPane.showMessageDialog(null, "Error: Put an Integer in the form.", "Error format",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Put a Double in the form.", "Error format",
							JOptionPane.ERROR_MESSAGE);
				} 
			}else
			{
				JOptionPane.showMessageDialog(null, "Error: Fill al the forms.", "Error format",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		//button to go back
		if(e.getSource()==btnLogo) 
		{
			this.dispose();
		}

	}
	/**
	 * Loads all models into the comboBox.
	 *
	 * @param worker The worker whose models will be loaded.
	 */
	public void loadModels(Worker worker) {
	    modelsList = cont.getModels(worker);
	    if (!modelsList.isEmpty()) {
	        for (Model m : modelsList.values()) {
	            comboBoxList.addItem(m.getName_model());
	        }
	    }
	    comboBoxList.setSelectedIndex(-1);
	}

	/**
	 * Updates the text fields with the details of the selected model.
	 *
	 * @param model The model whose details will be displayed.
	 */
	private void updateFields(Model model) {
	    if (model != null) {
	        textFieldName.setText(model.getName_model());
	        textFieldMark.setText(model.getMark());
	        textFieldStock.setText(String.valueOf(model.getStock()));
	        textFieldPrice.setText(String.valueOf(model.getPrice()));
	    } else {
	        textFieldName.setText("");
	        textFieldMark.setText("");
	        textFieldStock.setText("");
	        textFieldPrice.setText("");
	        
	        comboBoxList.setSelectedIndex(-1);
	        toggleFields(false);
	    }
	}

	/**
	 * Sets up the listener for the comboBox to detect changes in selection.
	 */
	public void setupListeners() {
	    comboBoxList.addActionListener(new ActionListener() {
	        /**
	         * Detects a change in selection and updates the fields accordingly.
	         *
	         * @param e The action event triggered when an item is selected.
	         */
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String selectedModel = (String) comboBoxList.getSelectedItem();
	            if (selectedModel != null && modelsList.containsKey(selectedModel)) {
	                Model model = modelsList.get(selectedModel);
	                updateFields(model);
	            } else {
	                updateFields(null);
	            }
	        }
	    });
	}

	/**
	 * Enables or disables the text fields and buttons.
	 *
	 * @param enable If true, the fields and buttons are enabled; otherwise, they are disabled.
	 */
	private void toggleFields(boolean enable) {
	    textFieldMark.setEnabled(enable);
	    textFieldStock.setEnabled(enable);
	    textFieldPrice.setEnabled(enable);
	    btnUpdate.setEnabled(enable);
	}

	/**
	 * Checks if a given string is a valid integer.
	 *
	 * @param str The string to check.
	 * @return True if the string is a valid integer, false otherwise.
	 */
	public static boolean checkInt(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;  // The string is a valid integer.
	    } catch (NumberFormatException e) {
	        return false; // The string is not a valid integer.
	    }
	}

	/**
	 * Checks if a given string is a valid double.
	 *
	 * @param str The string to check.
	 * @return True if the string is a valid double, false otherwise.
	 */
	public static boolean checkDouble(String str) {
	    try {
	        Double.parseDouble(str);
	        return true;  // The string is a valid double.
	    } catch (NumberFormatException e) {
	        return false; // The string is not a valid double.
	    }
	}

}
