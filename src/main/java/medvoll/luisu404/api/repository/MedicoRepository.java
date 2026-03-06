package medvoll.luisu404.api.repository;import jakarta.validation.constraints.Future;import jakarta.validation.constraints.NotNull;import medvoll.luisu404.api.domain.medico.Especialidad;import medvoll.luisu404.api.domain.medico.Medico;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;import org.springframework.stereotype.Repository;import java.time.LocalDateTime;@Repository public interface MedicoRepository extends JpaRepository<Medico,Long> { Page<Medico> findAllByActivoTrue(Pageable paginacion);//    @Query(value = """ select m from Medico m where m.activo = true and m.especialidad = :especialidad and m.id not
//    in(
//        select c.medico.id from Consulta c
//        where c.fecha = :fecha)
//    order by rand()
//    limit 1
/*    """, nativeQuery = true) */
@Query(value = """
SELECT * FROM medicos m
WHERE m.activo = true
AND m.especialidad = :especialidad
AND m.id NOT IN (
    SELECT c.medico_id FROM consultas c
    WHERE c.fecha = :fecha
)
ORDER BY RAND()
LIMIT 1
""", nativeQuery = true)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);

    @Query("""
            select m.activo from Medico m where m.id = :idMedico
            """)
    boolean findActivoById(Long idMedico);
}
