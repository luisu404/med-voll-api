package medvoll.luisu404.api.controller;

import medvoll.luisu404.api.model.medico.DatosRegistroMedico;
import medvoll.luisu404.api.model.paciente.DatosRegistroPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacientes")

public class PacienteController {
    @PostMapping
    public void registrar(@RequestBody DatosRegistroPaciente datos){
        System.out.println(datos);
    }
}
