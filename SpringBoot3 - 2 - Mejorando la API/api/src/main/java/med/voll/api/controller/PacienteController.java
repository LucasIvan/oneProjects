package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.domain.DTO.paciente.DatosRegistroPaciente;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@PostMapping
	public void registrarPaciente(@RequestBody DatosRegistroPaciente datos) {
		System.out.println("datos recebidos: " + datos);
	}
}
