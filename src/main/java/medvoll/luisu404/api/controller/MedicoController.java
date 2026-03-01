package medvoll.luisu404.api.controller;

import jakarta.validation.Valid;
import medvoll.luisu404.api.dto.medico.MedicoDTO;
import medvoll.luisu404.api.model.medico.Medico;
import medvoll.luisu404.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid MedicoDTO medicoDTO){

    medicoRepository.save(new Medico(medicoDTO));
    }
}
