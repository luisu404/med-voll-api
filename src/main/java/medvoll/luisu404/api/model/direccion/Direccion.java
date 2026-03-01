package medvoll.luisu404.api.model.direccion;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Direccion(
        String calle,
        String numero,
        String complemento,
        String barrio,
        @JsonAlias("codigo_postal")
        String codigoPostal,
        String ciudad,
        String estado
) {
}
