package medvoll.luisu404.api.dto.paciente;

import medvoll.luisu404.api.domain.direccion.Direccion;
import medvoll.luisu404.api.domain.paciente.Paciente;

public record DatosDetallePaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Direccion direccion
) {
    public DatosDetallePaciente (Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                paciente.getDireccion()
        );
    }
}
