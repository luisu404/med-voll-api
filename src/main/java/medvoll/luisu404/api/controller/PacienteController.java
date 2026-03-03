package medvoll.luisu404.api.controller;

import jakarta.validation.Valid;
import medvoll.luisu404.api.dto.paciente.DatosActualizaPaciente;
import medvoll.luisu404.api.dto.paciente.DatosListaPaciente;
import medvoll.luisu404.api.dto.paciente.DatosRegistroPaciente;
import medvoll.luisu404.api.model.paciente.Paciente;
import medvoll.luisu404.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")

public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){

        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }

    @GetMapping
    public Page<DatosListaPaciente> listar(@PageableDefault(size=10,sort={"nombre"}) Pageable paginacion){
        return pacienteRepository.findAllByActivoTrue(paginacion)
                .map(DatosListaPaciente::new);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizaPaciente pacienteDTO){
        var paciente = pacienteRepository.getReferenceById(pacienteDTO.id());
        paciente.actualizarInformacionesPaciente(pacienteDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
       var paciente =  pacienteRepository.getReferenceById(id);
       paciente.eliminarPaciente();
    }
}
