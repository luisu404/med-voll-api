package medvoll.luisu404.api.model.medico;

import medvoll.luisu404.api.model.direccion.Direccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad,
        Direccion direccion
) {
}
