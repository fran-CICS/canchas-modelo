package canchas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Paleta extends PersistentEntity{
	
	private String nombre;
	
	private Integer grosor;
	
	@ManyToOne
	private Color color;
	
	@ManyToOne
	private Fabricante fabricante;

	public Paleta() {}
	
	public Paleta(String nombre, Integer grosor, Color color, Fabricante fabricante) {}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getGrosor() {
		return grosor;
	}
	public void setGrosor(Integer grosor) {
		this.grosor = grosor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
  
}
