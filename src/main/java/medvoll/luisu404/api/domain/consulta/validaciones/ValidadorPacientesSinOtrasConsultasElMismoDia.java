package medvoll.luisu404.api.domain.consulta.validaciones;

import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import medvoll.luisu404.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacientesSinOtrasConsultasElMismoDia implements IValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(pacienteTieneOtraConsultaEnElDia){
            throw new ValidacionException("Paciente ya tiene una consulta reservada para ese dia");
        }
    }
}
