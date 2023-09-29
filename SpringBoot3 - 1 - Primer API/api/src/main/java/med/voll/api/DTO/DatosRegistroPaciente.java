package med.voll.api.DTO;

public record DatosRegistroPaciente(String nombre, String email, String telefono, String documentoIdentidad,
		DatosDireccion direccion) {
}
