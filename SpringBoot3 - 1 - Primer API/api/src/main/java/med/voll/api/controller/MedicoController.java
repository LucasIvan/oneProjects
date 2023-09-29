package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.DatosActualizarMedico;
import med.voll.api.DTO.DatosListadoMedico;
import med.voll.api.DTO.DatosRegistroMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
		medicoRepository.save(new Medico(datosRegistroMedico));
	}

	@GetMapping
	public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable pageable) {
		//return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);
		//Esto muestra todo, pero como deseamos mostrar solo los activos realizaremos lo siguiente
		return medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
	}
	/* COMO NO SE DESEA ELIMINAR LOS REGISTROS EL CÓDIGO QUEDA PARA REFERENCIA NOMÁS
	 * DELETE Registro
	@DeleteMapping("/{id}")
	@Transactional
	public void eliminarMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medicoRepository.delete(medico);
	}
	*/
	
	//DELETE Logico
	@DeleteMapping("/{id}")
	@Transactional
	public void eliminarMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.darBaja();
	} 
}
