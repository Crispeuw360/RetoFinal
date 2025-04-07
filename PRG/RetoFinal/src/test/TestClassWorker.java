package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Worker;

class TestClassWorker {

	private Worker worker;
	@BeforeEach
	void setUp() throws Exception {
		worker = new Worker("w1","1234",true,2);
	}

	@AfterEach
	void tearDown() throws Exception {
		worker = null;
	}

	@Test
	void testConstructor() {
		assertEquals(true,worker.isAdmin(),"Is not Admin");
		assertEquals("w1",worker.getUser(),"not the same User");
		assertEquals("1234",worker.getPassword(),"Not the same Password");
		assertEquals(2,worker.getId_car_dealer(),"Not the same id");
	}
	
	@Test
	void testEmptyConstructor() {
		Worker w1 = new Worker();
		assertEquals(false,w1.isAdmin(),"Is not Admin");
		assertEquals("",w1.getUser(),"not the same User");
		assertEquals("",w1.getPassword(),"Not the same Password");
	}
	
	@Test
	void testGettersSetters()
	{
		worker.setAdmin(false);
		worker.setUser("worker");
		worker.setPassword("4321");
		worker.setId_car_dealer(5);
		
		assertEquals(false,worker.isAdmin());
		assertEquals("worker",worker.getUser());
		assertEquals("4321",worker.getPassword());
		assertEquals(5,worker.getId_car_dealer());
	}
	
	@Test
	void testToString()
	{
		String expected = "Worker [admin=true, user=w1, password=1234, id_car_dealer=2]";
		assertEquals(expected,worker.toString(),"the ToString is not the same");
	}

}
