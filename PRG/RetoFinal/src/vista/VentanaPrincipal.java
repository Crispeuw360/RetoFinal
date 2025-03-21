package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.LoginControlador;
import modelo.CarDealership;
import modelo.Model;
import modelo.Worker;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class VentanaPrincipal extends JFrame {
	private LoginControlador cont;
	private JButton btnSell;
	private JButton btnModifyCars;
	private JButton btnDelete;
	private JLabel lblDealership;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmDealership;
	private JMenuItem mntmAdmin;
	private Worker worker;
	private CarDealership cardealer;
	private Map<String, Model> models;
	private DefaultListModel<String> listModel;
	private JList<String> listModels;
	private JMenuItem mntmLocation;
	private JLabel lblCarInfo;

	public VentanaPrincipal(LoginControlador cont, Worker worker) {
		this.cont = cont;
		this.worker = worker;
		setBackground(new Color(181, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCarInfo = new JLabel("New label");
		lblCarInfo.setBounds(40, 128, 319, 192);
		contentPane.add(lblCarInfo);

		lblDealership = new JLabel("");
		lblDealership.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealership.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblDealership.setBounds(40, 10, 705, 55);
		contentPane.add(lblDealership);

		listModel = new DefaultListModel<>();
		listModels = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(listModels);
		scrollPane.setBounds(470, 75, 275, 350);
		contentPane.add(scrollPane);

		btnModifyCars = new JButton("MODIFY");
		btnModifyCars.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnModifyCars.setBounds(318, 475, 150, 55);
		contentPane.add(btnModifyCars);

		btnSell = new JButton("SELL");
		btnSell.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnSell.setBounds(84, 475, 150, 55);
		contentPane.add(btnSell);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(552, 475, 150, 55);
		contentPane.add(btnDelete);

		menuBar = new JMenuBar();
		menuBar.setBounds(676, 10, 100, 40);
		contentPane.add(menuBar);

		mnNewMenu = new JMenu("Username");
		mnNewMenu.setPreferredSize(new Dimension(100, 30)); // Makes the JMenu bigger
		menuBar.add(mnNewMenu);

		mntmDealership = new JMenuItem("Concesionario:");
		mnNewMenu.add(mntmDealership);

		mntmLocation = new JMenuItem();
		mnNewMenu.add(mntmLocation);

		mntmAdmin = new JMenuItem("Admin");
		mnNewMenu.add(mntmAdmin);

		loadDealer();
		loadModel();

		listModels.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					String modelName = listModels.getSelectedValue();

					if (modelName != null) {

						Model model = models.get(modelName);

						String infoCar = "Name: " + model.getName_model() + "<br>" + "Mark: " + model.getMark();
						lblCarInfo.setText("<html>" + infoCar + "</htlm>");
					}
				}
			}
		});

	}

	public void loadModel() {
		// Instantiate the Map models, taking all the models from the given dealership
		models = cont.getModels(cont.getWorkingPlace(worker));

		if (!models.isEmpty()) {

			for (Model m : models.values()) {
				listModel.addElement(m.getName_model());
			}

		}
	}

	public void loadDealer() {

		cardealer = cont.getWorkingPlace(worker);

		lblDealership.setText("Bienvenido a " + cardealer.getName());

		mntmLocation.setText("📍 " + cardealer.getLocation());

	}
}
