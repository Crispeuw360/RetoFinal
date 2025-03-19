package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Model;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class VentanaVender extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUnits;
	private JComboBox<Model> comboBoxModels;
	private JComboBox comboBoxUsers;
	private JLabel lblUnits;
	private JButton btnSell;
	private JLabel lblMessage;
	private JButton btnAddUser;

	
	public VentanaVender() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		comboBoxModels = new JComboBox();
		comboBoxModels.setBounds(116, 53, 135, 35);
		contentPanel.add(comboBoxModels);
		
		comboBoxUsers = new JComboBox();
		comboBoxUsers.setBounds(523, 53, 135, 35);
		contentPanel.add(comboBoxUsers);
		
		lblUnits = new JLabel("Units to sell:");
		lblUnits.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblUnits.setBounds(146, 314, 102, 24);
		contentPanel.add(lblUnits);
		
		textFieldUnits = new JTextField();
		textFieldUnits.setBounds(258, 317, 114, 24);
		contentPanel.add(textFieldUnits);
		textFieldUnits.setColumns(10);
		
		btnSell = new JButton("SELL\r\n");
		btnSell.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnSell.setBounds(573, 420, 85, 21);
		contentPanel.add(btnSell);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMessage.setBounds(95, 372, 529, 24);
		contentPanel.add(lblMessage);
		
		btnAddUser = new JButton("ADD USER");
		btnAddUser.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnAddUser.setBounds(146, 421, 105, 21);
		contentPanel.add(btnAddUser);
		
	}
}
