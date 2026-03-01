package medvoll.luisu404.api.repository;

import medvoll.luisu404.api.model.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
