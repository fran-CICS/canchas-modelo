package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import canchas.Cancha;
import canchas.Color;
import canchas.Fabricante;
import canchas.GeneradorReserva;
import canchas.Jugador;
import canchas.JugadorAmateur;
import canchas.JugadorProfesional;
import canchas.JugadorSemiProfesional;
import canchas.Paleta;
import canchas.Reserva;

public class JugadorTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Fabricante davorBrasil = new Fabricante("Davor Brasil");
	Fabricante wilsonChina = new Fabricante("Wilson China");
	Color azul = new Color("azul");
	Color rojo = new Color("rojo");
	Paleta paletaWilsonRoja= new Paleta("Wilson", 8, rojo, wilsonChina);
	Paleta paletaDavorAzul=new Paleta("Davor", 5, azul, davorBrasil);
	Jugador jugadorAmateurCarlos = new JugadorAmateur("Carlos","Mendez", "Lobos 213 Wilde", "0303456"
			, new DateTime(1988,9,23,0,0).toDate() ,paletaDavorAzul);
	Jugador jugadorSemiProfesionalGallego = new JugadorSemiProfesional("Hector","Sambrano", "Valladolid 32 Madrid","404088"
			, new DateTime(1979,3,22,0,0).toDate() ,paletaWilsonRoja);
	Jugador jugadorProfesionalBrazuca = new JugadorProfesional("Mario","De Souza", "Crianças 44 Londrina","88404088"
			, new DateTime(1985,6,3,0,0).toDate() ,paletaWilsonRoja);
	Cancha solanoStadiumConLuz=new Cancha("Solano Stadium", azul, Boolean.TRUE);
	Cancha paddleLaPazSinLuz=new Cancha("Paddle La Paz", azul, Boolean.FALSE);
	Reserva reservaPrevia;
	
	@Before
	public void setUp() {
		List<Jugador> jugadoresCarlosVsGallego = dosJugadores(jugadorAmateurCarlos,
				jugadorSemiProfesionalGallego);
		
		reservaPrevia=new GeneradorReserva()
				.inicioReserva(LocalDateTime.of(2019, Month.FEBRUARY, 20, 18, 30))
				.jugadores(jugadoresCarlosVsGallego)
				.cancha(solanoStadiumConLuz)
				.build();
		
		solanoStadiumConLuz.getReservas().add(reservaPrevia);
		
		withTransaction(() -> {
			entityManager().persist(azul);
			entityManager().persist(rojo);
			entityManager().persist(paletaWilsonRoja);
			entityManager().persist(wilsonChina);
			entityManager().persist(paletaDavorAzul);
			entityManager().persist(davorBrasil);
			entityManager().persist(jugadorAmateurCarlos);
			entityManager().persist(jugadorSemiProfesionalGallego);
			entityManager().persist(jugadorProfesionalBrazuca);
			reservaPrevia.getInscripciones().stream().forEach(inscripcion ->
				entityManager().persist(inscripcion)
			);
			entityManager().persist(reservaPrevia);
			entityManager().persist(solanoStadiumConLuz);
			entityManager().persist(paddleLaPazSinLuz);
		});
	}

	private List<Jugador> dosJugadores(Jugador jugador1,
			Jugador jugador2) {
		List<Jugador> jugadoresCarlosVsGallego=new ArrayList<Jugador>();
		jugadoresCarlosVsGallego.add(jugador1);
		jugadoresCarlosVsGallego.add(jugador2);
		return jugadoresCarlosVsGallego;
	}

	// @Test
	public void testInsertColorRojo() {
		// Si yo no trabajo en el contexto de una transacción, no trabaja
		// O se hace todo o no se hace nada. Usa el concepto ACID
		withTransaction(() -> {
			Paleta paleta = new Paleta();
			paleta.setNombre("raquetona");
			paleta.setColor(rojo);
			// entityManager me permite guardar, modificar y buscar objetos en la base
			entityManager().persist(paleta);
			assertNotNull(rojo.getId());
			Color quizasRojo = entityManager().find(Color.class, rojo.getId());
			assertEquals(rojo, quizasRojo);
		});
	}

	// @Test
	public void testCambiarColorRojoPorAmarillo() {
		withTransaction(() -> {
			Color antesRojo = entityManager().find(Color.class, rojo.getId());
			antesRojo.setDescripcion("amarillo");
		});
	}

	@Test
	public void testJugadores() {
		withTransaction(() -> {
			entityManager().persist(jugadorAmateurCarlos);
			Jugador jugadorBuscado=entityManager().find(Jugador.class, jugadorAmateurCarlos.getId());
			assertEquals(jugadorBuscado, jugadorAmateurCarlos);
		});
	}
	
	@Test(expected = RuntimeException.class)
	public void testReservaFallaPorReservaPrevia() {
		List<Jugador> jugadoresBrazucaVsGallego = dosJugadores(jugadorProfesionalBrazuca,
				jugadorSemiProfesionalGallego);
		
		reservaPrevia=new GeneradorReserva()
				.inicioReserva(LocalDateTime.of(2019, Month.FEBRUARY, 20, 18, 30))
				.jugadores(jugadoresBrazucaVsGallego)
				.cancha(solanoStadiumConLuz)
				.build();
	}
	
	@Test(expected = RuntimeException.class)
	public void testReservaNocturnaEnCanchaSinIluminacionFalla() {
		List<Jugador> jugadoresCarlosVsGallego = dosJugadores(jugadorAmateurCarlos,
				jugadorSemiProfesionalGallego);
		
		reservaPrevia=new GeneradorReserva()
				.inicioReserva(LocalDateTime.of(2019, Month.FEBRUARY, 20, 18, 30))
				.jugadores(jugadoresCarlosVsGallego)
				.cancha(paddleLaPazSinLuz)
				.build();
	}
	
	@Test
	public void testEnQueCanchasEstuvoUnJugador() {
		List<Cancha> canchasEnLasQueEstuvo=jugadorAmateurCarlos.enQueCanchasEstuvo();
		Assert.assertTrue(!canchasEnLasQueEstuvo.isEmpty());
		Assert.assertEquals(solanoStadiumConLuz.getNombre(), canchasEnLasQueEstuvo.get(0).getNombre());
	}

}
