package canchas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import database.EntityManagerHelper;

public class RepositorioCancha {

	public static List<Cancha> buscarCanchasParaUnHorario(LocalDateTime horario){
		Query busquedaDeTodasLasCanchas=EntityManagerHelper.createQuery("SELECT c FROM Cancha c");
		@SuppressWarnings("unchecked")
		List<Cancha> todasLasCanchas=(List<Cancha>)busquedaDeTodasLasCanchas.getResultList();
		return todasLasCanchas.stream()
			.filter(cancha -> !cancha.getReservas().stream().anyMatch(reserva -> reserva.getFechaHoraInicio().equals(horario)))
			.collect(Collectors.toList());
	}
}
