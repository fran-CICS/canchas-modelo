package canchas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("amateur")
public class JugadorAmateur extends Jugador {

	public JugadorAmateur() {}
}
