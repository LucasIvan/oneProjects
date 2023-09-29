package med.voll.api.domain.DTO.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.DTO.DatosDireccion;

public record DatosActualizarMedico(
		@NotNull Long id, 
		String nombre, 
		String documento, 
		DatosDireccion direccion) {}
