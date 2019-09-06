package canchas;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("amateur")
public class JugadorAmateur extends Jugador {

	public JugadorAmateur() {}
	
	public JugadorAmateur(String nombre, String apellido, String direccion, String telefono, Date fecNac, Paleta paleta) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDomicilio(direccion);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fecNac);
		this.setPaleta(paleta);
	}
}
