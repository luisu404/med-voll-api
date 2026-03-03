package medvoll.luisu404.api.repository;

import medvoll.luisu404.api.model.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findAllByActivoTrue(Pageable paginacion);
}
