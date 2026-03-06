package medvoll.luisu404.api.domain.consulta.validaciones;

import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import medvoll.luisu404.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements IValidadorDeConsultas {
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datos){
        if (datos.idMedico() == null){
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidacionException("Consulta no puede ser reservada con medicos inactivos");
        }
    }
}
