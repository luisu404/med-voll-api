package medvoll.luisu404.api.domain.consulta.validaciones;

import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorConsultaConAnticipacion implements IValidadorDeConsultas {
    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferencieEnMninutos = Duration.between(ahora, fechaConsulta).toMinutes();
        if (diferencieEnMninutos < 30){
            throw new ValidacionException("Horario seleccionado con menos que 30 minutos de anticipacion");
        }
    }
}
