package Exception;

import javax.swing.JOptionPane;

public class StockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockException(){
		JOptionPane.showMessageDialog(null, "The stock of this model is 0, purchase can not be done", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}
