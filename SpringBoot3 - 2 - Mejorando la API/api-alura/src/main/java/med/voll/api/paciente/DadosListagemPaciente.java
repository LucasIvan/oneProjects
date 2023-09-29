package med.voll.api.paciente;

public record DadosListagemPaciente(Long id, String nombre, String email, String cpf) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getCpf());
    }

}
