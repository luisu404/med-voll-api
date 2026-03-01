package medvoll.luisu404.api.model.paciente;

import medvoll.luisu404.api.model.direccion.Direccion;

public record DatosRegistroPaciente(
        String nombre,
        String email,
        String telefono,
        String documento,
        Direccion direccion
) {
}
