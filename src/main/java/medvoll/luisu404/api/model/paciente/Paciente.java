package medvoll.luisu404.api.model.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.luisu404.api.dto.medico.MedicoDTO;
import medvoll.luisu404.api.dto.paciente.PacienteDTO;
import medvoll.luisu404.api.model.direccion.Direccion;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="pacientes")
@Entity(name="Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direccion;

    public Paciente(PacienteDTO pacienteDTO) {
        this.id = null;
        this.nombre = pacienteDTO.nombre();
        this.email = pacienteDTO.email();
        this.telefono = pacienteDTO.telefono();
        this.documento = pacienteDTO.documento();
        this.direccion = new Direccion(pacienteDTO.direccion());
    }
}
