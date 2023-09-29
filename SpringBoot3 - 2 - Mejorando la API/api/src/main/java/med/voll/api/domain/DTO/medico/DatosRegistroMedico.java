package med.voll.api.domain.DTO.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.DTO.DatosDireccion;
import med.voll.api.domain.DTO.Especialidad;

public record DatosRegistroMedico(
		@NotBlank
		String nombre,
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		String telefono,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,8}")
		String documento, 
		
		@NotNull
		Especialidad especialidad,
		
		@NotNull
		@Valid
		DatosDireccion direccion) {}
