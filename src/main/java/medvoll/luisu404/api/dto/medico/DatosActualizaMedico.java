package medvoll.luisu404.api.dto.medico;

import jakarta.validation.constraints.NotNull;
import medvoll.luisu404.api.dto.direccion.DatosDireccion;

public record DatosActualizaMedico(

        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion

) {
}
