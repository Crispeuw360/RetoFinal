package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.CarDealership;

class TestClassCarDealership {

	private CarDealership cardealer;
	@BeforeEach
	void setUp() throws Exception {
		cardealer = new CarDealership("b2","bilbao",1);
	}

	@AfterEach
	void tearDown() throws Exception {
		cardealer = null;
	}

	@Test
	void testConstructor() {
		assertEquals("b2",cardealer.getName(),"the name is not the same");
		assertEquals("bilbao",cardealer.getLocation(),"not the same Location");
		assertEquals(1,cardealer.getId(),"Not the same id");
	}
	@Test
	void testEmptyConstructor() {
		CarDealership c1 = new CarDealership();
		
		assertEquals("",c1.getName(),"the name is not the same");
		assertEquals("",c1.getLocation(),"not the same Location");
		assertEquals(0,c1.getId(),"Not the same id");
	}
	
	@Test
	void testGettersSetters()
	{
		cardealer.setName("b3");
		cardealer.setLocation("cadiz");
		cardealer.setId(3);
		
		assertEquals("b3",cardealer.getName());
		assertEquals("cadiz",cardealer.getLocation());
		assertEquals(3,cardealer.getId());
	}
	
	@Test
	void testToString()
	{
		String expected = "CarDealership [name=b2, location=bilbao, id=1]";
		assertEquals(expected,cardealer.toString(),"the ToString is not showing what is expected");
	}

}
