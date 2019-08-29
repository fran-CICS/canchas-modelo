package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import canchas.Color;
import canchas.Jugador;
import canchas.JugadorAmateur;
import canchas.Paleta;

public class JugadorTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Before
	public void setUp() {
		
	}
	
//	@Test
	public void testInsertColorRojo() {
		//Si yo no trabajo en el contexto de una transacción, no trabaja
		//O se hace todo o no se hace nada. Usa el concepto ACID
		withTransaction(() -> {
			Color rojo = new Color("rojo");
			Paleta paleta = new Paleta();
			paleta.setNombre("raquetona");
			paleta.setColor(rojo);
			//entityManager me permite guardar, modificar y buscar objetos en la base
			entityManager().persist(rojo);
			entityManager().persist(paleta);
			assertNotNull(rojo.getId());
			Color quizasRojo=entityManager().find(Color.class, rojo.getId());
			assertEquals(rojo,quizasRojo);
		});
	}
	
//	@Test
	public void testCambiarColorRojoPorAmarillo() {
		withTransaction(() -> {
			Color antesRojo=entityManager().find(Color.class, 1l);
			antesRojo.setDescripcion("amarillo");
		});
	}
	
	@Test
	public void testJugadores() {
		withTransaction(() -> {
			Jugador jugadorAmateur = new JugadorAmateur();
			entityManager().persist(jugadorAmateur);
		});
		
	}
	
}
