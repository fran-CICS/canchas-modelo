package canchas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cancha extends PersistentEntity {

	private String nombre;

	private Boolean tieneIluminacion;

	@JoinColumn(name="canchaId")
	@OneToMany
	private Collection<Reserva> reservas = new ArrayList<Reserva>();

	@ManyToOne
	private Color color;

	public Cancha() {}

	public Cancha(String nombre) {
		this.nombre = nombre;
	}

	public Cancha(String nombre, Color color) {
		this.nombre = nombre;
		this.setColor(color);
	}
	
	public Cancha(String nombre, Color color, Boolean tieneLuces) {
		this.nombre = nombre;
		this.setColor(color);
		this.setTieneIluminacion(tieneLuces);
	}

	public Color getColor() {
		return color;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Collection<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void reservar(LocalDateTime inicioReserva, Collection<Jugador> jugadores) {
		reservas.add(new GeneradorReserva().inicioReserva(inicioReserva).jugadores(jugadores).cancha(this).build());
	}

	public Boolean getTieneIluminacion() {
		return tieneIluminacion;
	}

	public void setTieneIluminacion(Boolean tieneIluminacion) {
		this.tieneIluminacion = tieneIluminacion;
	}

	public List<Jugador> jugadoresQueEstuvieronAhi() {
		List<Jugador> jugadoresQueEstuvieron=new ArrayList<Jugador>();
		getReservas().stream().flatMap(reserva -> reserva.getInscripciones().stream()).forEach(inscripcion -> jugadoresQueEstuvieron.add(inscripcion.getJugador()));
		return jugadoresQueEstuvieron;
	}
}
