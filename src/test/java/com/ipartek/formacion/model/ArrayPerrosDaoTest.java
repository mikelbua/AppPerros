package com.ipartek.formacion.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.ipartek.formacion.model.ArrayPerroDAO;
import com.ipartek.formacion.model.pojo.Perro;

public class ArrayPerrosDaoTest {

	private static ArrayPerroDAO dao;
	private static Perro mock;
	private static  int indice = 1;
	private static final int MOCK_INESISTENTE = -1;
	private static final String MOCK_NOMBRE = "pulgas";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		dao = ArrayPerroDAO.getinstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		
		mock = new Perro(1, MOCK_NOMBRE, "");
		dao.create(mock);
	}

	@After
	public void tearDown() throws Exception {
		dao.delete(mock.getId());
		mock = null;
	}

	@Test
	public void testGetAll() {

		assertEquals("deberia devolver uno", indice, dao.getAll().size());

		try {
			dao.delete(mock.getId());
		} catch (Exception e) {

			e.printStackTrace();
		}

		assertEquals("deberia devolver 0 por que hemos borrado el objeto", 0, dao.getAll().size());
	}

	@Test
	public void testGetById() {
		assertNull("no deberia existir ningun perro", dao.getById(MOCK_INESISTENTE));

		Perro p = dao.getById(indice);
		assertSame("deberia existir pulgas", p, mock);
	}

	@Test
	public void testDelete() {
		try {
			
			//crear un perro para eliminarlo
			Perro pEliminar = new Perro ("pulgas345");
			
			dao.create(pEliminar);
			Perro p = dao.delete(345);
			
			assertSame(p, mock);
			assertEquals("no debera haber perros", 0, dao.getAll().size());

			dao.delete(MOCK_INESISTENTE);
			fail("Deberia dar erro y no printar este mensaje");

		} catch (Exception e) {
			assertTrue("Lanzada exception correctamente al eliminar perro con ID que no existe", true);
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testCreate() {
		
	}

}
