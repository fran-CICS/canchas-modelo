package canchas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Inscripcion extends PersistentEntity {

	@OneToOne
	private Paleta paleta;

	@ManyToOne
	private Jugador jugador;
	
	public Inscripcion() {}

	public Inscripcion(Jugador jugador, Paleta paleta) {
		this.jugador = jugador;
		this.paleta = paleta;
	}

	public Paleta getPaleta() {
		return paleta;
	}

	public void setPaleta(Paleta paleta) {
		this.paleta = paleta;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
