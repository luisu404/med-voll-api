package medvoll.luisu404.api.controller;

import jakarta.validation.Valid;
import medvoll.luisu404.api.dto.medico.DatosActualizaMedico;
import medvoll.luisu404.api.dto.medico.DatosDetalleMedico;
import medvoll.luisu404.api.dto.medico.DatosListaMedico;
import medvoll.luisu404.api.dto.medico.DatosRegistroMedico;
import medvoll.luisu404.api.model.medico.Medico;
import medvoll.luisu404.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico medicoDTO, UriComponentsBuilder uriComponentsBuilder){
        var medico = new Medico(medicoDTO);
        medicoRepository.save(medico);
        var uri = uriComponentsBuilder.path("api/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size=10,sort={"nombre"}) Pageable paginacion){
        var page = medicoRepository.findAllByActivoTrue(paginacion)
                .map(DatosListaMedico::new);
        return ResponseEntity.ok(page);
    }
    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizaMedico medicoDTO){
        var medico = medicoRepository.getReferenceById(medicoDTO.id());
        medico.actualizarInformacionesMedico(medicoDTO);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
       var medico =  medicoRepository.getReferenceById(id);
       medico.eliminarMedico();
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var medico =  medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

}
