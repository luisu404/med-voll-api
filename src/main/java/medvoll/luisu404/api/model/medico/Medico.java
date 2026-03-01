package medvoll.luisu404.api.model.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.luisu404.api.dto.direccion.DireccionDTO;
import medvoll.luisu404.api.dto.medico.MedicoDTO;
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
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;


    public Medico(MedicoDTO medicoDTO) {
        this.id = null;
        this.nombre = medicoDTO.nombre();
        this.email = medicoDTO.email();
        this.telefono = medicoDTO.telefono();
        this.documento = medicoDTO.documento();
        this.especialidad = medicoDTO.especialidad();
        this.direccion = new Direccion(medicoDTO.direccion());
    }
}
