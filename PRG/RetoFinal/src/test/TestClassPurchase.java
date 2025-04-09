package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Purchase;

class TestClassPurchase {

	private Purchase purchase;
	@BeforeEach
	void setUp() throws Exception {
		purchase = new Purchase("12345678A", "Mustang", 1, LocalDate.of(2024, 3, 27), 2, 1001);
	}

	@AfterEach
	void tearDown() throws Exception {
		purchase = null;
	}

	@Test
    void testConstructor() {
        assertEquals("12345678A", purchase.getDni(), "El DNI no coincide");
        assertEquals("Mustang", purchase.getName_model(), "El nombre del modelo no coincide");
        assertEquals(1, purchase.getId_car_dealer(), "El ID del concesionario no coincide");
        assertEquals(LocalDate.of(2024, 3, 27), purchase.getDate_purchase(), "La fecha de compra no coincide");
        assertEquals(2, purchase.getQuantity(), "La cantidad no coincide");
        assertEquals(1001, purchase.getId_purchase(), "El ID de compra no coincide");
    }

    @Test
    void testConstructorVacio() {
        Purchase emptyPurchase = new Purchase(); // Constructor vacío

        assertEquals("", emptyPurchase.getDni(), "El DNI debería ser una cadena vacía");
        assertEquals("", emptyPurchase.getName_model(), "El nombre del modelo debería ser una cadena vacía");
        assertEquals(0, emptyPurchase.getId_car_dealer(), "El ID del concesionario debería ser 0");
        assertNull(emptyPurchase.getDate_purchase(), "La fecha de compra debería ser null");
        assertEquals(0, emptyPurchase.getQuantity(), "La cantidad debería ser 0");
        assertEquals(0, emptyPurchase.getId_purchase(), "El ID de compra debería ser 0");
    }

    @Test
    void testSettersAndGetters() {
        purchase.setDni("87654321B");
        purchase.setName_model("Camaro");
        purchase.setId_car_dealer(2);
        purchase.setDate_purchase(LocalDate.of(2025, 1, 15));
        purchase.setQuantity(5);
        purchase.setId_purchase(2002);

        assertEquals("87654321B", purchase.getDni());
        assertEquals("Camaro", purchase.getName_model());
        assertEquals(2, purchase.getId_car_dealer());
        assertEquals(LocalDate.of(2025, 1, 15), purchase.getDate_purchase());
        assertEquals(5, purchase.getQuantity());
        assertEquals(2002, purchase.getId_purchase());
    }

    @Test
    void testToString() {
        String expected = "Purchase [dni=12345678A, name_model=Mustang, id_car_dealer=1, date_purchase=2024-03-27, quantity=2, id_purchase=1001]";
        assertEquals(expected, purchase.toString(), "El método toString no devuelve el resultado esperado");
    }

}
