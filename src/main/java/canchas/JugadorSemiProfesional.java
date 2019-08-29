package canchas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("semi-profesional")
public class JugadorSemiProfesional extends Jugador {
	
	public JugadorSemiProfesional() {}

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
