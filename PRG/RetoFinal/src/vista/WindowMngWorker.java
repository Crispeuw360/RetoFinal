package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class WindowMngWorker extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_user;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WindowMngWorker dialog = new WindowMngWorker();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WindowMngWorker() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

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
		passwordField.setBounds(126, 143, 170, 20);
		panelDatos.add(passwordField);

		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(126, 193, 170, 20);
		panelDatos.add(textField_2);

		JLabel lblDealer = new JLabel("Dealership");
		lblDealer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDealer.setBounds(10, 196, 77, 21);
		panelDatos.add(lblDealer);

		JLabel lblAdmin = new JLabel("Admin:");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdmin.setBounds(10, 246, 77, 21);
		panelDatos.add(lblAdmin);


		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModify.setBounds(2, 10, 150, 33);
		panelDatos.add(btnModify);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(163, 10, 150, 33);
		panelDatos.add(btnUpdate);

		JRadioButton rdbtnYes = new JRadioButton("YES");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(126, 247, 80, 20);
		panelDatos.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("NO");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(216, 247, 80, 20);
		panelDatos.add(rdbtnNo);
		
		
		
	}
}
