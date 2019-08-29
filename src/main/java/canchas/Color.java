package canchas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Color {
  @Id
  @GeneratedValue
  private Long id;
  
	public Long getId() {
	return id;
}
	
public Color() {}

public void setId(Long id) {
	this.id = id;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

	public Color(String descripcion) {
		this.descripcion = descripcion;
	}

	private String descripcion;
}
