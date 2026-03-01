package medvoll.luisu404.api.controller;

import medvoll.luisu404.api.model.medico.DatosRegistroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @PostMapping
    public void registrar(@RequestBody DatosRegistroMedico datos){
        System.out.println(datos);
    }
}
