package canchas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Query;

import database.EntityManagerHelper;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "categoria")
@Entity
public abstract class Jugador extends PersistentEntity {

	public Jugador() {}

	private String nombre;

	private String apellido;

	private String domicilio;

	private String telefono;

	private Date fechaNacimiento;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Paleta paleta;

	public Inscripcion crearInscripcion() {
		return new Inscripcion(this, this.paleta);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Paleta getPaleta() {
		return paleta;
	}

	public void setPaleta(Paleta paletaB) {
		this.paleta = paletaB;
	}

	public List<Cancha> enQueCanchasEstuvo() {
		EntityManagerHelper.withTransaction(() -> {
			List<Cancha> canchas=new ArrayList<Cancha>();
			Query buscarCanchas=EntityManagerHelper
					.createQuery("SELECT can.id FROM Inscripcion ins, Reserva res, Cancha can "
							+" WHERE ins.reserva.id=res.id AND res.cancha.id=can.id AND ins.jugador=?")
					.setParameter(1, this.getId());
			List<Long> idsCanchasQueEstuvo=buscarCanchas.getResultList();
			for (Long idCancha : idsCanchasQueEstuvo) {
				Cancha canchaQueEstuvo=EntityManagerHelper.getEntityManager().find(Cancha.class,idCancha);
				canchas.add(canchaQueEstuvo);
			}
			return canchas;
		});
		return new ArrayList<Cancha>();
	}
}
