package canchas;

import javax.persistence.Entity;

@Entity
public class Color extends PersistentEntity {

	private String descripcion;
	
	public Color() {}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Color(String descripcion) {
		this.descripcion = descripcion;
	}
}
