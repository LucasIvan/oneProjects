package med.voll.api.domain.DTO.paciente;

import med.voll.api.domain.DTO.DatosDireccion;

public record DatosRegistroPaciente(String nombre, String email, String telefono, String documentoIdentidad,
		DatosDireccion direccion) {
}
