package medvoll.luisu404.api.model.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.luisu404.api.dto.medico.DatosActualizaMedico;
import medvoll.luisu404.api.dto.medico.DatosRegistroMedico;
import medvoll.luisu404.api.model.direccion.Direccion;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="medicos")
@Entity(name="Medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico medicoDTO) {
        this.id = null;
        this.activo = true;
        this.nombre = medicoDTO.nombre();
        this.email = medicoDTO.email();
        this.telefono = medicoDTO.telefono();
        this.documento = medicoDTO.documento();
        this.especialidad = medicoDTO.especialidad();
        this.direccion = new Direccion(medicoDTO.direccion());
    }

    public void actualizarInformacionesMedico(@Valid DatosActualizaMedico medicoDTO) {
        if (medicoDTO.nombre()!= null){
            this.nombre = medicoDTO.nombre();
        }
        if (medicoDTO.telefono()!= null){
            this.telefono = medicoDTO.telefono();
        }
        if (medicoDTO.direccion()!= null){
            this.direccion.actualizarDireccion(medicoDTO.direccion());
        }
    }

    public void eliminarMedico() {
        this.activo = false;
    }
}
