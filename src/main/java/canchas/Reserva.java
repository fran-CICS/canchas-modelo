package canchas;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reserva extends PersistentEntity{

	@JoinColumn(name="reservaId")
	@OneToMany
	private Collection<Inscripcion> inscripciones;

	private LocalDateTime fechaHoraInicio;

	private LocalDateTime fechaHoraFin;

	@OneToOne
	private Color colorCancha;

	public Reserva() {}
			
	public Reserva(Collection<Inscripcion> inscripciones, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin,
			Color colorCancha) {
		this.inscripciones = inscripciones;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.colorCancha = colorCancha;
	}

	public Collection<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Collection<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public Color getColorCancha() {
		return colorCancha;
	}

	public void setColorCancha(Color colorCancha) {
		this.colorCancha = colorCancha;
	}

}
