package canchas;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.Collection;

public class GeneradorReserva {

	private LocalDateTime inicioReserva;
	private Collection<Jugador> jugadores;
	private Cancha cancha;

	public GeneradorReserva inicioReserva(LocalDateTime inicioReserva) {
		this.inicioReserva = inicioReserva;
		return this;
	}

	public GeneradorReserva jugadores(Collection<Jugador> jugadores) {
		this.jugadores = jugadores;
		return this;
	}

	private LocalDateTime finReserva() {
		return inicioReserva.plusHours(1);
	}

	public GeneradorReserva cancha(Cancha cancha) {
		this.cancha = cancha;
		return this;
	}

	public Reserva build() {
		if(inicioReserva.getHour()<12||(inicioReserva.getHour()==12&&inicioReserva.getMinute()>0)) {
			throw new RuntimeException("Muy temprano macho");
		}
		if(!cancha.getTieneIluminacion()
				&&(inicioReserva.getHour()>18||(inicioReserva.getHour()==18&&inicioReserva.getMinute()>0))) {
			throw new RuntimeException("En esa cancha no se puede jugar despues de las 18");
		}
		
		if(cancha.getReservas().stream().anyMatch(reserva -> reserva.getFechaHoraInicio().equals(inicioReserva))){
			throw new RuntimeException("Ya hay una reserva para esa cancha en ese horario");
		}
		
		return new Reserva(//
				jugadores.stream().map(jugador -> jugador.crearInscripcion()).collect(toList()), //
				inicioReserva, //
				finReserva(), //
				cancha.getColor());
	}

}
