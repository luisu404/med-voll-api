package medvoll.luisu404.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medvoll.luisu404.api.dto.direccion.DireccionDTO;
import medvoll.luisu404.api.model.medico.Especialidad;

public record MedicoDTO(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{10,12}")  String telefono,
        @NotBlank @Pattern(regexp = "\\d{11,13}") String documento,
        @NotNull Especialidad especialidad,
        @NotNull @Valid DireccionDTO direccion
) {
}
