package canchas;

import javax.persistence.Entity;

@Entity
public class Fabricante extends PersistentEntity{
	
	private String nombreFabricante;
	
	public Fabricante() {}
	
	public Fabricante(String nombre) {
		this.setNombreFabricante(nombre);
	}

	public String getNombreFabricante() {
		return nombreFabricante;
	}

	public void setNombreFabricante(String nombreFabricante) {
		this.nombreFabricante = nombreFabricante;
	}

}
