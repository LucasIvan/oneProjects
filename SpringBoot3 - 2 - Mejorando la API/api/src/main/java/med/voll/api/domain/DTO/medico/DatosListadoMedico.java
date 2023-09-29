package med.voll.api.domain.DTO.medico;

import med.voll.api.domain.model.Medico;
//record para limitar los datos que queremos listar
public record DatosListadoMedico(Long id, String nombre, String especialidad,String documento,String email) {
	public DatosListadoMedico (Medico medico) {
		this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
	}
}
