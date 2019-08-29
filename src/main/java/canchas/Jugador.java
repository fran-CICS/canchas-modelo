package canchas;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="categoria")
public abstract class Jugador extends PersistentEntity{
	
	public Jugador() {}
	
  private String nombre;
  
  private String apellido;
  
  private String domicilio;
  
  private String telefono;
  
  private Date fechaNacimiento;

  @OneToOne(cascade= {CascadeType.PERSIST})
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
}
