package medvoll.luisu404.api.repository;

import jakarta.persistence.EntityManager;
import medvoll.luisu404.api.domain.consulta.Consulta;
import medvoll.luisu404.api.domain.medico.Especialidad;
import medvoll.luisu404.api.domain.medico.Medico;
import medvoll.luisu404.api.domain.paciente.Paciente;
import medvoll.luisu404.api.dto.direccion.DatosDireccion;
import medvoll.luisu404.api.dto.medico.DatosRegistroMedico;
import medvoll.luisu404.api.dto.paciente.DatosRegistroPaciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Debería devolver null cuando el medico buscado existe pero no esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        var lunesSiguenteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = registrarMedico("Medico 1","medico1@gmail.com","12345678999", Especialidad.DERMATOLOGIA);
        var paciente = registrarPaciente("Paciente 1","paciente1@gmail.com","99932132132");
        registrarConsulta(medico, paciente, lunesSiguenteALas10);

        var medicoLibre  = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA,lunesSiguenteALas10);
        assertThat(medicoLibre).isNull();
    }

//    @Test
//    @DisplayName("Debería devolver medico cuando el medico buscado existe esta disponible en esa fecha")
//    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
//        var lunesSiguenteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
//
//        var medico = registrarMedico("Medico 1","medico1@gmail.com","12345678999", Especialidad.DERMATOLOGIA);
//
//        var medicoLibre  = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.DERMATOLOGIA,lunesSiguenteALas10);
//        assertThat(medicoLibre).isEqualTo(medico);
//    }


    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        em.persist(new Consulta(null, medico,paciente,fecha,null));
    }
    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        em.flush();
        return medico;
    }
    private Paciente registrarPaciente(String nombre, String email, String documento){
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }
    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad){
        return new DatosRegistroMedico(
                nombre,
                email,
                "1231231310",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento){
        return new DatosRegistroPaciente(
                nombre,
                email,
                "3213213210",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion(){
        return new DatosDireccion(
                "Calle Rompete un Deo",
                "22",
                "cuidad 2",
                "La Cueba",
                "Neyba",
                "82000",
                "Bahoruco"
        );
    }




}