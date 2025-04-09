package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalButtonUI;

import Exception.StockException;
import controller.*;
import model.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Component;

public class WindowPurchase extends JDialog implements ActionListener, ChangeListener, ItemListener {

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
	private Map<String, Client> clientsList;
	private Map<String, Model> modelsList;
	private LoginController cont;
	private JLabel lblModels;
	private JLabel lblWorkers;
	private Worker worker;
	private JButton btnGoBack;
	private JLabel lblQuantity;
	private JSlider sliderQuantity;
	private JLabel lblInformationModels;
	private JLabel lblInformationClients;
	private JLabel lblImage;

	public WindowPurchase(JFrame parent, Worker worker, LoginController cont) {
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(0, 0, 0));
		setTitle("PURCHASE");
		// super(parent, true);
		this.cont = cont;
		this.worker = worker;
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(55, 55, 55));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBoxModels = new JComboBox<String>();
		comboBoxModels.setForeground(new Color(255, 255, 255));
		comboBoxModels.setBackground(new Color(55, 55, 55));
		comboBoxModels.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		comboBoxModels.setBounds(117, 94, 135, 35);
		comboBoxModels.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
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
		contentPanel.add(comboBoxModels);

		comboBoxClients = new JComboBox<String>();
		comboBoxClients.setForeground(new Color(255, 255, 255));
		comboBoxClients.setBackground(new Color(55, 55, 55));
		comboBoxClients.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		comboBoxClients.setBounds(524, 94, 135, 35);
		comboBoxClients.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
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
		contentPanel.add(comboBoxClients);

		btnSell = new JButton("SELL\r\n");
		btnSell.setBackground(new Color(211, 47, 47));
		btnSell.setForeground(new Color(255, 255, 255));
		btnSell.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnSell.setBounds(566, 430, 127, 43);
		contentPanel.add(btnSell);
		btnSell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// add the image to the sell button
		ImageIcon imageDollar = new ImageIcon(WindowPurchase.class.getResource("/imgs/dollar.png"));
		Image img1 = imageDollar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon imageDollarScaled = new ImageIcon(img1);
		btnSell.setIcon(imageDollarScaled);

		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(255, 255, 255));
		lblMessage.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblMessage.setBounds(117, 396, 529, 24);
		contentPanel.add(lblMessage);

		btnAddUser = new JButton("ADD USER");
		btnAddUser.setForeground(new Color(255, 255, 255));
		btnAddUser.setBackground(new Color(211, 47, 47));
		btnAddUser.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnAddUser.setBounds(139, 431, 127, 43);
		contentPanel.add(btnAddUser);
		btnAddUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		ImageIcon imageUser = new ImageIcon(WindowPurchase.class.getResource("/imgs/usuario.png"));
		Image img2 = imageUser.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon imageUserScaled2 = new ImageIcon(img2);
		btnAddUser.setIcon(imageUserScaled2);

		lblModels = new JLabel("SELECT THE MODEL");
		lblModels.setForeground(new Color(255, 255, 255));
		lblModels.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblModels.setBounds(106, 49, 177, 35);
		contentPanel.add(lblModels);

		lblWorkers = new JLabel("SELECT THE CLIENT");
		lblWorkers.setForeground(new Color(255, 255, 255));
		lblWorkers.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblWorkers.setBounds(516, 49, 177, 35);
		contentPanel.add(lblWorkers);

		btnGoBack = new JButton("GO BACK");
		btnGoBack.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnGoBack.setBounds(24, 515, 84, 24);
		contentPanel.add(btnGoBack);
		btnSell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblQuantity = new JLabel("Units to sell: 0");
		lblQuantity.setForeground(new Color(255, 255, 255));
		lblQuantity.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblQuantity.setBounds(342, 349, 153, 24);
		contentPanel.add(lblQuantity);

		sliderQuantity = new JSlider();
		sliderQuantity.setBounds(117, 351, 200, 22);
		contentPanel.add(sliderQuantity);
		sliderQuantity.setValue(5);
		sliderQuantity.setValue(1);
		sliderQuantity.setMaximum(10);
		sliderQuantity.setBackground(Color.DARK_GRAY);

		lblInformationModels = new JLabel("");
		lblInformationModels.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblInformationModels.setForeground(new Color(255, 255, 255));
		lblInformationModels.setBounds(117, 265, 153, 75);
		contentPanel.add(lblInformationModels);

		lblInformationClients = new JLabel("");
		lblInformationClients.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblInformationClients.setForeground(new Color(255, 255, 255));
		lblInformationClients.setBounds(524, 265, 187, 56);
		contentPanel.add(lblInformationClients);

		lblImage = new JLabel("");
		lblImage.setBounds(352, 63, 69, 66);
		contentPanel.add(lblImage);

		ImageIcon imageLogo = new ImageIcon(WindowPurchase.class.getResource("/imgs/logo.png"));
		Image img3 = imageLogo.getImage().getScaledInstance(69, 66, Image.SCALE_SMOOTH);
		ImageIcon imageUserScaled3 = new ImageIcon(img3);
		lblImage.setIcon(imageUserScaled3);

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
			for (Client c : clientsList.values()) {
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
			this.dispose();
			WindowCreateUser win = new WindowCreateUser(this, worker, cont);
			win.setVisible(true);
		}

		if (e.getSource() == btnSell) {
			try {
				if (comboBoxClients.getSelectedItem() == null || comboBoxModels.getSelectedItem() == null) {
					lblMessage.setText("YOU HAVE TO SELECT MODEL AND CLIENT BEFORE");
				} else {
					checkingStock();

				}
			} catch (StockException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
		}

		if (e.getSource() == btnGoBack) {
			this.dispose();
			WindowMain ven = new WindowMain(cont, worker);
			ven.setVisible(true);

		}

	}

	public void checkingStock() throws StockException {
		Client client;
		Model model;

		client = clientsList.get(comboBoxClients.getSelectedItem());
		model = modelsList.get(comboBoxModels.getSelectedItem());

		if (cont.checkStock(model) > 0) {
			if (cont.checkStock(model) < sliderQuantity.getValue()) {
				JOptionPane.showMessageDialog(null, "Insufficient stock", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			} else {

				comboBoxClients.setSelectedIndex(-1);
				lblInformationClients.setText("");

				// model = cont.getModel(model.getName_model());

				cont.callProcedure(client, model, worker, LocalDate.now(), sliderQuantity.getValue());
				lblInformationModels.setText("<html>Mark: " + model.getMark() + "<br>Price: " + model.getPrice() + "€"
						+ "<br>Stock: " + (model.getStock() - sliderQuantity.getValue()) + "</html>");

				lblMessage.setText("PURCHASE CORRECTLY DONE!");
			}
		} else {
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
		Client client;

		if (comboBoxModels.getSelectedItem() != null) {
			model = modelsList.get(comboBoxModels.getSelectedItem());
			if (model != null) {
				lblMessage.setText("");
				lblInformationModels.setText("<html>Mark: " + model.getMark() + "<br>Price: " + model.getPrice() + "€"
						+ "<br>Stock: " + model.getStock() + "</html>");
			}
		}

		if (comboBoxClients.getSelectedItem() != null) {
			client = clientsList.get(comboBoxClients.getSelectedItem());
			if (client != null) {
				lblMessage.setText("");
				lblInformationClients.setText("<html>DNI: " + client.getDni() + "<br>Email: " + client.getEmail());
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		this.dispose(); // Cierra la ventana al hacer clic en la imagen
	}
}
