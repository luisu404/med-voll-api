package medvoll.luisu404.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.luisu404.api.dto.paciente.DatosActualizaPaciente;
import medvoll.luisu404.api.dto.paciente.DatosRegistroPaciente;
import medvoll.luisu404.api.domain.direccion.Direccion;

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
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.id = null;
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documento = datosRegistroPaciente.documento();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarInformacionesPaciente(@Valid DatosActualizaPaciente pacienteDTO) {
        if (pacienteDTO.nombre()!= null){
            this.nombre = pacienteDTO.nombre();
        }
        if (pacienteDTO.telefono()!= null){
            this.telefono = pacienteDTO.telefono();
        }
        if (pacienteDTO.direccion()!= null){
            this.direccion.actualizarDireccion(pacienteDTO.direccion());
        }
    }
    public void eliminarPaciente() {
        this.activo = false;
    }
}
