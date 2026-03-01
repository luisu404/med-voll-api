package medvoll.luisu404.api.model.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.luisu404.api.dto.direccion.DireccionDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion(DireccionDTO direccionDTO){
        this.calle = direccionDTO.calle();
        this.numero = direccionDTO.numero();
        this.complemento = direccionDTO.complemento();
        this.barrio = direccionDTO.barrio();
        this.codigo_postal = direccionDTO.codigo_postal();
        this.ciudad = direccionDTO.ciudad();
        this.estado = direccionDTO.estado();
    }
}

