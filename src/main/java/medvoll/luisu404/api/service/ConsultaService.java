package medvoll.luisu404.api.service;

import medvoll.luisu404.api.domain.consulta.Consulta;
import medvoll.luisu404.api.domain.consulta.validaciones.IValidadorDeConsultas;
import medvoll.luisu404.api.domain.medico.Medico;
import medvoll.luisu404.api.dto.consulta.DatosCancelamientoConsulta;
import medvoll.luisu404.api.dto.consulta.DatosDetalleConsulta;
import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.infrastructure.exceptions.ValidacionException;
import medvoll.luisu404.api.repository.ConsultaRepository;
import medvoll.luisu404.api.repository.MedicoRepository;
import medvoll.luisu404.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired private ConsultaRepository consultaRepository;
    @Autowired private MedicoRepository medicoRepository;
    @Autowired private PacienteRepository pacienteRepository;

    @Autowired
    private List<IValidadorDeConsultas> validadores;


    public DatosDetalleConsulta reservar(DatosReservaConsulta datos){
        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id dado");
        }
        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un medico con el id dado");
        }

        validadores.forEach(v->v.validar(datos));

        var medico = elegirMedico(datos);
        if(medico == null){
            throw new ValidacionException("No existe un medico disponible con ese horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha(), null);
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un medico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }



}
