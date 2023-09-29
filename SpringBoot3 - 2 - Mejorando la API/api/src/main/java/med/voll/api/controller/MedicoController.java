package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.DTO.DatosDireccion;
import med.voll.api.domain.DTO.medico.DatosActualizarMedico;
import med.voll.api.domain.DTO.medico.DatosListadoMedico;
import med.voll.api.domain.DTO.medico.DatosRegistroMedico;
import med.voll.api.domain.DTO.medico.DatosRespuestaMedico;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
		Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
		
		DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), 
				medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), 
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), 
						medico.getDireccion().getPiso()));
		
		URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(url).body(datosRespuestaMedico);
	}

	@GetMapping
	public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2) Pageable pageable) {
		//return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);
		//Esto muestra todo, pero como deseamos mostrar solo los activos realizaremos lo siguiente
		return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
		return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), 
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), 
						medico.getDireccion().getPiso())));
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
	public ResponseEntity<?> eliminarMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.darBaja();
		return ResponseEntity.noContent().build();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		var datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), 
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), 
						medico.getDireccion().getPiso()));
		return ResponseEntity.ok(datosRespuestaMedico);
	} 
}
