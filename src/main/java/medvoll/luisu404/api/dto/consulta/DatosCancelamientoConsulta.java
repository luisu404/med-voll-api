package medvoll.luisu404.api.dto.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamiento motivo
) {
}
