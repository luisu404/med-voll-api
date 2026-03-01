package medvoll.luisu404.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medvoll.luisu404.api.dto.direccion.DireccionDTO;

public record PacienteDTO(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{10,12}")  String telefono,
        @NotBlank @Pattern(regexp = "\\d{11,13}") String documento,
        @NotNull @Valid DireccionDTO direccion
        ) {
}
