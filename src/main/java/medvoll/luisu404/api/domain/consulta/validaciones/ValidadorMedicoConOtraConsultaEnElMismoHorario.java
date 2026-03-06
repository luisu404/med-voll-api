package medvoll.luisu404.api.domain.consulta.validaciones;

import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import medvoll.luisu404.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements IValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsulta datos){
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidacionException("Medico ya tiene otra consulta esa misma fecha y hora");
        }
    }
}
