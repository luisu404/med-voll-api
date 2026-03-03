package medvoll.luisu404.api.dto.paciente;
import medvoll.luisu404.api.model.paciente.Paciente;

public record DatosListaPaciente(
        Long id,
        String nombre,
        String email,
        String documento
) {
    public DatosListaPaciente(Paciente paciente) {
        this(
           paciente.getId(),
           paciente.getNombre(),
           paciente.getEmail(),
           paciente.getDocumento()
        );
    }
}
