package medvoll.luisu404.api.domain.consulta.validaciones;

import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import medvoll.luisu404.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements IValidadorDeConsultas{
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datos){
        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if(!pacienteActivo){
            throw new ValidacionException("Consulta no puede ser reservada con pacientes inactivos");
        }
    }
}
