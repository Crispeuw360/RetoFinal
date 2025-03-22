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
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;

public class VentanaPrincipal extends JFrame implements ActionListener {
	private LoginControlador cont;
	private JButton btnSell;
	private JButton btnModifyCars;
	private JButton btnDelete;
	private JLabel lblDealership;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmLogOut;
	private JMenuItem mntmAdmin;
	private Worker worker;
	private CarDealership cardealer;
	private Map<String, Model> models;
	private DefaultListModel<String> listModel;
	private JList<String> listModels;
	private JMenuItem mntmLocation;
	private JLabel lblCarInfo;
	private JLabel lblWarning;

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

		lblCarInfo = new JLabel("");
		lblCarInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblCarInfo.setBounds(50, 150, 320, 200);
		contentPane.add(lblCarInfo);

		lblDealership = new JLabel("");
		lblDealership.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealership.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblDealership.setBounds(40, 10, 705, 55);
		contentPane.add(lblDealership);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblWarning.setBounds(480, 425, 255, 40);
		contentPane.add(lblWarning);

		listModel = new DefaultListModel<>();
		listModels = new JList<>(listModel);
		listModels.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		JScrollPane scrollPane = new JScrollPane(listModels);
		scrollPane.setBounds(470, 75, 275, 350);
		contentPane.add(scrollPane);

		JLabel lblDisponibleModels = new JLabel("Avaliable Models");
		lblDisponibleModels.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponibleModels.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scrollPane.setColumnHeaderView(lblDisponibleModels);

		btnModifyCars = new JButton("MODIFY");
		btnModifyCars.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnModifyCars.setBounds(318, 475, 150, 55);
		contentPane.add(btnModifyCars);
		btnModifyCars.addActionListener(this);

		btnSell = new JButton("SELL");
		btnSell.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnSell.setBounds(84, 475, 150, 55);
		contentPane.add(btnSell);
		btnSell.addActionListener(this);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(552, 475, 150, 55);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);

		menuBar = new JMenuBar();
		menuBar.setBounds(666, 10, 110, 40);
		contentPane.add(menuBar);

		mnNewMenu = new JMenu("Username");
		mnNewMenu.setPreferredSize(new Dimension(110, 40)); // Makes the JMenu bigger
		menuBar.add(mnNewMenu);

		mntmLocation = new JMenuItem("<dynamic>");
		mnNewMenu.add(mntmLocation);
		mntmLocation.addActionListener(this);
		
		mntmAdmin = new JMenuItem("Admin");
		mnNewMenu.add(mntmAdmin);
		mntmAdmin.addActionListener(this);

		mntmLogOut = new JMenuItem("Log Out:");
		mnNewMenu.add(mntmLogOut);
		mntmLogOut.addActionListener(this);

		loadDealer();
		loadModel();
		loadWorker();

		listModels.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					String modelName = listModels.getSelectedValue();

					if (modelName != null) {

						Model model = models.get(modelName);

						String infoCar = "Name: " + model.getName_model() + "<br>" + "Mark: " + model.getMark() + "<br>"
								+ "Price: " + model.getPrice() + "€" + "<br>" + "Stock: " + model.getStock();
						lblCarInfo.setText("<html>" + infoCar + "</htlm>");
					}
				}
			}
		});

	}

	public void loadModel() {
		// Instantiate the Map models, taking all the models from the given dealership
		models = cont.getModels(cont.getWorkingPlace(worker));

		// Delete all models on the list from previous loads

		listModel.clear();

		if (!models.isEmpty()) {
			// Add models into List when models isnt empty
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

	public void loadWorker() {
		if (!worker.isAdmin()) {

			btnDelete.setEnabled(false);
			btnDelete.setToolTipText("This is an Admin function!");
			
			mntmAdmin.setEnabled(false);
			mntmAdmin.setToolTipText("This is an Admin function!");

			ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
			toolTipManager.setInitialDelay(0); // Show tooltip immediately
			
		

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnDelete) {
			String modelName = listModels.getSelectedValue();
			if (modelName != null) {
				Model model = models.get(modelName);
				if (cont.deleteModel(model)) {
					lblWarning.setText(model.getName_model() + " was erased from the DataBase");
					loadModel();
				}
			}
		}

		if (e.getSource() == mntmAdmin) {

			// INSTANCIAR VENTANA ADMIN

		}

		if (e.getSource() == mntmLogOut) {
			this.dispose();
		

		}

		if (e.getSource() == mntmLocation) {

			try {
				// Make the URL, Replace space with nothing(cant search space)
				String searchUrl = "https://www.google.com/search?q=" + cardealer.getLocation().replace(" ", "");

				// Open predetermined browser
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(searchUrl));
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al abrir el navegador.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}
}
