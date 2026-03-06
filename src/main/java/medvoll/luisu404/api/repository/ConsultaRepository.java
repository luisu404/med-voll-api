package medvoll.luisu404.api.repository;


import jakarta.validation.constraints.NotNull;
import medvoll.luisu404.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
