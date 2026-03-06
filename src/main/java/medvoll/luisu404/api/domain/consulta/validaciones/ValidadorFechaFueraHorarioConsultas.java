package medvoll.luisu404.api.domain.consulta.validaciones;

import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFechaFueraHorarioConsultas  implements IValidadorDeConsultas{
    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperteturaClinica = fechaConsulta.getHour()<7;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour()<18;
        //
        if (domingo || horarioAntesDeAperteturaClinica || horarioDespuesDeCierreClinica) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atendimineto de la clinica");
        }
    }
}
