package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Client;

class TestClassClient {

	private Client client;
	@BeforeEach
	void setUp() throws Exception {
		client = new Client("1234","email","user123","123");
	}

	@AfterEach
	void tearDown() throws Exception {
		client = null;
	}

	@Test
	void testConstructor() {
		assertEquals("1234",client.getDni(),"Not the same Dni");
		assertEquals("email",client.getEmail(),"Not the same Email");
		assertEquals("user123",client.getUser_(),"Not the same user");
		assertEquals("123",client.getPassword_(),"not the same password");
	}
	
	@Test
	void testEmptyConstructor() {
		Client c1 = new Client();
		
		assertEquals("",c1.getDni(),"Not the same Dni");
		assertEquals("",c1.getEmail(),"Not the same Email");
		assertEquals("",c1.getUser_(),"Not the same user");
		assertEquals("",c1.getPassword_(),"not the same password");
	}
	
	@Test
	void testGettersSetters()
	{
		client.setDni("4321");
		client.setEmail("MAIL");
		client.setUser_("USER321");
		client.setPassword_("321");
		
		assertEquals("4321",client.getDni());
		assertEquals("MAIL",client.getEmail());
		assertEquals("USER321",client.getUser_());
		assertEquals("321",client.getPassword_());
	}
	@Test
	void testToString()
	{
		String expected = "Client [dni=1234, email=email, user_=user123, password_=123]";
		assertEquals(expected,client.toString(),"The ToString is now showing what is expected");
	}

}
