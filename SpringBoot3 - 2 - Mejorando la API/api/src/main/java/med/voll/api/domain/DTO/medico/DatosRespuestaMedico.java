package med.voll.api.domain.DTO.medico;

import med.voll.api.domain.DTO.DatosDireccion;

public record DatosRespuestaMedico(
		Long id,
		String nombre,
		String email,
		String telefono,
		String especialidad,
		DatosDireccion direccion) {}
