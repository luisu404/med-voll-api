package medvoll.luisu404.api.dto.medico;

import medvoll.luisu404.api.domain.direccion.Direccion;
import medvoll.luisu404.api.domain.medico.Especialidad;
import medvoll.luisu404.api.domain.medico.Medico;

public record DatosDetalleMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad,
        Direccion direccion
) {
    public DatosDetalleMedico (Medico medico){
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad(),
                medico.getDireccion()
        );
    }
}
