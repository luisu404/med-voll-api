package medvoll.luisu404.api.controller;

import jakarta.validation.Valid;
import medvoll.luisu404.api.dto.paciente.PacienteDTO;
import medvoll.luisu404.api.model.paciente.Paciente;
import medvoll.luisu404.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacientes")

public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid PacienteDTO pacienteDTO){

        pacienteRepository.save(new Paciente(pacienteDTO));
    }
}
