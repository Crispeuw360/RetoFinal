package modelo;

public class StockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockException(){
		System.out.println("The stock of this model is 0, purchase can not be done");
	}
}
