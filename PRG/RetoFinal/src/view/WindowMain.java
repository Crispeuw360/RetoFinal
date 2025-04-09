package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.LoginController;
import model.CarDealership;
import model.Model;
import model.Worker;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class WindowMain extends JFrame implements ActionListener {
	private LoginController cont;
	private Worker worker;
	private JButton btnSell;
	private JButton btnModifyCars;
	private JButton btnDelete;
	private JLabel lblDealership;
	private JMenuBar menuBar;
	private JMenu mnUserMenu;
	private JMenuItem mntmLogOut;
	private CarDealership cardealer;
	private Map<String, Model> models;
	private DefaultListModel<String> listModel;
	private JList<String> listModels;
	private JMenuItem mntmLocation;
	private JLabel lblCarInfo;
	private JLabel lblWarning;
	private JMenu mnAdmin;
	private JMenuItem mntmMngModel;
	private JMenuItem mntmMngWorker;
	private ImageIcon icon = new ImageIcon("src/img/icon.png");
	private Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

	public WindowMain(LoginController cont, Worker worker) {
		this.cont = cont;
		this.worker = worker;
		setBackground(new Color(44, 44, 44));
		getRootPane().setBorder(new LineBorder(new Color(30, 30, 30), 10));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(800, 600);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(55, 55, 55));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCarInfo = new JLabel("");
		lblCarInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblCarInfo.setForeground(Color.WHITE);
		lblCarInfo.setBounds(50, 188, 320, 200);
		contentPane.add(lblCarInfo);

		lblDealership = new JLabel("");
		lblDealership.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealership.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblDealership.setForeground(Color.WHITE);
		lblDealership.setBounds(40, 10, 705, 55);
		contentPane.add(lblDealership);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblWarning.setForeground(Color.WHITE);
		lblWarning.setBounds(480, 425, 255, 40);
		contentPane.add(lblWarning);

		listModel = new DefaultListModel<>();
		listModels = new JList<>(listModel);
		listModels.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		listModels.setBackground(new Color(70, 70, 70));
		listModels.setForeground(Color.WHITE);
		listModels.setSelectionBackground(new Color(211, 47, 47));
		listModels.setSelectionForeground(Color.WHITE);
		listModels.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(listModels);
		scrollPane.setBounds(470, 75, 275, 350);
		scrollPane.setBackground(new Color(55, 55, 55));
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(55, 55, 55)));
		contentPane.add(scrollPane);

		JLabel lblDisponibleModels = new JLabel("Available Models");
		lblDisponibleModels.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponibleModels.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblDisponibleModels.setForeground(new Color(0, 0, 0));
		scrollPane.setColumnHeaderView(lblDisponibleModels);

		btnModifyCars = new JButton("MODIFY");
		btnModifyCars.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnModifyCars.setBounds(318, 475, 150, 55);
		btnModifyCars.setBackground(new Color(211, 47, 47));
		btnModifyCars.setForeground(Color.WHITE);
		btnModifyCars.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnModifyCars.setFocusPainted(false);
		btnModifyCars.setBorderPainted(false);
		contentPane.add(btnModifyCars);
		btnModifyCars.addActionListener(this);

		btnSell = new JButton("SELL");
		btnSell.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnSell.setBounds(84, 475, 150, 55);
		btnSell.setBackground(new Color(211, 47, 47));
		btnSell.setForeground(Color.WHITE);
		btnSell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnSell.setFocusPainted(false);
		btnSell.setBorderPainted(false);
		contentPane.add(btnSell);
		btnSell.addActionListener(this);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnDelete.setBounds(552, 475, 150, 55);
		btnDelete.setBackground(new Color(211, 47, 47));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnDelete.setFocusPainted(false);
		btnDelete.setBorderPainted(false);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);

		menuBar = new JMenuBar();
		menuBar.setBounds(666, 10, 110, 40);
		menuBar.setBackground(new Color(55, 55, 55));
		menuBar.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0)));
		contentPane.add(menuBar);

		mnUserMenu = new JMenu("Username");
		mnUserMenu.setPreferredSize(new Dimension(110, 40));
		mnUserMenu.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mnUserMenu.setForeground(Color.WHITE);
		mnUserMenu.setBackground(new Color(55, 55, 55));
		menuBar.add(mnUserMenu);

		mntmLocation = new JMenuItem("<dynamic>");
		mntmLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mntmLocation.setBackground(new Color(70, 70, 70));
		mntmLocation.setForeground(Color.WHITE);
		mnUserMenu.add(mntmLocation);
		mntmLocation.addActionListener(this);

		mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mntmLogOut.setBackground(new Color(70, 70, 70));
		mntmLogOut.setForeground(Color.WHITE);
		mnUserMenu.add(mntmLogOut);
		mntmLogOut.addActionListener(this);

		mnAdmin = new JMenu("Admin options");
		mnAdmin.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mnAdmin.setBackground(new Color(70, 70, 70));
		mnAdmin.setForeground(Color.WHITE);
		mnUserMenu.add(mnAdmin);

		mntmMngWorker = new JMenuItem("Manage workers");
		mntmMngWorker.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mntmMngWorker.setBackground(new Color(80, 80, 80));
		mntmMngWorker.setForeground(Color.WHITE);
		mnAdmin.add(mntmMngWorker);
		mntmMngWorker.addActionListener(this);

		mntmMngModel = new JMenuItem("Manage models");
		mntmMngModel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mntmMngModel.setBackground(new Color(80, 80, 80));
		mntmMngModel.setForeground(Color.WHITE);
		mnAdmin.add(mntmMngModel);
		mntmMngModel.addActionListener(this);

		// Opaque menu to get same colors
		mnUserMenu.setOpaque(true);
		mnAdmin.setOpaque(true);
		mntmLocation.setOpaque(true);
		mntmLogOut.setOpaque(true);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(WindowMain.class.getResource("/imgs/Logo.png")));
		lblNewLabel.setBounds(-40, -28, 255, 206);
		contentPane.add(lblNewLabel);

		loadDealer();
		loadModel();
		loadWorker();

		listModels.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String modelName = listModels.getSelectedValue();
					if (modelName != null) {
						Model model = models.get(modelName);
						String infoCar = "Name: " + model.getName_model() + "<br><br>" + "Mark: " + model.getMark()
								+ "<br><br>" + "Price: " + model.getPrice() + "€" + "<br><br>" + "Stock: "
								+ model.getStock();
						lblCarInfo.setText("<html>" + infoCar + "</htlm>");
					}
				}
			}
		});
	}

	public void loadModel() {
		models = cont.getModels(cont.getWorkingPlace(worker));
		listModel.clear();
		if (!models.isEmpty()) {
			for (Model m : models.values()) {
				listModel.addElement(m.getName_model());
			}
		}
	}

	public void loadDealer() {
		cardealer = cont.getWorkingPlace(worker);
		lblDealership.setText("Welcome to " + cardealer.getName());
		mntmLocation.setText("📍 " + cardealer.getLocation());
	}

	public void loadWorker() {
		mnUserMenu.setText(worker.getUser());
		if (!worker.isAdmin()) {
			btnDelete.setEnabled(false);
			btnDelete.setToolTipText("This is an Admin function!");
			btnDelete.setBackground(new Color(100, 100, 100));

			mnAdmin.setEnabled(false);
			mnAdmin.setToolTipText("This is an Admin function!");
			mnAdmin.setBackground(new Color(100, 100, 100));

			ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
			toolTipManager.setInitialDelay(0);
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
					lblCarInfo.setText("");
				}
			}
		}

		if (e.getSource() == mntmLocation) {
			try {
				String searchUrl = "https://www.google.com/search?q=" + cardealer.getLocation().replace(" ", "");
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(searchUrl));
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Failed while trying to open the browser.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == mntmLogOut) {
			this.dispose();
			cont.visualizarPantalla();
		}

		if (e.getSource() == btnModifyCars) {
			this.dispose();
			WindowModify win = new WindowModify(this, cont, worker);
			win.setVisible(true);

		}

		if (e.getSource() == btnSell) {

			this.dispose();
			WindowPurchase win = new WindowPurchase(this, worker, cont);
			win.setVisible(true);

		}

		if (e.getSource() == mntmMngWorker) {
			this.dispose();
			WindowMngWorker ven = new WindowMngWorker(this, cont, worker);
			ven.setVisible(true);
		}

		if (e.getSource() == mntmMngModel) {
			this.dispose();
			WindowCreateModel ven = new WindowCreateModel(this, cont, worker);
			ven.setVisible(true);
		}
	}
}