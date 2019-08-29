package canchas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Paleta {
	
	@Id
	@GeneratedValue
	private Long paletaId;
	
	public Paleta() {}
	private String nombre;
	
	private Integer grosor;
	@ManyToOne
	private Color color;
	
	public Long getPaletaId() {
		return paletaId;
	}
	public void setPaletaId(Long paletaId) {
		this.paletaId = paletaId;
	}
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
  
}
