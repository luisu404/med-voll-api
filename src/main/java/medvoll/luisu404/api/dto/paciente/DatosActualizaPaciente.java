package medvoll.luisu404.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import medvoll.luisu404.api.dto.direccion.DatosDireccion;

public record DatosActualizaPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {

}
