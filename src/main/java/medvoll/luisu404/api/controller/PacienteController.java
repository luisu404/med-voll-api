package medvoll.luisu404.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.luisu404.api.dto.paciente.DatosActualizaPaciente;
import medvoll.luisu404.api.dto.paciente.DatosDetallePaciente;
import medvoll.luisu404.api.dto.paciente.DatosListaPaciente;
import medvoll.luisu404.api.dto.paciente.DatosRegistroPaciente;
import medvoll.luisu404.api.domain.paciente.Paciente;
import medvoll.luisu404.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(datosRegistroPaciente);
        pacienteRepository.save(paciente);
        var uri = uriComponentsBuilder.path("api/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallePaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size=10,sort={"nombre"}) Pageable paginacion){
        var page = pacienteRepository.findAllByActivoTrue(paginacion)
                .map(DatosListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizaPaciente pacienteDTO){
        var paciente = pacienteRepository.getReferenceById(pacienteDTO.id());
        paciente.actualizarInformacionesPaciente(pacienteDTO);
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
       var paciente =  pacienteRepository.getReferenceById(id);
       paciente.eliminarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var paciente =  pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }
}
