package canchas;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("semi-profesional")
public class JugadorSemiProfesional extends Jugador {

	public JugadorSemiProfesional() {
	}

	public JugadorSemiProfesional(String nombre, String apellido, String direccion, String telefono, Date fecNac, Paleta paleta) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDomicilio(direccion);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fecNac);
		this.setPaleta(paleta);
	}

	private int peso;
	private int altura;

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
