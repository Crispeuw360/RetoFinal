package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Model;

class TestClassModel {

	private Model model;

	@BeforeEach
	void setUp() throws Exception {
		model = new Model("Mustang", "Ford", 10, 55000.0, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		model = null;
	}

	@Test
	void testConstructor() {
		assertEquals("Mustang", model.getName_model(), "El nombre del modelo no coincide");
        assertEquals("Ford", model.getMark(), "La marca no coincide");
        assertEquals(10, model.getStock(), "El stock no coincide");
        assertEquals(55000.0, model.getPrice(), 0.01, "El precio no coincide");
        assertEquals(1, model.getId_car_dealer(), "El ID del concesionario no coincide");
	}
	@Test
	void testEmptyConstructor() {
	    Model emptyModel = new Model(); // Usamos el constructor vacío
	    
	    assertEquals("", emptyModel.getName_model(), "El nombre del modelo debería ser una cadena vacía");
	    assertEquals("", emptyModel.getMark(), "La marca debería ser una cadena vacía");
	    assertEquals(0, emptyModel.getStock(), "El stock debería ser 0");
	    assertEquals(0.0, emptyModel.getPrice(), 0.01, "El precio debería ser 0.0");
	    assertEquals(0, emptyModel.getId_car_dealer(), "El ID del concesionario debería ser 0");
	}
	
	@Test
	void testSettersGetters() {
		model.setName_model("Camaro");
		model.setMark("chevrolet");
		model.setStock(10);
		model.setPrice(70000.0);
		model.setId_car_dealer(3);
		
		assertEquals("Camaro",model.getName_model());
		assertEquals("chevrolet",model.getMark());
		assertEquals(10,model.getStock());
		assertEquals(70000.0,model.getPrice());
		assertEquals(3,model.getId_car_dealer());
	}
	
	@Test
	void testToString()
	{
		String expected = "Model [name_model=Mustang, mark=Ford, stock=10, price=55000.0, id_car_dealer=1]";
		assertEquals(expected,model.toString(),"the method String is not achiving what is expected");
	}

}
